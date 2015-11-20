<#macro page title header='header.ftl' footer='footer.ftl'>
<#--<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>-->
<#--<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>-->
<#--<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>-->
<#--<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>-->
<#--<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>-->
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>${title}</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <#include "commonPath.ftl"/>
</head>
<body>
    <#include "${header}">
    <#nested>
    <#include "${footer}">
</body>
</html>
</#macro>