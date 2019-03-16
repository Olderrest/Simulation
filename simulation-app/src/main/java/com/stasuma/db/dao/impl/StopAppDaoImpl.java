package com.stasuma.db.dao.impl;

import com.stasuma.db.DataSource;
import com.stasuma.db.dao.RoutesStopsDao;
import com.stasuma.db.dao.StopAppDao;
import com.stasuma.objects.Route;
import com.stasuma.objects.Stop;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StopAppDaoImpl implements StopAppDao {
    private static final Logger LOGGER = Logger.getLogger(StopAppDaoImpl.class);


    private static final String SQL_INSERT = "INSERT INTO stops(time, avg_workload) values(?,?)";
    private static final String SQL_FIND_ALL = "SELECT * FROM stops";
    private static final String SQL_UPDATE = "UPDATE stops SET time=?, avg_workload=? WHERE id=?";
    private static final String SQL_FIND_BY_ID = "SELECT * FROM stops WHERE id=?";
    private static final String SQL_DELETE_BY_ID = "DELETE FROM stops WHERE id=?";


    private final DataSource dataSource = DataSource.getInstance();

    private final RoutesStopsDao routesStopsDao = new RoutesStopsDaoImpl();

    @Override
    public void add(Stop stop, Route route) {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            connection.setAutoCommit(false);
            try (PreparedStatement ps = connection.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS)) {
                ps.setInt(1, stop.getTime());
                ps.setLong(2, stop.getAverageWorkload());
                ps.execute();
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        stop.setId(rs.getInt("id"));
                        LOGGER.trace("Stop successfully added. Info: " + stop);
                    }
                }
            }
            connection.commit();
            routesStopsDao.add(route, stop);
        } catch (SQLException e) {
            e.printStackTrace();
            dataSource.rollback(connection);
            LOGGER.trace("Error during add stop. Info: " + stop);
        } finally {
            dataSource.closeConnection(connection);
        }
    }



    @Override
    public void update(Stop stop) {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            connection.setAutoCommit(false);
            try(PreparedStatement ps = connection.prepareStatement(SQL_UPDATE)) {
                ps.setInt(1, stop.getTime());
                ps.setLong(2, stop.getAverageWorkload());
                ps.setInt(3, stop.getId());
                ps.executeUpdate();
                LOGGER.trace("Stop successfully updated. Info: " + stop);
            }
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            dataSource.rollback(connection);
            LOGGER.trace("Error during update stop.");
        }finally {
            dataSource.closeConnection(connection);
        }
    }

    @Override
    public Stop findById(int id) {
        Stop stop = new Stop();
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            connection.setAutoCommit(false);
            try(PreparedStatement ps = connection.prepareStatement(SQL_FIND_BY_ID)) {
                ps.setInt(1, id);
                try(ResultSet rs = ps.executeQuery()){
                    if (rs.next()){
                        stop.setId(rs.getInt("id"));
                        stop.setTime(rs.getInt("time"));
                        stop.setAverageWorkload(rs.getLong("avg_workload"));
                        LOGGER.trace("Stop successfully found. Info: " + stop);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            dataSource.rollback(connection);
            LOGGER.trace("Error during find stop.");
        }finally {
            dataSource.closeConnection(connection);
        }
        return stop;
    }

    @Override
    public void deleteById(int id) {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            connection.setAutoCommit(false);
            try(PreparedStatement ps = connection.prepareStatement(SQL_DELETE_BY_ID)) {
                ps.setInt(1, id);
                ps.executeUpdate();
                LOGGER.trace("Stop successfully deleted.");
            }
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            dataSource.rollback(connection);
            LOGGER.trace("Error during delete stop.");
        }finally {
            dataSource.closeConnection(connection);
        }
    }

    @Override
    public List<Stop> findAll() {
        List<Stop> stops = new ArrayList<>();
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            connection.setAutoCommit(false);
            try (PreparedStatement ps = connection.prepareStatement(SQL_FIND_ALL)) {
                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        Stop stop = new Stop();
                        stop.setId(rs.getInt("id"));
                        stop.setTime(rs.getInt("time"));
                        stop.setAverageWorkload(rs.getLong("avg_workload"));
                        stops.add(stop);
                    }
                    LOGGER.trace("List of stops successfully found.");
                }
            }
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            dataSource.rollback(connection);
            LOGGER.trace("Error during find list of stops.");
        } finally {
            dataSource.closeConnection(connection);
        }
        return stops;
    }
}
