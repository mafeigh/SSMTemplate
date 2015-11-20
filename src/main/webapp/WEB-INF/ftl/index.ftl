<#import "include/template.ftl" as template/>
<@template.page title="申请">
<script src="${jsPath}/example/apply.js" type="text/javascript"></script>
<form id="editForm" name="editForm" method="post" action='${webContextPath}/example/htmlvalid.do'>
    <table class="tab-search">
        <thead>
        <tr>
            <th colspan="6">
            <span class="ticon"></span>
            用户信息：
            </th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <th>
                <span class="redf di">*</span>&nbsp;<span>UM账号</span>：
            </th>
            <td>
                <input type="text" name="id" style="width: 200px;"  value= "" >
                <span id="errorumAccount" class="red"></span>
            </td>
            <th>姓名：</th>
            <td>
                <input type="text" name="name" id="name" style="width: 200px;" maxlength="30"><form:errors path="name"/><br/>
            </td>
            </td>
        </tr>
        <tr>
            <th>money：</th>
            <td>
                <input type="text" name="money" style="width: 200px;" maxlength="30"><br/>
            </td>
            </td>
            <th>birthDate：</th>
            <td>
                <input type="text" name="birthDate"><br/>
            </td>
        </tr>
        </tbody>
    </table>
    <input type="submit" id="submit" value="页面提交">
</form>
<div class="oz mt10px tac">
    <input type="button" name="saveBtn" id="saveBtn" value="ajax提交" class="normallBtn commBtn" onClick="save();">
</div>
<br>
<input id="upfile" name="upfile" type="file">
<input type="button" value="上传" onclick="uploadFile();"/>
</@template.page>