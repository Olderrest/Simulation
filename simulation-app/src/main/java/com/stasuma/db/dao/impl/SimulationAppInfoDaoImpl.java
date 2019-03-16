package com.stasuma.db.dao.impl;

import com.stasuma.db.DataSource;
import com.stasuma.db.dao.SimulationAppInfoDao;
import com.stasuma.objects.SimulationInfo;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.Set;

public class SimulationAppInfoDaoImpl implements SimulationAppInfoDao {
    private static final Logger LOGGER = Logger.getLogger(SimulationAppInfoDaoImpl.class);


    private static final String SQL_INSERT = "INSERT INTO simulation_info(year,month,day,hour,minute,stop_id,route_id,bus_id,passengers_in,passengers_out,passengers_in_bus) " +
            "values(?,?,?,?,?,?,?,?,?,?,?)";
    private static final String SLQ_DELETE_ALL = "DELETE FROM simulation_info";

    private final DataSource dataSource = DataSource.getInstance();


    @Override
    public void add(SimulationInfo info) {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            connection.setAutoCommit(false);
            try (PreparedStatement ps = connection.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS)) {
                processStatement(ps, info);
                ps.execute();
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        info.setId(rs.getInt("id"));
                        LOGGER.trace("Bus info added. Info: " + info);
                    }
                }
            }
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            dataSource.rollback(connection);
            LOGGER.trace("Error during add bus info");
        } finally {
            dataSource.closeConnection(connection);
        }
    }

    @Override
    public void addAll(Set<SimulationInfo> infos) {
        final int batchSize = 1000;
        int count = 0;
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            connection.setAutoCommit(false);
            try (PreparedStatement ps = connection.prepareStatement(SQL_INSERT)) {
                for (SimulationInfo info : infos) {
                    processStatement(ps, info);
                    ps.addBatch();
                    if (++count % batchSize == 0) {
                        ps.executeBatch();
                    }
                }
                ps.executeBatch();
                LOGGER.trace("List infos added successfully.");
            }
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            dataSource.rollback(connection);
            LOGGER.trace("Error during add list bus info.");
        } finally {
            dataSource.closeConnection(connection);
        }
    }

    @Override
    public void deleteAll() {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            connection.setAutoCommit(false);
            try(PreparedStatement ps = connection.prepareStatement(SLQ_DELETE_ALL)) {
                ps.executeUpdate();
                LOGGER.trace("Bus info successfully deleted.");
            }
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            dataSource.rollback(connection);
            LOGGER.trace("Error during delete bus info.");
        }finally {
            dataSource.closeConnection(connection);
        }
    }

    private void processStatement(PreparedStatement ps, SimulationInfo info) throws SQLException {
        ps.setInt(1, info.getYear());
        ps.setInt(2, info.getMonth());
        ps.setInt(3, info.getDay());
        ps.setInt(4, info.getHour());
        ps.setInt(5, info.getMinute());
        ps.setInt(6, info.getStopId());
        ps.setInt(7, info.getRouteId());
        ps.setInt(8, info.getBusId());
        ps.setInt(9, info.getPassengersInCount());
        ps.setInt(10, info.getPassengersOutCount());
        ps.setInt(11, info.getPassengersInBusCount());
    }
}
