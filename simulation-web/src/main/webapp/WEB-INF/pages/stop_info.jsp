<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Stop info</title>
</head>
<%@ include file="menu.jsp" %>
<body>
<div id="back"><a href="${pageContext.request.contextPath}/stops/all_stops">Back</a></div>
<div id="stop_info">
    <table id="sortable" class="table">
        <caption>Stop info</caption>
        <thead>
        <tr>
            <th>Stop №</th>
            <th>Distance from previous stop(min)</th>
            <th>Belongs to the route(s) №</th>
            <th>Average workload</th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td>${stop.id}</td>
            <td>${stop.time}</td>
            <td><c:forEach var="route" items="${routes}" varStatus="count">
                <c:out value="${count.count}"/>)<a href="${pageContext.request.contextPath}/routes/route_info/${route.id}" class="btn btn-info">Route №:
                <c:out value="${route.id}"/></a>
            </c:forEach></td>
            <td><c:out value="${stop.avgWorkload}"/></td>
        </tr>
        </tbody>
    </table>
</div>
</body>
</html>
