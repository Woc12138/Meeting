package cn.edu.ncu.service;

import cn.edu.ncu.bean.Department;
import cn.edu.ncu.dao.DepartmentDao;

import java.util.List;

/**
 * Created by sang on 2017/8/17.
 */
public class DepartmentService {
    private DepartmentDao departmentDao = new DepartmentDao();

    public int updateDepById(String name, int id) {
        return departmentDao.updateDepById(name, id);
    }

    public List<Department> getAllDepartment() {
        return departmentDao.getAllDepartment();
    }

    public int deleteDepById(int id) {
        return departmentDao.deleteDepById(id);
    }

    public int insert(String name) {
        return departmentDao.insert(name);
    }
}
