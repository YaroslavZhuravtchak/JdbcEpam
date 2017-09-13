package com.zhuravchak.dao;

import com.zhuravchak.entity.Employee;
import com.zhuravchak.entity.Task;


/**
 * Created by Yaroslav on 05-Sep-17.
 */
public interface TaskDAO extends AbstractDAO<Long, Task, Employee>{}