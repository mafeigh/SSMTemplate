<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="include/commonPath.jsp"%>
<%@ include file="include/header.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <title>add</title>
	 <script src="${jsPath}/example/apply.js" type="text/javascript"></script>
  </head>
  <body>
  <form id="editForm" name="editForm" class="easyui-form" method="post" action='${webContextPath}/example/htmlvalid.do'>
	  <table class="tab-search">
		  <thead>
			  <th colspan="6">
			  用户信息：
			  </th>
		  </thead>
		  <tbody>
    	  <tr>
			  <th>
				  UM账号:
			  </th>
			  <td>
				  <input type="text" name="id" class="easyui-textbox" value="" data-options="required:true">
			  </td>
			  <th>姓名：</th>
			  <td>
				  <input type="text" name="name" class="easyui-textbox" id="name" data-options="required:true" maxlength="30"><br/>
			  </td>
		  </tr>
		  <tr>
			  <th>money：</th>
			  <td>
				  <input type="text" name="money" style="width: 200px;" maxlength="30"><br/>
			  </td>
			  <th>birthDate：</th>
			  <td>
				  <input class="easyui-datebox" name="birthDate"><br/>
			  </td>
		  </tr>
		  </tbody>
	  </table>
	  <input type="submit" id="submit" value="页面提交">
  </form>
  <div class="oz mt10px tac">
	  <%--<input type="button" name="saveBtn" id="saveBtn" value="ajax提交" class="normallBtn commBtn" onClick="save();">--%>
      <a href="javascript:void(0)" class="easyui-linkbutton" onclick="save()">ajax提交</a>
  </div>
  <br>
  <input id="upfile" name="upfile" type="file">
  <input type="button" value="上传" onclick="uploadFile();"/>
  </body>
</html>

