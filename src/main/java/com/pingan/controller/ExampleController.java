package com.pingan.controller;

import com.pingan.model.Apply;
import com.pingan.service.ExampleService;
import com.pingan.util.Page;
import com.pingan.validator.NotNullEx;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;

@Validated
@Controller
@Scope("prototype")
@RequestMapping("example")
public class ExampleController extends BaseController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ExampleController.class);

    @Resource
    private ExampleService exampleService;

    @RequestMapping(value = "index.do")
    public String index() {
        // slf4j输出2个以上参数需要使用数组
        Object data[] = {"UploadFileMaxSize", propertyReader.getValue("UploadFileMaxSize"), "m"};
        LOGGER.info("propertyReader get {} is {} {}" , data);
        return "index";
    }

    // 数据库查询列表，返回json给datagrid控件
    @RequestMapping(value = "getDataList.do")
    @ResponseBody
    public Map<String, Object> getDateList(Integer page, Integer rows, String sort, String order) {
        Page pageInfo = new Page();
        pageInfo.setPageNum(page);
        pageInfo.setPageSize(rows);
        pageInfo.setOrderColumn(sort);
        pageInfo.setOrderSort(order);

        jsonMap.put("flag", "1");
        jsonMap.put("total", exampleService.getApplyCount());
//        返回的json格式
//        {"total":28,"rows":[
//            {"id":"01","name":"Koi","money":10.00,"birthDate":"1980/02/03"},
//            {"id":"02","name":"si","money":13.00,"birthDate":"1988/02/03"}
//        ]}
        List<Apply> applyList = exampleService.getApplyByPage(pageInfo).getList();
        jsonMap.put("rows", applyList);
        return jsonMap;
    }

    // 用annotation验证参数和返回值，返回页面
    @RequestMapping(value = "htmlvalid.do")
    @NotNull public String htmlValid(@Valid Apply a, BindingResult result, ModelMap modelMap) {
        Apply apply = exampleService.getApply(a.getId());
        modelMap.addAttribute("apply", apply);
        return "test";
    }

    // 用annotation验证参数和返回值，返回json
    @RequestMapping(value = "jsonvalid.do")
    @ResponseBody
    @NotNull public Map<String, Object> jsonValid(@NotNullEx(field="id", message = "param is null") Integer id,
                                                  @Valid Apply a, BindingResult result, ModelMap modelMap) {
        Apply apply = exampleService.getApply(id);
        modelMap.addAttribute("apply", apply);
        jsonMap.put("flag", "1");
        return jsonMap;
    }

    // restful范例
    @RequestMapping(value = "/blog/{blogId}/message/{msgId}", method = RequestMethod.DELETE)
    @ResponseBody
    public Map<String, Object> delete(@PathVariable("blogId") Long blogId, @PathVariable("msgId") Long msgId) {
        jsonMap.put("blog", blogId);
        jsonMap.put("message", msgId);
        return jsonMap;
    }

    // 跳转测试页面使用
    @RequestMapping(value = "add.do")
    public String add() {
        return "add";
    }

    @RequestMapping(value = "search.do")
    public String search() {
        return "search";
    }
}
