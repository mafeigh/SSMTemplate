<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<style>
	.pages{cursor:pointer;}
	.pages:hover{text-decoration:underline;}
</style>
<%
	String formaction = request.getParameter("formaction");
	String colsize = request.getParameter("colsize");
%>
	<script type="text/javascript">
		function selectAll(checked){
			$("[name=idSelect]").attr("checked",checked);
		}
		//翻页存储的ID字符串，逗号分隔
		function getStrSelectedIds() {
			if ($("#ids") && typeof($("#ids").val()) != 'undefined') {
				var ids = "";
				var strSelectedIdsArr = $("#ids").val().split(",");
				$("[name=idSelect]:visible").each(function(index,domEle) {
					//如果是选中的，值是不相同的，拼ID串
					if ($(domEle).prop("checked")) {
						var theSame = false;
						for (var i = 0; i < strSelectedIdsArr.length; i++) {
							if (strSelectedIdsArr[i] == $(domEle).val()) {
								theSame = true;
							}
						}
						if (!theSame) {
							var id = $(domEle).val();
							ids += "," + id;
						}
					} else {
						//如果是未选中的，将已有的替换成空
						var selectedIds = $("#ids").val();
						var idsArr = selectedIds.split(",");
						var updateVal = "";
						for (var i = 0;i < idsArr.length; i++) {
							if (idsArr[i] == $(domEle).val()) {
								idsArr.splice(i,1);
							}
						}
						updateVal = idsArr.toString();
						$("#ids").val(updateVal);
					}
				});
				if (ids.length > 1) {
					ids = ids.substr(1);
				}
				if ($("#ids").val() != "" && ids != "") {
					$("#ids").val($("#ids").val() + "," + ids);
				} else if ($("#ids").val() != "") {
					$("#ids").val($("#ids").val());
				} else {
					$("#ids").val(ids);
				}
			}
		}
		//查询
		function goWhere(page,rows)
		{
			if ($("#ids") && typeof($("#ids").val()) != 'undefined') {
				$("#ids").val("");
			}
			if (typeof($("#searchBtn").attr("id")) != "undefined") {
				$("#searchBtn").attr("disabled",true);
			}
			$('#pagePage').val(page);
			$('#pageRows').val(rows);
			document.forms[0].action="<%=formaction%>";
			document.forms[0].submit();
		}
		//翻页
		function goWhereNext(page,rows)
		{
			if ($("#ids") && typeof($("#ids").val()) != 'undefined') {
				getStrSelectedIds();
			}
			$('#pagePage').val(page);
			$('#pageRows').val(rows);
			document.forms[0].action="<%=formaction%>";
			document.forms[0].submit();
		}
		//跳转页面
		function jumpGoWhere(countPage){
			var reg=/^\d*$/;
			var page=$('#pageInput').val();
			if ($("#pageInput").val() <= 0) {
				alert('输入的数字必须大于0!');
				$('#pageInput').val('');
				return false;
			}
			if(!reg.test(page))
			{
				alert('跳转页码必须是整数数字!');
				$('#pageInput').val('');
				return false;
			}
			if(page!='')
			{
				if(Number(countPage)<=Number(page))
				{
						page=countPage;
				}
				$('#pagePage').val(page);
			}
			document.forms[0].action="<%=formaction%>";
			document.forms[0].submit();
		}

		// 设置每页大小
		function setPageRow(inP){
			var value = inP.value;
			if(value!=""){
				if (value <= 0){
					value = 10;
				}
			}else{
				value=100000000;
			}
			$('#pagePage').val(1);
			$('#pageRows').val(value);
			document.forms[0].action="<%=formaction%>";
			document.forms[0].submit();
		}

		function order(orderstr, index)
		{
			var cpage = $('#pageCountPage').val();
			if(cpage>0){
				$('#pageOrder').val(orderstr);
				if (typeof index != 'undefined') {
					$('#orderIndex').val(index);
				}
				var des=$('#pageSort').val();
				if(des=='desc')
				{
					$('#pageSort').val('asc');
				}else{
					$('#pageSort').val('desc');
				}
				$('#pagePage').val(1);

				document.forms[0].action="<%=formaction%>";
				document.forms[0].submit();
			}
		}
	</script>

		<input type="hidden" name="pageNum" value="${page.pageNum}" id="pagePage">
		<input type="hidden" name="pageSize" value="${page.pageSize}" id="pageRows">
		<input type="hidden" name="pages" value="${page.pages}" id="pageCountPage">
		<input type="hidden" name="orderColumn" value="${page.orderColumn}" id="pageOrder">
		<input type="hidden" name="orderSort" value="${page.orderSort}" id="pageSort">
		<input type="hidden" name="orderIndex" value="${orderIndex}" id="orderIndex">

		<div>
			<c:if test="${page.pageNum > 0 && page.pages > 0}">
	    		<span class="pages" onclick="javascript:goWhereNext('1','${page.pageSize}','${orderColumn}','${sort}');">首 页</span>
	    		<c:if test="${page.pageNum <= 1 }">
	    			<span class="pages" onclick="javascript:goWhereNext('1','${page.pageSize}','${orderColumn}','${sort}');">上一页</span>
	    		</c:if>
	    		<c:if test="${page.pageNum > 1 }">
	    			<span class="pages" onclick="javascript:goWhereNext('${page.pageNum - 1 }','${page.pageSize}','${orderColumn}','${sort}');">上一页</span>
	    		</c:if>
	    		<c:if test="${page.pageNum < page.pages }">
	    			<span class="pages" onclick="javascript:goWhereNext('${page.pageNum + 1  }','${page.pageSize}','${orderColumn}','${sort}');">下一页</span>
	    		</c:if>
	    		<c:if test="${page.pageNum >= page.pages && page.pages > 0 }">
	    			<span class="pages" onclick="javascript:goWhereNext('${page.pages}','${page.pageSize}','${orderColumn}','${sort}');">下一页</span>
	    		</c:if>
	    		<c:if test="${page.pageNum >= page.pages && page.pages <= 0 }">
	    			<span class="pages" onclick="javascript:goWhereNext('1','${page.pageSize}','${orderColumn}','${sort}');">下一页</span>
	    		</c:if>
	    		<c:if test="${page.pages <= 0 }">
	    			<span class="pages" onclick="javascript:goWhereNext('1','${page.pageSize}','${orderColumn}','${sort}');">尾 页</span>
	    		</c:if>
	    		<c:if test="${page.pages > 0 }">
	    			<span class="pages" onclick="javascript:goWhereNext('${page.pages }','${page.pageSize}','${orderColumn}','${sort}');">尾 页</span>
	    		</c:if>
	    		&nbsp;${page.pageNum }/${page.pages }&nbsp;
	    		跳转 <input class="pageIpt" type="text" size='2' id="pageInput" maxlength="10" /> 页
	    		<a href="javascript:void(0);" class="tjBtn" onclick="jumpGoWhere('${page.pages }');"><span>跳转</span></a>
	    		每页
    			<select name="pageSizeSel" onchange="setPageRow(this)" >
	    			<c:if test="${page.pageSize <= 10 }">
	    			<option value="10" selected="selected">10</option>
	    			<option value="30">30</option>
	    			<option value="50">50</option>
	    			</c:if>
	    			<c:if test="${page.pageSize > 10 &&page.pageSize<=30 }">
	    			<option value="10">10</option>
	    			<option value="30" selected="selected">30</option>
	    			<option value="50">50</option>
	    			</c:if>
	    			<c:if test="${page.pageSize > 30 &&page.pageSize<=50}">
	    			<option value="10">10</option>
	    			<option value="30">30</option>
	    			<option value="50" selected="selected">50</option>
	    			</c:if>
	    			<c:if test="${page.pageSize > 50}">
	    			<option value="10">10</option>
	    			<option value="30">30</option>
	    			<option value="50">50</option>
	    			</c:if>
	    		</select> 条
    		</c:if>
		</div>