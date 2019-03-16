package com.stasuma.db.dao.impl;

import com.stasuma.db.DataSource;
import com.stasuma.db.dao.SimulationAppWorkTimeDao;
import com.stasuma.objects.SimulationWorkTime;
import org.apache.log4j.Logger;

import java.sql.*;

public class SimulationAppWorkTimeDaoImpl implements SimulationAppWorkTimeDao {
    private static final Logger LOGGER = Logger.getLogger(SimulationAppInfoDaoImpl.class);

    private static final String SQL_INSERT = "INSERT INTO simulation_work_time(years, months, days, start_time, end_time, route_count, bus_count, stop_count) values (?,?,?,?,?,?,?,?)";
    private static final String SQL_GET = "SELECT * FROM simulation_work_time";
    private static final String SQL_DELETE = "DELETE FROM simulation_work_time";

    private final DataSource dataSource = DataSource.getInstance();

    @Override
    public void add(SimulationWorkTime data) {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            connection.setAutoCommit(false);
            try (PreparedStatement ps = connection.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS)) {
                processData(ps, data);
                ps.execute();
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        data.setId(rs.getInt("id"));
                        LOGGER.trace("Simulation data successfully added. Info: " + data);
                    }
                }
            }
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            dataSource.rollback(connection);
            LOGGER.trace("Error during add simulation data. Info: " + data);
        } finally {
            dataSource.closeConnection(connection);
        }
    }

    @Override
    public SimulationWorkTime get() {
        SimulationWorkTime data = null;
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            connection.setAutoCommit(false);
            try (PreparedStatement ps = connection.prepareStatement(SQL_GET)) {
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        data = new SimulationWorkTime();
                        data.setId(rs.getInt("id"));
                        data.setYears(rs.getInt("years"));
                        data.setMonths(rs.getInt("months"));
                        data.setDays(rs.getInt("days"));
                        data.setStartSimulation(rs.getString("start_time"));
                        data.setStartSimulation(rs.getString("end_time"));
                        data.setRouteCount(rs.getInt("route_count"));
                        data.setBusCount(rs.getInt("bus_count"));
                        data.setStopCount(rs.getInt("stop_count"));
                        LOGGER.trace("Simulation data successfully found. Info: " + data);
                    }
                }
            }
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            dataSource.rollback(connection);
            LOGGER.trace("Error during find simulation data");
        } finally {
            dataSource.closeConnection(connection);
        }
        return data;
    }

    @Override
    public void delete() {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            connection.setAutoCommit(false);
            try (PreparedStatement ps = connection.prepareStatement(SQL_DELETE)) {
                ps.executeUpdate();
            }
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            dataSource.rollback(connection);
        }finally {
            dataSource.closeConnection(connection);
        }
    }

    private void processData(PreparedStatement ps, SimulationWorkTime data) throws SQLException {
        ps.setInt(1, data.getYears());
        ps.setInt(2, data.getMonths());
        ps.setInt(3, data.getDays());
        ps.setString(4, data.getStartSimulation());
        ps.setString(5, data.getEndSimulation());
        ps.setInt(6, data.getRouteCount());
        ps.setInt(7, data.getBusCount());
        ps.setInt(8, data.getStopCount());
    }
}
