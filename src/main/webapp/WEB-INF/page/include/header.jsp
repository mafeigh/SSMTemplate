<div class="easyui-panel" style="padding:5px;">
    <a href="${webContextPath}\example\index.do" class="easyui-linkbutton" data-options="plain:true">Home</a>
    <a href="#" class="easyui-menubutton" menu="#mm1" iconCls="icon-edit">Search</a>
    <a href="${webContextPath}\example\add.do" class="easyui-linkbutton" data-options="plain:true, iconCls:'icon-help'">Add</a>
    <a href="#" class="easyui-linkbutton" data-options="plain:true" onclick="javascript:$.messager.alert('Title','About');">About</a>
</div>

<%--×Ó²Ëµ¥--%>
<div id="mm1" style="width:100px;">
    <div plain="true" onclick="javascript:window.location.href='${webContextPath}\\example\\search.do'">Search</div>
    <div class="menu-sep"></div>
    <div plain="true" iconCls="icon-edit" onclick="javascript:$.messager.alert('Title','About');">About</div>
</div>
