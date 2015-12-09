<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title></title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/css.css">
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/static/JQuery zTree v3.5.15/css/zTreeStyle/zTreeStyle.css">
    <style>
        ul.ztree {
            margin-top: 10px;
            border: 1px solid #617775;
            background: #f0f6e4;
            width: 220px;
            height: 200px;
            overflow-y: scroll;
            overflow-x: auto;
        }
    </style>

</head>
<body>

<form:form method="post" commandName="client">
    <form:hidden path="id"/>
    <c:if test="${op ne '新增'}">
        <form:hidden path="clientId"/>
        <form:hidden path="clientSecret"/>
    </c:if>

    <c:if test="${op eq '新增'}">
        <div class="form-group">
            <form:label path="clientId">客户端id：</form:label>
            <form:input path="clientId"/>
        </div>
        <div class="form-group">
            <form:label path="clientSecret">客户端key：</form:label>
            <form:input path="clientSecret"/>
        </div>
    </c:if>
    <div class="form-group">
        <form:label path="clientName">客户端名：</form:label>
        <form:input path="clientName"/>
    </div>

    <form:button>${op}</form:button>

</form:form>


</body>
</html>