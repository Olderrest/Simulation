<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Routes list</title>
</head>
<%@ include file="menu.jsp" %>
<body>
<div id="back"><a href="${pageContext.request.contextPath}/main_page">Back</a></div>
<div id="route_list">
    <c:choose>
        <c:when test="${routesList.size() > 0}">
            <table id="sortable" class="table">
                <caption>All routes</caption>
                <thead>
                <tr>
                    <th>Route №</th>
                    <th>Route type</th>
                    <th></th>
                    <th></th>
                    <th></th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="route" items="${routesList}">
                    <tr>
                        <td><c:out value="${route.id}"/></td>
                        <td><c:out value="${route.routeType}"/></td>
                        <c:choose>
                            <c:when test="${route.stops.size() < 2}">
                                <td style="width: 150px"><p>Invalid route. Need to <a
                                        href="${pageContext.request.contextPath}/stops/add_stop/${route.id}"
                                        class="btn btn-info">add</a> a stop</p></td>
                                <td></td>
                            </c:when>
                            <c:otherwise>
                                <c:choose>
                                    <c:when test="${route.buses.size() < 1}">
                                        <td style="width: 150px"><p>Invalid route. Need to <a
                                                href="${pageContext.request.contextPath}/buses/add_bus/${route.id}"
                                                class="btn btn-info">add</a> a bus</p></td>
                                        <td></td>
                                    </c:when>
                                    <c:otherwise>
                                        <td>
                                            <a href="${pageContext.request.contextPath}/routes/route_info/${route.id}"
                                               class="btn btn-info">Route info</a></td>
                                        <td><a href="${pageContext.request.contextPath}/routes/update_route/${route.id}"
                                               class="btn btn-info">Update</a></td>
                                    </c:otherwise>
                                </c:choose>
                            </c:otherwise>
                        </c:choose>
                        <td><a href="${pageContext.request.contextPath}/routes/delete_route/${route.id}"
                               class="btn btn-info">Delete</a></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <div class="form" id="add_route">
                <p>Form for adding new route</p>
                <spring:url value="/routes/save" var="addRoute"/>
                <form:form modelAttribute="addRoute" action="${addRoute}">
                    <form:label path="interval">Interval: </form:label>
                    <form:input path="interval" type="number"/>

                    <form:label path="interval">Interval: </form:label>
                    <form:select path="routeType">
                        <form:option value="STRAIGHT" label="STRAIGHT"/>
                        <form:option value="CIRCULAR" label="CIRCULAR"/>
                    </form:select>

                    <button class="btn btn-success btn-block">Save</button>
                </form:form>
            </div>
        </c:when>
        <c:otherwise>
            <h2>Route list is empty because the simulation was not running.</h2>
        </c:otherwise>
    </c:choose>
</div>
</body>
</html>
