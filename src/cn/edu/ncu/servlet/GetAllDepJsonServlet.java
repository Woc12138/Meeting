package cn.edu.ncu.servlet;

import com.google.gson.Gson;
import cn.edu.ncu.bean.Department;
import cn.edu.ncu.service.DepartmentService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by sang on 2017/8/21.
 */
public class GetAllDepJsonServlet extends HttpServlet {
    private DepartmentService departmentService = new DepartmentService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Department> list = departmentService.getAllDepartment();
        resp.setContentType("application/json;charset=utf-8");
        resp.getWriter().write(new Gson().toJson(list));
    }
}
