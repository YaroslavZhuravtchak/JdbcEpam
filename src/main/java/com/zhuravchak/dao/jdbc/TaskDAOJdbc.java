package com.zhuravchak.dao.jdbc;

import com.zhuravchak.dao.TaskDAO;
import com.zhuravchak.entity.Employee;
import com.zhuravchak.entity.Task;
import com.zhuravchak.pool.ConnectionPool;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class TaskDAOJdbc implements TaskDAO {

    private final static Logger LOG = Logger.getLogger(TaskDAO.class);
    /**
     * Instantiates a new JdbcUserDAO.
     */
    private TaskDAOJdbc() {
    }

    /** Nested class JdbcUserDAOHolder. */
    private static class TaskDAOJdbcHolder {
        private static final TaskDAOJdbc HOLDER_INSTANCE = new TaskDAOJdbc();
    }

    /**Gets the instance. */
    public static TaskDAOJdbc getInstance() {
        return TaskDAOJdbcHolder.HOLDER_INSTANCE;
    }

    @Override
    public boolean add(Task entity) {
        return false;
    }

    @Override
    public boolean addForEntity (Employee employee, String description) {
        boolean result = false;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        String sql = "INSERT INTO tasks (description, employee_id) VALUES(?, ?)";

        try {
            connection = ConnectionPool.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, description);
            preparedStatement.setLong(2, employee.getId());
            preparedStatement.executeUpdate();
            LOG.debug("Task added");
            result = true;
        } catch (SQLException var9) {
            var9.printStackTrace();
        } finally {
            if(preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(connection != null) {
                ConnectionPool.closeConnection(connection);
            }
        }
        return result;
    }

    @Override
    public List<Task> getAll() {
        ArrayList taskList = new ArrayList();
        String sql = "SELECT task_id, description, employee_id FROM tasks";
        Statement statement = null;
        Connection connection = null;
        try {
            connection = ConnectionPool.getConnection();
            statement = connection.createStatement();
            ResultSet e = statement.executeQuery(sql);

            while(e.next()) {
                Task task = new Task();
                task.setId(e.getLong("task_id"));
                task.setDiscription(e.getString("description"));
                task.setEmployee_id(e.getLong("employee_id"));
                taskList.add(task);
            }
        } catch (SQLException var9) {
            var9.printStackTrace();
        } finally {
            if(statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(connection != null) {
                ConnectionPool.closeConnection(connection);
            }
        }
        return taskList;
    }

    @Override
    public List<Task> getAllForEntity(Employee employee) {
        ArrayList taskList = new ArrayList();
        String sql = "SELECT task_id, description, employee_id FROM tasks WHERE employee_id = ?";
        PreparedStatement preparedStatement = null;
        Connection connection = null;

        try {
            connection = ConnectionPool.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, employee.getId());
            ResultSet e = preparedStatement.executeQuery();

            while(e.next()) {
                Task task = new Task();
                task.setId(e.getLong("task_id"));
                task.setDiscription(e.getString("description"));
                task.setEmployee_id(e.getLong("employee_id"));
                taskList.add(task);
            }
        } catch (SQLException var10) {
            var10.printStackTrace();
        } finally {
            if(preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if(connection != null) {
                ConnectionPool.closeConnection(connection);
            }
        }
        return taskList;
    }

    @Override
    public Task getById(Long id) {
        return null;
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }

    @Override
    public boolean delete(Task entity) {
        return false;
    }

    @Override
    public boolean deleteForEntity (Employee employee){
        boolean result = false;
        PreparedStatement preparedStatement = null;
        String sql = "DELETE FROM tasks WHERE employee_id = ?";
        Connection connection = null;

        try {
            connection = ConnectionPool.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, employee.getId());
            preparedStatement.executeUpdate();
            LOG.debug("Task deleted");
            result = true;
        } catch (SQLException var8) {
            var8.printStackTrace();
        } finally {
            if(preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(connection != null) {
                ConnectionPool.closeConnection(connection);
            }
        }
        return result;
    }
}
