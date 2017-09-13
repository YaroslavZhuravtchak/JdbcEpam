//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.zhuravchak.dao.jdbc;

import com.zhuravchak.dao.DepartmentDAO;
import com.zhuravchak.entity.Department;
import com.zhuravchak.entity.Entity;
import com.zhuravchak.pool.ConnectionPool;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DepartmentDAOJdbc implements DepartmentDAO {

    private final static Logger LOG = Logger.getLogger(DepartmentDAO.class);
    /**
     * Instantiates a new JdbcUserDAO.
     */
    private DepartmentDAOJdbc() {
    }

    /** Nested class JdbcUserDAOHolder. */
    private static class DepartmentDAOJdbcHolder {
        private static final DepartmentDAOJdbc HOLDER_INSTANCE = new DepartmentDAOJdbc();
    }

    /**Gets the instance. */
    public static DepartmentDAOJdbc getInstance() {
        return DepartmentDAOJdbcHolder.HOLDER_INSTANCE;
    }

    @Override
    public boolean add(Department department){

        boolean result = false;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        String sql = "INSERT INTO departments (department_name, phone_number) VALUES(?, ?)";

        try {
            connection = ConnectionPool.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, department.getName());
            preparedStatement.setString(2, department.getPhoneNumber());
            preparedStatement.executeUpdate();
            LOG.debug("Department added");
            result = true;
        } catch (SQLException e) {
            e.printStackTrace();
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
    public boolean addForEntity(Entity entity, String str) {
        return false;
    }

    @Override
    public List<Department> getAll() {
        ArrayList departmentList = new ArrayList();
        String sql = "SELECT department_id, department_name, phone_number FROM departments";
        Statement statement = null;
        Connection connection = null;

        try {
            connection = ConnectionPool.getConnection();
            statement = connection.createStatement();
            ResultSet e = statement.executeQuery(sql);

            while(e.next()) {
                Department department = new Department();
                department.setId(e.getLong("department_id"));
                department.setName(e.getString("department_name"));
                department.setPhoneNumber(e.getString("phone_number"));
                departmentList.add(department);
            }
        } catch (SQLException e) {
            e.printStackTrace();
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
        return departmentList;
    }

    @Override
    public List<Department> getAllForEntity(Entity entity) {
        return null;
    }

    @Override
    public Department getById(Long ID) {
        PreparedStatement preparedStatement = null;
        String sql = "SELECT department_id, department_name, phone_number FROM departments WHERE department_id=?";
        Department department = null;
        Connection connection = null;

        try {
            connection = ConnectionPool.getConnection();
            department = new Department();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, ID.longValue());
            ResultSet e = preparedStatement.executeQuery();
            e.next();
            department.setId(e.getLong("department_id"));
            department.setName(e.getString("department_name"));
            department.setPhoneNumber(e.getString("phone_number"));
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
        return department;
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }

    @Override
    public boolean delete(Department entity) {
        return false;
    }

    @Override
    public boolean deleteForEntity(Entity entity) {
        return false;
    }
}
