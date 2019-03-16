<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<html>
<head>
    <title>Update Bus</title>
</head>
<body>
<%@ include file="menu.jsp" %>
<div id="back"><a href="${pageContext.request.contextPath}/stops/all_stops">Back</a></div>
<spring:url value="/buses/save" var="saveUrl"/>
<div id="update" class="form">
    <p>Form for updating the bus</p>
    <form:form modelAttribute="bus" method="post" action="${saveUrl}">
    <input type="hidden" name="routeId" value="${bus.route.id}"/>
        <form:hidden path="id"/>
    <div class="container">
        <div class="form-group col-xs-3">
            <form:label path="size">Bus size:</form:label>
            <form:input type="number" min="5" class="form-control form-control-sm" path="size"/>
        </div>
    </div>
    <button class="btn btn-success btn-block">Save</button>
    </form:form>
</body>
</html>
