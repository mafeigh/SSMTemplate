package com.pingan.controller;

import org.activiti.engine.IdentityService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.identity.Group;
import org.activiti.engine.identity.User;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("example")
public class ActivitiController extends BaseController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ExampleController.class);

    @Resource
    private RepositoryService repositoryService;
    @Resource
    private TaskService taskService;
    @Resource
    private RuntimeService runtimeService;
    @Resource
    private IdentityService identityService;

    private String deploymentId;

    @RequestMapping(value = "activitiDeploy.do")
    @ResponseBody
    public Map<String, Object> deploy() {
        deploymentId = repositoryService.createDeployment().addClasspathResource("activiti/test.bpmn20.xml").deploy().getId();
        jsonMap.put("result", deploymentId);
        return jsonMap;
    }

    @RequestMapping(value = "activitiCreateUser.do")
    @ResponseBody
    public Map<String, Object> createUser() {
        User jeff = identityService.newUser("jeff");
        jeff.setFirstName("zhang");
        jeff.setPassword("1234");
        identityService.saveUser(jeff);
        Group manager = identityService.newGroup("manager");
        manager.setName("manager group");
        identityService.saveGroup(manager);
        identityService.createMembership(jeff.getId(), manager.getId());
        jsonMap.put("result", 1);
        return jsonMap;
    }

    @RequestMapping(value = "activitiProcessStart.do")
    @ResponseBody
    public Map<String, Object> processStart(String i) {
        HashMap<String, Object> variableMap = new HashMap<String, Object>();
        variableMap.put("input", "zhangyu");
        variableMap.put("i", i);
        ProcessInstance pi = runtimeService.startProcessInstanceByKey("testprocess", variableMap);
        jsonMap.put("result", pi.getId());
        return jsonMap;
    }

    @RequestMapping(value = "activitiQueryTask.do")
    @ResponseBody
    public Map<String, Object> queryTask(String processId) {
        List<Task> tasks = taskService.createTaskQuery().processInstanceId(processId).list();
        LOGGER.info("all task count:{}", tasks.size());

        tasks = taskService.createTaskQuery().taskAssignee("jeff1").list();
        LOGGER.info("jeff's task count before claim:{}", tasks.size());

        // 查询manager组的任务列表
        tasks = taskService.createTaskQuery().taskCandidateGroup("manager").list();
        LOGGER.info("manager's task count:{}", tasks.size());

        // claim task
        tasks = taskService.createTaskQuery().processInstanceId(processId).list();
        LOGGER.info("all task count:{}", tasks.size());
        for (Task t : tasks) {
            taskService.claim(t.getId(), "jeff1");
        }
        jsonMap.put("result", 1);
        return jsonMap;
    }

    @RequestMapping(value = "activitiCompleteTask.do")
    @ResponseBody
    public Map<String, Object> completeTask(String userId) {
        List<Task> tasks = taskService.createTaskQuery().taskAssignee(userId).list();
        LOGGER.info("jeff's task count after claim:{}" , tasks.size());

        // complete task
        for(Task t : tasks) {
            taskService.complete(t.getId());
        }

        tasks = taskService.createTaskQuery().taskAssignee(userId).list();
        LOGGER.info("jeff's task count after claim:{}" , tasks.size());
        jsonMap.put("result", 1);
        return jsonMap;
    }
}
