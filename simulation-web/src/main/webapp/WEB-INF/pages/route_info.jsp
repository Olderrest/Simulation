<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Route info</title>
</head>
<%@ include file="menu.jsp" %>
<body>
<div id="back"><a href="${pageContext.request.contextPath}/routes/all_routes" >Back</a></div>
<div id="route_info">
    <table id="sortable" class="table">
        <caption>Route info</caption>
        <thead>
        <tr>
            <th>Route №r</th>
            <th width="80px">Stops list</th>
            <th width="80px">Bus list</th>
            <th>Movement bus interval(min)</th>
            <th>Average workload</th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td>${route.id}</td>
            <td><c:forEach var="stop" items="${route.stops}" varStatus="count">
                <c:out value="${count.count}"/>) <a href="${pageContext.request.contextPath}/stops/stop_info?id=${stop.id}" class="btn btn-info">Stop №: <c:out value="${stop.id}"/></a><br>
            </c:forEach></td>
            <td><c:forEach var="bus" items="${route.buses}" varStatus="count">
                <c:out value="${count.count}"/>) <a href="${pageContext.request.contextPath}/buses/all_buses" class="btn btn-info">Bus №: <c:out value="${bus.id}"/></a><br>
            </c:forEach> </td>
            <td><c:out value="${route.interval}"/></td>
            <td><c:out value="${route.avgWorkload}"/></td>
        </tr>
        </tbody>
    </table>
</div>
<div id="add_to_route" class="form">
    <div class="row">
        <div class="col-lg-6"><a href="/stops/add_stop/${route.id}" class="btn btn-info">Add</a> new stop to this route. </div>
        <div class="col-lg-6"><a href="/buses/add_bus/${route.id}" class="btn btn-info">Add</a> new bus to this route. </div>
    </div>
</div>
</body>
</html>
