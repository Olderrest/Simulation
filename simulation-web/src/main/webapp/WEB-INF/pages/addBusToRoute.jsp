<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Add bus to route</title>
</head>
<body>
<%@ include file="menu.jsp" %>
<spring:url value="/buses/save" var="saveUrl"/>
<div id="update" class="form">
    <p>Form for adding a new bus</p>
    <form:form modelAttribute="bus" method="post" action="${saveUrl}">
        <form:hidden path="id"/>
        <input type="hidden" name="routeId" value="${routeId}"/>
        <div class="container">
            <div class="form-group col-xs-3">
                <form:label path="size">Bus size:</form:label>
                <form:input type="number" min="5" class="form-control form-control-sm" path="size"/>
            </div>
        </div>
        <button class="btn btn-success btn-block">Save</button>
    </form:form>
</div>
</body>
</html>
