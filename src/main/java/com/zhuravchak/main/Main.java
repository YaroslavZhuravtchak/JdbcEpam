package com.zhuravchak.main;

import com.zhuravchak.entity.Department;
import com.zhuravchak.entity.Employee;
import com.zhuravchak.dao.jdbc.DepartmentDAOJdbc;
import com.zhuravchak.dao.jdbc.EmployeeDAOJdbc;
import com.zhuravchak.dao.jdbc.TaskDAOJdbc;
import com.zhuravchak.pool.ConnectionPool;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Yaroslav on 05-Sep-17.
 */
public class Main {
    public static void main(String[] args) throws SQLException {

        EmployeeDAOJdbc employeeService = EmployeeDAOJdbc.getInstance();
        TaskDAOJdbc taskService = TaskDAOJdbc.getInstance();
        DepartmentDAOJdbc departmentJdbc = DepartmentDAOJdbc.getInstance();

        List employeeList = employeeService.getAll();
        System.out.println(employeeList);


        List taskList = taskService.getAll();
        System.out.println(taskList + "\n");

        Department department = departmentJdbc.getById(2L);
        employeeList = employeeService.getAllForEntity(department);
        System.out.println(employeeList + "\n");

        Employee employee = employeeService.getById(5L);
        List taskList1 = taskService.getAllForEntity(employee);
        System.out.println(taskList1);

        Employee employee2 = employeeService.getById(3L);
        System.out.println(employeeService.delete(employee2));

        ConnectionPool.destroy();
    }
}
