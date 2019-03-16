package com.stasuma.db.dao.impl;

import com.stasuma.db.DataSource;
import com.stasuma.db.dao.BusAppDao;
import com.stasuma.objects.Bus;
import com.stasuma.objects.Route;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.List;

public class BusAppDaoImpl implements BusAppDao {
    private static final Logger LOGGER = Logger.getLogger(BusAppDaoImpl.class);

    private final String SQL_INSERT = "INSERT INTO buses(size, load_percent, route_id) values (?,?,?)";
    private static final String SQL_UPDATE = "UPDATE buses SET size=?, load_percent=?, route_id=? WHERE id=?";

    private final DataSource dataSource = DataSource.getInstance();

    @Override
    public void add(Bus bus, Route route) {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            connection.setAutoCommit(false);
            try (PreparedStatement ps = connection.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS)) {
                ps.setInt(1, bus.getSize());
                ps.setInt(2, bus.getLoadPercentage());
                ps.setInt(3, route.getId());
                ps.execute();
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        bus.setId(rs.getInt("id"));
                        LOGGER.trace("Bus successfully added. Info: " + bus);
                    }
                }
            }
            connection.commit();
        }catch (SQLException e) {
            e.printStackTrace();
            dataSource.rollback(connection);
            LOGGER.trace("Error during add bus. Info :" + bus);
        } finally {
            dataSource.closeConnection(connection);
        }
    }

    @Override
    public void update(Bus bus) {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            connection.setAutoCommit(false);
            try(PreparedStatement ps = connection.prepareStatement(SQL_UPDATE)) {
                ps.setInt(1, bus.getSize());
                ps.setInt(2, bus.getLoadPercentage());
                ps.setInt(3, bus.getRouteId());
                ps.setInt(4, bus.getId());
                ps.executeUpdate();
                LOGGER.trace("Bus successfully updated. Info: " + bus);
            }
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            dataSource.rollback(connection);
            LOGGER.trace("Error during update Bus.");
        }finally {
            dataSource.closeConnection(connection);
        }
    }

    @Override
    public Bus findById(int id) {
        return null;
    }

    @Override
    public List<Bus> findAll() {
        return null;
    }

    @Override
    public void delete() {

    }
}
