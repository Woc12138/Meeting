package cn.edu.ncu.servlet;

import cn.edu.ncu.bean.Employee;
import cn.edu.ncu.service.EmployeeService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by sang on 2017/8/18.
 */
public class ApproveaccountServlet extends HttpServlet {
    private EmployeeService employeeService = new EmployeeService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Employee> list = employeeService.getUnApproveaccount();
        req.setAttribute("list", list);
        req.getRequestDispatcher("/approveaccount.jsp").forward(req, resp);
    }
}
