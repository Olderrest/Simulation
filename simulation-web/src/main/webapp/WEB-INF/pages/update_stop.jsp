<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Update Stop</title>
</head>
<body>
<%@ include file="menu.jsp" %>
<div id="back"><a href="${pageContext.request.contextPath}/stops/all_stops">Back</a></div>
<spring:url value="/stops/save" var="saveUrl"/>
<div id="update" class="form">
    <p>Form for updating the stop</p>
    <form:form modelAttribute="stop" method="post" action="${saveUrl}">
        <form:hidden path="id"/>
        <input type="hidden" name="routeId" value="${stop.routes.iterator().next().id}"/>
        <div class="container">
            <div class="form-group col-xs-3">
                <form:label path="time">Time from next stop:</form:label>
                <form:input type="number" min="1" class="form-control form-control-sm" path="time"/>
            </div>
        </div>
        <button class="btn btn-success btn-block">Save</button>
    </form:form>
</div>

</body>
</html>
