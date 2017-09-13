package com.zhuravchak.pool;

/**
 * Created by Yaroslav on 05-Sep-17.
 */
import org.apache.log4j.Logger;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.concurrent.ArrayBlockingQueue;

public class ConnectionPool {

    private final static Logger LOG = Logger.getLogger(ConnectionPool.class);
    private static final String KEY_RESOURCE_PATH = "src/main/resources/database.properties";
    private static final String KEY_DRIVER_NAME = "driver-name";
    private static final String KEY_CONNECTION_STRING = "connection-string";
    private static final String POOL_SIZE = "pool-size";
    private static ConnectionPool HOLDER_INSTANCE;
    private ArrayBlockingQueue<Connection> connectionQueue;

    private ConnectionPool () {
        try {
            makeConnection();
        } catch (ClassNotFoundException e) {
            LOG.error("Failed to register driver");
            e.printStackTrace();
        } catch (IOException e) {
            LOG.error("Failed to read database properties");
            e.printStackTrace();
        } catch (SQLException e) {
            LOG.error("Failed to get connection");
            e.printStackTrace();
        }
    }

    public static ConnectionPool getInstance() {
        if(HOLDER_INSTANCE==null){
            HOLDER_INSTANCE = new ConnectionPool();
            LOG.info("Creating a instance of  ConnectionPool");
        }
        return HOLDER_INSTANCE;
    }
    public static Connection getConnection() {
        Connection connection = null;
        try {
            connection = getInstance().connectionQueue.take();
        } catch (InterruptedException e) {
            LOG.error("Failed to take connection from pool.", e);
            e.printStackTrace();
        }
        return connection;
    }

    public static void closeConnection(Connection conn) {
        if (conn != null) {
            try {
                getInstance().connectionQueue.put(conn);
            } catch (InterruptedException e) {
                LOG.error("Failed to put connection to pool.", e);
                e.printStackTrace();
            }
        }
    }

    private void makeConnection() throws IOException, ClassNotFoundException, SQLException {
        Properties properties = new Properties();
        FileInputStream fis = new FileInputStream(KEY_RESOURCE_PATH);
        properties.load(fis);
        int poolSize = Integer.valueOf(properties.getProperty(POOL_SIZE));
        connectionQueue = new ArrayBlockingQueue<>(poolSize);
       // Class.forName(properties.getProperty(KEY_DRIVER_NAME));
        for (int i = 0; i < poolSize; i++) {
            Connection connection = DriverManager.getConnection(
                    properties.getProperty(KEY_CONNECTION_STRING),
                    properties.getProperty("user"),
                    properties.getProperty("password"));

            connectionQueue.offer(connection);
        }
        LOG.info("Connections was successfully created.");
    }
    public static void destroy() {
        int count = 0;
        for (Connection connection : getInstance().connectionQueue) {
            try {
                connection.close();
                count++;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        LOG.info("Connections in the amount of " + count + " pieces successfully closed.");
    }

}