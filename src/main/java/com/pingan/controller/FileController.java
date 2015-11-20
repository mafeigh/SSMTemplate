package com.pingan.controller;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;

@Controller
@Scope("prototype")
public class FileController extends BaseController {
    private static final Logger LOGGER = LoggerFactory.getLogger(FileController.class);

    /**
     * 上传
     */
    @RequestMapping(value = "uploadFile.do")
    @ResponseBody
    public Map<String, Object> uploadFile(HttpServletRequest request, HttpServletResponse response) {
        try {
            // 转型为MultipartHttpRequest
            MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
            // 根据前台的name名称得到上传的文件
            MultipartFile file = multipartRequest.getFile("upfile");
            if (!file.isEmpty()) {
                if (file.getSize() > FileUtils.ONE_MB * Integer.parseInt(propertyReader.getValue("UploadFileMaxSize"), 10)) {
                    jsonMap.put("message", propertyReader.getValue("FileSizeExceed") + propertyReader.getValue("UploadFileMaxSize") + "m");
                    return jsonMap;
                }
                // 文件名
                String fileName = file.getOriginalFilename();

                // TODO:上传文件
                // file.transferTo();
                jsonMap.put("fileName", fileName);
                jsonMap.put("success", true);
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
        return jsonMap;
    }


    /**
     * 下载
     */
    @RequestMapping(value = "downloadFile.do")
    public void downloadFile(HttpServletRequest request, HttpServletResponse response) throws IOException {
        OutputStream os = response.getOutputStream();
        try {
            String fileName = request.getParameter("fileName");
            fileName = new String(fileName.getBytes("ISO-8859-1"), "UTF-8");
            response.setContentType("application/octet-stream; charset=utf-8");
            response.setHeader("Content-Disposition", "attachment; filename=" + this.getFileName(request, fileName));

            // TODO:读取文件供下载
            // os.write(FileUtils.readFileToByteArray(file));
            os.flush();
        } finally {
            IOUtils.closeQuietly(os);
        }
    }

    protected String getFileName(HttpServletRequest request, String fileName) throws UnsupportedEncodingException {
        String browserName = request.getHeader("User-Agent");
        // IE浏览器
        if (browserName.contains("MSIE")) {
            return URLEncoder.encode(fileName, "UTF-8");
        } else {
            // 其它浏览器
            return new String(fileName.getBytes("UTF-8"), "ISO-8859-1");
        }
    }
}
