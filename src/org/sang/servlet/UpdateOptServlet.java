package org.sang.servlet;

import org.sang.bean.Employee;
import org.sang.service.OptionService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UpdateOptServlet extends HttpServlet {
    private OptionService optionService = new OptionService();
    String IDnumber = null;
    String gender = null;
    String location = null;
    String roomrequired = null;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String[] optionid = req.getParameterValues("optionid");
        String[] optioninfo = req.getParameterValues("optioninfo");
        String mid = req.getParameter("mid");
        for (int i = 0; i < optionid.length; i++) {
            if (optionid[i].equals("1")) {
                IDnumber = optioninfo[i];
            } else if (optionid[i].equals("2")) {
                gender = optioninfo[i];
            } else if (optionid[i].equals("3")) {
                location = optioninfo[i];
            } else if (optionid[i].equals("4")) {
                roomrequired = optioninfo[i];
            }
        }
            int loginEmpId = ((Employee) req.getSession().getAttribute("loginUser")).getEmployeeid();
            int update = optionService.update(loginEmpId, IDnumber, gender, location, roomrequired, Integer.parseInt(mid));
            if (update == 1) {
                //上传成功，跳转到我的会议页面
                resp.sendRedirect(req.getContextPath() + "/mymeeting");
            } else if (update == -1) {
                //已经上传过了
                req.setAttribute("error", "参会人员需填写的信息已上传");
                req.getRequestDispatcher("/mymeeting").forward(req, resp);
            } else {
                req.setAttribute("error", "上传失败");
                req.getRequestDispatcher("/mymeeting").forward(req, resp);
            }
    }
}