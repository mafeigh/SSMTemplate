<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="include/commonPath.jsp" %>
<%@ include file="include/header.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>index</title>
</head>
<body>
<div class="easyui-layout" style="width:700px;height:350px;">
    <%--<div data-options="region:'north'" style="height:50px"></div>--%>
    <%--<div data-options="region:'south',split:true" style="height:50px;"></div>--%>
    <%--<div data-options="region:'east'" title="East" style="width:100px;"></div>--%>
    <div data-options="region:'west',split:true" title="West" style="width:160px;">
        <div class="easyui-accordion" data-options="fit:true">
            <div title="Title1" style="padding:10px;">
                <select class="easyui-combobox" name="state" style="width:100px">
                    <option value="AL">Al</option>
                    <option value="AK">Ak</option>
                </select>
            </div>
            <div title="Title2" data-options="selected:true" style="padding:10px;">
                content2
            </div>
        </div>
    </div>
    <div data-options="region:'center',title:'Main Title',iconCls:'icon-ok'">
        <div style="margin:20px 0;">
            activiti init: <br/>
            <a href="javascript:void(0)" class="easyui-linkbutton" onclick="activiti('activitiDeploy.do')">activitiDeploy</a>
            <a href="javascript:void(0)" class="easyui-linkbutton" onclick="activiti('activitiCreateUser.do')">activitiCreateUser</a>
        </div>
        <div>
        <br style="margin:20px 0;">
            start process: <br/>
            param:<input class="easyui-textbox" type="text" id="param"/>
            <a href="javascript:void(0)" class="easyui-linkbutton" onclick="activitiWithId('activitiProcessStart.do', 'i', $('#param').val())">activitiProcessStart</a><br/>

            <br/>
            process id:<input class="easyui-textbox" type="text" id="processId"/>
            <a href="javascript:void(0)" class="easyui-linkbutton" onclick="activitiWithId('activitiQueryTask.do', 'processId', $('#processId').val())">activitiQueryTask</a><br/>
            <br/>
            user id:<input class="easyui-textbox" type="text" id="userId"/>
            <a href="javascript:void(0)" class="easyui-linkbutton" onclick="activitiWithId('activitiCompleteTask.do', 'userId', $('#userId').val())">activitiCompleteTask</a>
        </div>
    </div>
</div>

<script src="${jsPath}/example/activiti.js" type="text/javascript"></script>
</body>
</html>

