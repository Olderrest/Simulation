<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<html>
<head>
    <title>Bus info</title>
    <%@ include file="menu.jsp" %>
    <script type="text/javascript" src="<c:url value="/resources/js/daysSelect.js"/>"></script>
    <script type="text/javascript" src="<c:url value="/resources/js/filterSelect.js"/>"></script>
</head>
<body>
<div id="back"><a href="${pageContext.request.contextPath}/main_page">Back</a></div>
<c:choose>
    <c:when test="${infoForSelect.routes.size() > 0}">
        <div id="query_form">
            <p>To get information, specify the request</p>
            <div id="select">
                <form:form method="post" modelAttribute="simulationInfoQuery" action="simulation_info">
                    <c:if test="${infoForSelect.years.size() > 0}">
                        <div class="row">
                            <div class="col-lg-6">
                                <form:label path="yearFrom">Year from<span id="star">*</span>: </form:label>
                                <form:select path="yearFrom">
                                    <form:option value="-1" label="None"/>
                                    <form:options items="${infoForSelect.years}"/>
                                </form:select>
                                <p style="color: red;"><form:errors path="yearFrom"/></p>
                            </div>
                            <div class="col-lg-6">
                                <form:label path="yearUntil">Year until: </form:label>
                                <form:select path="yearUntil">
                                    <form:option value="-1" label="None"/>
                                    <form:options items="${infoForSelect.years}"/>
                                </form:select>
                            </div>
                        </div>
                    </c:if>
                    <c:if test="${infoForSelect.months.size() > 0}">
                        <div class="row">
                            <div class="col-lg-6">
                                <form:label path="monthFrom">Month from<span id="star">*</span>: </form:label>
                                <form:select id="month_select" path="monthFrom">
                                    <form:option value="-1" label="None"/>
                                    <form:options items="${infoForSelect.months}"/>
                                </form:select>
                                <p style="color: red;"><form:errors path="monthFrom"/></p>
                            </div>

                            <div class="col-lg-6">
                                <form:label path="monthUntil">Month until: </form:label>
                                <form:select path="monthUntil">
                                    <form:option value="-1" label="None"/>
                                    <form:options items="${infoForSelect.months}"/>
                                </form:select>
                            </div>
                        </div>
                    </c:if>
                    <div class="row">
                        <div class="col-lg-6">
                            <form:label path="dayFrom">Day from<span id="star">*</span>: </form:label>
                            <form:select id="day_selectFrom" path="dayFrom">
                                <form:option value="-1" label="None"/>
                                <form:options items="${infoForSelect.days}"/>
                            </form:select>
                            <p style="color: red;"><form:errors path="dayFrom"/></p>
                        </div>

                        <div class="col-lg-6">
                            <form:label path="dayUntil">Day until: </form:label>
                            <form:select id="day_selectUntil" path="dayUntil">
                                <form:option value="-1" label="None"/>
                                <form:options items="${infoForSelect.days}"/>
                            </form:select>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-lg-6">
                            <form:label path="hourFrom">Hour from<span id="star">*</span>: </form:label>
                            <form:select path="hourFrom">
                                <form:option value="-1" label="None"/>
                                <form:options items="${infoForSelect.hours}"/>
                            </form:select>
                            <p style="color: red;"><form:errors path="hourFrom"/></p>
                        </div>

                        <div class="col-lg-6">
                            <form:label path="hourUntil">Hour until: </form:label>
                            <form:select path="hourUntil">
                                <form:option value="-1" label="None"/>
                                <form:options items="${infoForSelect.hours}"/>
                            </form:select>
                        </div>
                    </div>
                    <div id="filter_select">
                        <p>Select which filter use:</p>
                        <select id="filterSelector">
                            <option value="bus">Bus filter</option>
                            <option value="route">Route filter</option>
                            <option value="stop">Stop filter</option>
                        </select>
                    </div>
                    <div id="filters">
                        <div id="bus_filter">
                            <div class="row">
                                <div class="col-lg-6">
                                    <form:label path="busIdFrom">Bus № from<span id="star">*</span>: </form:label>
                                    <form:select path="busIdFrom">
                                        <form:option value="0" label="None"/>
                                        <form:options items="${infoForSelect.buses}"/>
                                    </form:select>
                                    <p style="color: red;"><form:errors path="busIdFrom"/></p>
                                </div>

                                <div class="col-lg-6">
                                    <form:label path="busIdUntil">Bus № until: </form:label>
                                    <form:select path="busIdUntil">
                                        <form:option value="0" label="None"/>
                                        <form:options items="${infoForSelect.buses}"/>
                                    </form:select>
                                </div>
                            </div>
                        </div>
                        <div id="route_filter" class="hidden_div">
                            <div class="row">
                                <div class="col-lg-6">
                                    <form:label path="routeIdFrom">Route № from<span id="star">*</span>: </form:label>
                                    <form:select path="routeIdFrom">
                                        <form:option value="-1" label="None"/>
                                        <form:options items="${infoForSelect.routes}"/>
                                    </form:select>
                                    <p style="color: red;"><form:errors path="routeIdFrom"/></p>
                                </div>

                                <div class="col-lg-6">
                                    <form:label path="routeIdUntil">Route № until: </form:label>
                                    <form:select path="routeIdUntil">
                                        <form:option value="-1" label="None"/>
                                        <form:options items="${infoForSelect.routes}"/>
                                    </form:select>
                                </div>
                            </div>
                        </div>
                        <div id="stop_filter" class="hidden_div">
                            <div class="row">
                                <div class="col-lg-6">
                                    <form:label path="stopIdFrom">Stop № from<span id="star">*</span>: </form:label>
                                    <form:select path="stopIdFrom">
                                        <form:option value="-1" label="None"/>
                                        <form:options items="${infoForSelect.stops}"/>
                                    </form:select>
                                    <p style="color: red;"><form:errors path="stopIdFrom"/></p>
                                </div>

                                <div class="col-lg-6">
                                    <form:label path="stopIdUntil">Stop № until: </form:label>
                                    <form:select path="stopIdUntil">
                                        <form:option value="-1" label="None"/>
                                        <form:options items="${infoForSelect.stops}"/>
                                    </form:select>
                                </div>
                            </div>
                        </div>
                    </div>
                    <input type="submit" class="btn btn-success btn-block" value="Show info"/>
                </form:form>
            </div>
        </div>
        <div id="global_info">
            <h4>Global simulation information</h4>
            <p>Simulation start: <c:out value="${simulationWorkTime.startSimulationTime}"/></p>
            <p>Simulation end: <c:out value="${simulationWorkTime.endSimulationTime}"/></p>
            <p>Time in simulation: Years:<c:out value="${simulationWorkTime.year}"/> Months:<c:out
                    value="${simulationWorkTime.month}"/> Days: <c:out value="${simulationWorkTime.day}"/></p>
            <p>Route number in simulation: <c:out value="${simulationWorkTime.routeCount}"/></p>
            <p>Bus number in simulation: <c:out value="${simulationWorkTime.busCount}"/></p>
            <p>Stop number in simulation: <c:out value="${simulationWorkTime.stopCount}"/></p>
        </div>
        <c:if test="${not empty infos}">
            <div id="bus_info">
                <table id="sortable" class="table">
                    <caption>Bus information</caption>
                    <thead>
                    <tr>
                        <th>Year</th>
                        <th>Month</th>
                        <th>Day</th>
                        <th>Hour</th>
                        <th>Minute</th>
                        <th>Stop №</th>
                        <th>Route №</th>
                        <th>Bus №</th>
                        <th>Passengers boarded</th>
                        <th>Passengers left bus</th>
                        <th>Passengers in bus</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${infos}" var="info">
                        <tr>
                            <td><c:out value="${info.year}"/></td>
                            <td><c:out value="${info.month}"/></td>
                            <td><c:out value="${info.day}"/></td>
                            <td><c:out value="${info.hour}"/></td>
                            <td><c:out value="${info.minute}"/></td>
                            <td><c:out value="${info.stopId}"/></td>
                            <td><c:out value="${info.routeId}"/></td>
                            <td><c:out value="${info.busId}"/></td>
                            <td><c:out value="${info.passengersInCount}"/></td>
                            <td><c:out value="${info.passengersOutCount}"/></td>
                            <td><c:out value="${info.passengersInBusCount}"/></td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
                <c:choose>
                    <%-- Show Prev as link if not on first page --%>
                    <c:when test="${pagedList.firstPage}">
                        <span>Previous</span>
                    </c:when>
                    <c:otherwise>
                        <c:url value="/simulation_info/prev" var="url"/>
                        <a href='<c:out value="${url}" />' class="btn btn-info">Previous</a>
                    </c:otherwise>
                </c:choose>
                <c:forEach begin="1" end="${pagedList.pageCount}" step="1" varStatus="tagStatus">
                    <c:choose>
                        <c:when test="${(pagedList.page + 1) == tagStatus.index}">
                            <span>${tagStatus.index}</span>
                        </c:when>
                        <c:otherwise>
                            <c:url value="/simulation_info/${tagStatus.index}" var="url"/>
                            <a href='<c:out value="${url}" />'>${tagStatus.index}</a>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
                <c:choose>
                    <%-- Show Next as link if not on last page --%>
                    <c:when test="${pagedList.lastPage}">
                        <span>Next</span>
                    </c:when>
                    <c:otherwise>
                        <c:url value="/simulation_info/next" var="url"/>
                        <a href='<c:out value="${url}" />' class="btn btn-info">Next</a>
                    </c:otherwise>
                </c:choose>
            </div>
        </c:if>
    </c:when>
    <c:otherwise>
        <h2>Information is not available because the simulation was not running.</h2>
    </c:otherwise>
</c:choose>
</body>
</html>
