<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>All buses</title>
</head>
<%@ include file="menu.jsp" %>
<body>
<div id="back"><a href="${pageContext.request.contextPath}/main_page">Back</a></div>
<div id="bus_list">
    <c:choose>
        <c:when test="${busList.size() > 0}">
            <table id="sortable" class="table">
                <caption>All buses</caption>
                <thead>
                <tr>
                    <th>Bus №</th>
                    <th>Which route belongs</th>
                    <th>Load percent</th>
                    <th>Bus size</th>
                    <th></th>
                    <th></th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="bus" items="${busList}">
                    <tr>
                        <td><c:out value="${bus.id}"/></td>
                        <td><a href="${pageContext.request.contextPath}/routes/route_info/${bus.route.id}"
                               class="btn btn-info">Route № <c:out value="${bus.route.id}"/></a></td>
                        <td><c:out value="${bus.loadPercent}"/>%</td>
                        <td><c:out value="${bus.size}"/></td>
                        <td><a href="${pageContext.request.contextPath}/buses/update_bus/${bus.id}"
                               class="btn btn-info">Update</a></td>
                        <td><a href="${pageContext.request.contextPath}/buses/delete_bus/${bus.id}"
                               class="btn btn-info">Delete</a></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </c:when>
        <c:otherwise>
            <h2>Bus list is empty because the simulation was not running.</h2>
        </c:otherwise>
    </c:choose>
</div>
</body>
</html>
