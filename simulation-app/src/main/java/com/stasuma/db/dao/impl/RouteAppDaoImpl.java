package com.stasuma.db.dao.impl;

import com.stasuma.db.DataSource;
import com.stasuma.db.dao.RouteAppDao;
import com.stasuma.objects.Bus;
import com.stasuma.objects.Route;
import com.stasuma.objects.RouteType;
import com.stasuma.objects.Stop;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RouteAppDaoImpl implements RouteAppDao {
    private static final Logger LOGGER = Logger.getLogger(RouteAppDaoImpl.class);


    private final String SQL_INSERT = "INSERT INTO routes(stops_number, bus_number, move_bus_interval, route_type, avg_workload) values (?,?,?,?,?)";
    private final String SQL_FIND_ALL = "SELECT * FROM routes";
    private final String SQL_FIND_ALL_STOPS_FOR_ROUTE = "SELECT DISTINCT s.id, s.time, s.avg_workload \n" +
            "FROM stops s \n" +
            "INNER JOIN route_stop rs ON rs.stop_id = s.id\n" +
            "INNER JOIN routes r ON rs.route_id = ?\n" +
            "ORDER BY id ASC;";
    private final String SQL_FIND_ALL_BUSES_FOR_ROUTE = "SELECT * FROM buses WHERE route_id = ?";
    private final String SQL_UPDATE = "UPDATE routes SET stops_number=?, bus_number=?, move_bus_interval=?, avg_workload=? WHERE id = ?";


    private final DataSource dataSource = DataSource.getInstance();

    @Override
    public Route add(Route route) {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            connection.setAutoCommit(false);
            try (PreparedStatement ps = connection.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS)) {
                ps.setInt(1, route.getStops().size());
                ps.setInt(2, route.getBuses().size());
                ps.setLong(3, route.getInterval());
                ps.setString(4, route.getRouteType().name());
                ps.setLong(5, route.getAverageWorkload());
                ps.execute();
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        route.setId(rs.getInt("id"));
                        LOGGER.trace("Route successfully added. Info: " + route);
                    }
                }
            }
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            dataSource.rollback(connection);
            LOGGER.trace("Error during add route. Info: " + route);
        } finally {
            dataSource.closeConnection(connection);
        }
        return route;
    }

    @Override
    public void update(Route route) {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            connection.setAutoCommit(false);
            try(PreparedStatement ps = connection.prepareStatement(SQL_UPDATE)) {
                ps.setInt(1, route.getStops().size());
                ps.setInt(2, route.getBuses().size());
                ps.setInt(3, route.getInterval());
                ps.setLong(4, route.getAverageWorkload());
                ps.setInt(5, route.getId());
                ps.executeUpdate();
                LOGGER.trace("Route successfully updated. Info: " + route);
            }
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            dataSource.rollback(connection);
            LOGGER.trace("Error during update route.");
        }finally {
            dataSource.closeConnection(connection);
        }
    }

    @Override
    public Route findById(int id) {
        return null;
    }

    @Override
    public void deleteById(int id) {

    }

    public List<Route> findAll() {
        List<Route> routes = new ArrayList<>();
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            connection.setAutoCommit(false);
            try (PreparedStatement ps = connection.prepareStatement(SQL_FIND_ALL)) {
                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        Route route = new Route();
                        route.setId(rs.getInt("id"));
                        route.setStops(findAllStopsForRoute(route.getId()));
                        route.setBuses(findAllBusesForRoute(route.getId()));
                        route.setInterval(rs.getInt("move_bus_interval"));
                        route.setRouteType(RouteType.getType(rs.getString("route_type")));
                        routes.add(route);
                    }
                    LOGGER.trace("All routes found.");
                }
            }
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            dataSource.rollback(connection);
            LOGGER.trace("Error during find all routes.");
        } finally {
            dataSource.closeConnection(connection);
        }
        return routes;
    }

    private List<Stop> findAllStopsForRoute(int routeId) {
        List<Stop> stopsForRoute = new ArrayList<>();
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            connection.setAutoCommit(false);
            try (PreparedStatement ps = connection.prepareStatement(SQL_FIND_ALL_STOPS_FOR_ROUTE)) {
                ps.setInt(1, routeId);
                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        Stop stop = new Stop();
                        stop.setId(rs.getInt("id"));
                        stop.setTime(rs.getInt("time"));
                        stop.setAverageWorkload(rs.getLong("avg_workload"));
                        stopsForRoute.add(stop);
                    }
                    LOGGER.trace("All stops for route id: " + routeId + " found.");
                }
            }
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            dataSource.rollback(connection);
            LOGGER.trace("Error during found all stops for route id: " + routeId);
        } finally {
            dataSource.closeConnection(connection);
        }
        return stopsForRoute;
    }

    private Set<Bus> findAllBusesForRoute(int routeId) {
        Set<Bus> buses = new HashSet<>();
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            connection.setAutoCommit(false);
            try (PreparedStatement ps = connection.prepareStatement(SQL_FIND_ALL_BUSES_FOR_ROUTE)) {
                ps.setInt(1, routeId);
                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        Bus bus = new Bus();
                        bus.setId(rs.getInt("id"));
                        bus.setSize(rs.getInt("size"));
                        bus.setLoadPercentage(rs.getInt("load_percent"));
                        bus.setRouteId(rs.getInt("route_id"));
                        buses.add(bus);
                    }
                    LOGGER.trace("All buses for route id: " + routeId + " found.");
                }
            }
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            dataSource.rollback(connection);
            LOGGER.trace("Error during find all buses for route id: " + routeId);
        } finally {
            dataSource.closeConnection(connection);
        }
        return buses;
    }

}
