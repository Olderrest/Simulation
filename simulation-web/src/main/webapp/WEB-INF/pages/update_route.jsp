<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Update Route</title>
</head>
<body>
<%@ include file="menu.jsp" %>
<div id="back"><a href="${pageContext.request.contextPath}/routes/all_routes">Back</a></div>
<spring:url value="/routes/save" var="saveUrl"/>
<div id="update" class="form">
    <p>Form for updating the route</p>
    <form:form modelAttribute="route" method="post" action="${saveUrl}">
        <form:hidden path="id"/>
            <div class="container">
                <div class="form-group col-xs-3">
                    <form:label path="interval">Interval:</form:label>
                    <form:input type="number" min="1" class="form-control form-control-sm" path="interval"/>

                    <form:label path="routeType">Route Type:</form:label>
                    <form:select class="form-control form-control-sm" path="routeType">
                        <form:option value="STRAIGHT" label="STRAIGHT"/>
                        <form:option value="CIRCULAR" label="CIRCULAR"/>
                    </form:select>
                </div>
            </div>
        <button class="btn btn-success btn-block">Save</button>
    </form:form>
</div>
</body>
</html>
