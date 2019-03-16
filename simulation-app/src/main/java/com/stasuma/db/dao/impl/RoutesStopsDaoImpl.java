package com.stasuma.db.dao.impl;

import com.stasuma.db.DataSource;
import com.stasuma.db.dao.RoutesStopsDao;
import com.stasuma.objects.Route;
import com.stasuma.objects.Stop;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RoutesStopsDaoImpl implements RoutesStopsDao {
    private static final Logger consoleLogger = Logger.getLogger("file");


    private final String SQL_INSERT = "INSERT INTO route_stop(route_id, stop_id) values (?,?)";

    private final DataSource dataSource = DataSource.getInstance();

    @Override
    public void add(Route route, Stop stop) {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            connection.setAutoCommit(false);
            try (PreparedStatement ps = connection.prepareStatement(SQL_INSERT)) {
                ps.setInt(1, route.getId());
                ps.setInt(2, stop.getId());
                ps.execute();
                consoleLogger.trace("Routes stops info successfully added.");
            }
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            dataSource.rollback(connection);
            consoleLogger.trace("Error during add routes stops info.");
        } finally {
            dataSource.closeConnection(connection);
        }
    }
}
