package com.zhuravchak.dao.jdbc;

import com.zhuravchak.dao.EmployeeDAO;
import com.zhuravchak.entity.Department;
import com.zhuravchak.entity.Employee;
import com.zhuravchak.pool.ConnectionPool;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAOJdbc implements EmployeeDAO {

    private final static Logger LOG = Logger.getLogger(EmployeeDAO.class);

    /**
     * Instantiates a new JdbcUserDAO.
     */
    private EmployeeDAOJdbc() {
    }

    /** Nested class JdbcUserDAOHolder. */
    private static class EmployeeDAOJdbcHolder {
        private static final EmployeeDAOJdbc HOLDER_INSTANCE = new EmployeeDAOJdbc();
    }

    /**Gets the instance. */
    public static EmployeeDAOJdbc getInstance() {
        return EmployeeDAOJdbcHolder.HOLDER_INSTANCE;
    }

    @Override
    public boolean add(Employee entity) {
        return false;
    }

    @Override
    public boolean addForEntity(Department entity, String str) {
        return false;
    }

    @Override
    public List<Employee> getAll() {
        ArrayList employeeList = new ArrayList();
        String sql = "SELECT employee_id, lastnane, firstname, position, department_id FROM employees";
        Statement statement = null;
        Connection connection = null;

        try {
            connection = ConnectionPool.getConnection();
            statement = connection.createStatement();
            ResultSet e = statement.executeQuery(sql);

            while(e.next()) {
                Employee employee = new Employee();
                employee.setId(e.getLong("employee_id"));
                employee.setLastname(e.getString("lastnane"));
                employee.setFirstname(e.getString("firstname"));
                employee.setPosition(e.getString("position"));
                employee.setDepartment_id(e.getLong("department_id"));
                employeeList.add(employee);
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
        return employeeList;
    }

    @Override
    public List<Employee> getAllForEntity(Department department){
        ArrayList employeeList = new ArrayList();
        String sql = "SELECT employee_id, lastnane, firstname, position, department_id FROM employees WHERE department_id = ?";
        PreparedStatement preparedStatement = null;
        Connection connection = null;

        try {
            connection = ConnectionPool.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, department.getId());
            ResultSet e = preparedStatement.executeQuery();

            while(e.next()) {
                Employee employee = new Employee();
                employee.setId(e.getLong("employee_id"));
                employee.setLastname(e.getString("lastnane"));
                employee.setFirstname(e.getString("firstname"));
                employee.setPosition(e.getString("position"));
                employee.setDepartment_id(e.getLong("department_id"));
                employeeList.add(employee);
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
        return employeeList;
    }

    @Override
    public Employee getById(Long ID) {
        PreparedStatement preparedStatement = null;
        String sql = "SELECT employee_id, lastnane, firstname, position, department_id FROM employees WHERE employee_id = ?";
        Employee employee = null;
        Connection connection = null;

        try {
            employee = new Employee();
            connection = ConnectionPool.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, ID.longValue());
            ResultSet e = preparedStatement.executeQuery();
            e.next();
            employee.setId(e.getLong("employee_id"));
            employee.setLastname(e.getString("lastnane"));
            employee.setFirstname(e.getString("firstname"));
            employee.setPosition(e.getString("position"));
            employee.setDepartment_id(e.getLong("department_id"));
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
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
        return employee;
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }

    @Override
    public boolean delete(Employee employee) {
        boolean result = false;
        (TaskDAOJdbc.getInstance()).deleteForEntity(employee);
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        String sql = "DELETE FROM employees WHERE employee_id = ?";

        try {
            connection = ConnectionPool.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, employee.getId());
            preparedStatement.executeUpdate();
            LOG.debug("Employee deleted");
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

    @Override
    public boolean deleteForEntity(Department entity) {
        return false;
    }
}
