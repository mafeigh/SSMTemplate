<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="webContextPath" scope="request" value="${pageContext.request.contextPath}"></c:set>
<c:set var="jsPath" scope="request" value="${webContextPath}/js"></c:set>
<c:set var="cssPath" scope="request" value="${webContextPath}/css"></c:set>
<c:set var="imgPath" scope="request" value="${webContextPath}/images"></c:set>


<link rel="stylesheet" type="text/css" href="${cssPath}/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="${cssPath}/themes/icon.css">

<script src="${jsPath}/jquery/jquery.min.js" type="text/javascript"></script>
<script src="${jsPath}/jquery/jquery.form.js" type="text/javascript"></script>
<script src="${jsPath}/jquery/ajaxfileupload.js" type="text/javascript"></script>
<script src="${jsPath}/easyui/jquery.easyui.min.js" type="text/javascript"></script>
