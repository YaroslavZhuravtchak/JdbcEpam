package com.zhuravchak.dao;

import com.zhuravchak.entity.Department;
import com.zhuravchak.entity.Entity;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Yaroslav on 05-Sep-17.
 */

public interface DepartmentDAO extends AbstractDAO<Long, Department, Entity>{}
