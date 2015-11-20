package com.pingan.exception;

import org.hibernate.validator.method.MethodConstraintViolation;
import org.hibernate.validator.method.MethodConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.ExceptionHandlerExceptionResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.List;

/**
 * 不必在Controller中对异常进行处理，抛出即可，由此异常解析器统一控制。
 * ajax请求（有@ResponseBody的Controller）发生错误，输出JSON。
 * 页面请求（无@ResponseBody的Controller）发生错误，输出错误页面。
 * 需要与AnnotationMethodHandlerAdapter使用同一个messageConverters
 */
public class AnnotationHandlerMethodExceptionResolver extends ExceptionHandlerExceptionResolver {
    private final static Logger LOGGER = LoggerFactory.getLogger(AnnotationHandlerMethodExceptionResolver.class);
    private String defaultErrorView;

    public String getDefaultErrorView() {
        return defaultErrorView;
    }

    public void setDefaultErrorView(String defaultErrorView) {
        this.defaultErrorView = defaultErrorView;
    }

    protected ModelAndView doResolveHandlerMethodException(HttpServletRequest request, HttpServletResponse response, HandlerMethod handlerMethod, Exception exception) {
        // 打印错误日志
        exception.printStackTrace();
        LOGGER.error(exception.getMessage());

        // 获取异常发生时运行的函数
        if (handlerMethod == null) {
            return null;
        }
        Method methodThrowException = handlerMethod.getMethod();
        if (methodThrowException == null) {
            return null;
        }

        // 函数上定义了@ResponseBody就要返回json
        boolean returnJson = AnnotationUtils.findAnnotation(methodThrowException, ResponseBody.class) != null ? true : false;
        // 定义了ExceptionHandler
        ModelAndView modelAndViewByExceptionHandler = super.doResolveHandlerMethodException(request, response, handlerMethod, exception);
        // ExceptionHandler处理异常
        if (null != modelAndViewByExceptionHandler) {
            if (returnJson) {
                return writeToResponse(modelAndViewByExceptionHandler.getModelMap(), request, response);
            } else {
                return modelAndViewByExceptionHandler;
            }
        } else { // 自己进行异常处理
            ExceptionReturnValue exceptionReturnValue = getExceptionReturnValue(exception);
            if (returnJson) {
                return returnJson(request, response, exceptionReturnValue, methodThrowException);
            } else {
                return returnPage(exceptionReturnValue);
            }
        }
    }

    private ModelAndView returnPage(ExceptionReturnValue exceptionReturnValue) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("resultDesc", exceptionReturnValue.getResultDesc());
        modelAndView.setViewName(defaultErrorView);
        return modelAndView;
    }

    private ModelAndView returnJson(HttpServletRequest request, HttpServletResponse response, ExceptionReturnValue exceptionReturnValue, Method methodThrowException) {
        try {
            ResponseStatus responseStatusAnn = AnnotationUtils.findAnnotation(methodThrowException, ResponseStatus.class);
            if (responseStatusAnn != null) {
                HttpStatus responseStatus = responseStatusAnn.value();
                String reason = responseStatusAnn.reason();
                if (!StringUtils.hasText(reason)) {
                    response.setStatus(responseStatus.value());
                } else {
                    response.sendError(responseStatus.value(), reason);
                }
            }



            //exceptionReturnValue.setTimestamp(DateHelper.getCurrentTimeStamp(null));
            writeToResponse(exceptionReturnValue, request, response);
            return new ModelAndView();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private ExceptionReturnValue getExceptionReturnValue(Exception exception) {
        ExceptionReturnValue exceptionReturnValue = new ExceptionReturnValue();

        // 验证失败的异常只捕获第一条验证失败信息
        if (exception instanceof ConstraintViolationException) {
            for (ConstraintViolation<?> c : ((ConstraintViolationException) exception).getConstraintViolations()) {
                exceptionReturnValue.setResultDesc(c.getMessage());
                break;
            }
        }
        if (exception instanceof MethodConstraintViolationException) {
            for (MethodConstraintViolation c : ((MethodConstraintViolationException) exception).getConstraintViolations()) {
                exceptionReturnValue.setResultDesc(c.getMessage());
                break;
            }
        }
        return exceptionReturnValue;
    }

    private ModelAndView writeToResponse(Object exceptionValue, HttpServletRequest request, HttpServletResponse response) {
        HttpInputMessage inputMessage = new ServletServerHttpRequest(request);
        List<MediaType> acceptedMediaTypes = inputMessage.getHeaders().getAccept();
        if (acceptedMediaTypes.isEmpty()) {
            acceptedMediaTypes = Collections.singletonList(MediaType.ALL);
        }
        MediaType.sortByQualityValue(acceptedMediaTypes);
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        Class<?> returnValueType = exceptionValue.getClass();
        List<HttpMessageConverter<?>> messageConverters = super.getMessageConverters();
        if (messageConverters == null) {
            return null;
        }

        for (MediaType acceptedMediaType : acceptedMediaTypes) {
            for (HttpMessageConverter messageConverter : messageConverters) {
                if (messageConverter.canWrite(returnValueType, acceptedMediaType)) {
                    try {
                        messageConverter.write(exceptionValue, acceptedMediaType, outputMessage);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    return new ModelAndView();
                }
            }
        }

        if (logger.isWarnEnabled()) {
            logger.warn("Could not find HttpMessageConverter that supports return type [" + returnValueType + "] and " + acceptedMediaTypes);
        }
        return null;
    }
}
