package org.demo.home_03.db;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.demo.home_03.config.AppConfig;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author dshvedchenko on 20.05.16.
 */
public enum DataSource {
    INSTANCE;

    private ComboPooledDataSource cdps = null;
    private AppConfig config = AppConfig.Instance;

    private DataSource instance = null;

    private DataSource() {
        cdps = new ComboPooledDataSource();
        try {
            cdps.setDriverClass(config.dbClassName);
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        }
        cdps.setJdbcUrl(config.url);
        cdps.setUser(config.username);
        cdps.setPassword(config.password);
        cdps.setInitialPoolSize(15);
        cdps.setMinPoolSize(config.connPoolSize);
        cdps.setMaxConnectionAge(600);
        cdps.setDataSourceName("AUDIO_SHOP");

    }


    public Connection getConnection() throws SQLException {
        Connection connection = null;
        connection = cdps.getConnection();
        return connection;
    }

    public void destroy() {
        cdps.close();
    }
}
