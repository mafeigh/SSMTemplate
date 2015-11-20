<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="include/commonPath.jsp"%>
<%@ include file="include/header.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <title>search</title>
  </head>
  <body>
  <table id="dg" class="easyui-datagrid" title="Basic DataGrid" style="width:700px;height:400px"
         data-options="singleSelect:true,pagination:true,url:'${webContextPath}\\example\\getDataList.do',method:'get'">
    <thead>
    <tr>
      <th field="id" sortable="true" width="40">ID</th>
      <th data-options="field:'name',width:80" sortable="true">name</th>
      <th data-options="field:'money',width:80,align:'right'">money</th>
      <th data-options="field:'birthdate',width:120,align:'right'">birthDate</th>
    </tr>
    </thead>
  </table>
  </body>
</html>
