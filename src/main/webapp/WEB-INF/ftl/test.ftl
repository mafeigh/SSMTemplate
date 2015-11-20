<#import "include/template.ftl" as template/>
<@template.page title="test">
    <@spring.message code="upload.limit" /><br>
    money：${(apply.money?string.percent)!}<br>
    birthDate：${(apply.birthDate?string('yyyy-MM-dd HH:mm:ss'))!}<br>
</@template.page>