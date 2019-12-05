package org.sang.servlet;

import org.sang.bean.Employee;
import org.sang.bean.Meeting;
import org.sang.bean.Option;
import org.sang.bean.OptionInfo;
import org.sang.service.MeetingService;
import org.sang.service.OptionService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by sang on 2017/8/23.
 */
public class MeetingDetailsServlet extends HttpServlet {
    private MeetingService meetingService = new MeetingService();
    private OptionService optionService = new OptionService();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int empid = ((Employee) req.getSession().getAttribute("loginUser")).getEmployeeid();
        String mid = req.getParameter("mid");
        String type = req.getParameter("type");

        //参会人员需要填写的信息框
        Meeting meeting = meetingService.getMeetingDetailsByMeetingId(Integer.parseInt(mid));
        List<Employee> emps = meetingService.getEmps();
        List<Option> oplists = meetingService.getOptions(Integer.parseInt(mid));
        req.setAttribute("oplists", oplists);
        req.setAttribute("mt", meeting);
        req.setAttribute("emps", emps);
        req.setAttribute("type", type);

        //从数据库拉取每个参会人员填写的信息
        List<OptionInfo> list = new ArrayList<>();
        for (int i = 0;i<emps.size();i++) {
            Employee emp = emps.get(i);
            int empsid = emp.getEmployeeid();
            System.out.println("servlet,empsid: "+empsid);
            OptionInfo info = optionService.getOptionInfoByEmpid(empsid, Integer.parseInt(mid));
            if(info != null) {
                list.add(info);
            } else {
                list.add(new OptionInfo(null,null,null,null));
            }
        }
        //打包成list在jsp循环
        List<OptionInfo> opinfo = list;
        req.setAttribute("opinfolists", opinfo);

        //判断该参会人员信息是否上传
        int status = optionService.justifyupdate(empid, Integer.parseInt(mid));
        if (status == -1) {
            req.setAttribute("error", "参会人员需填写的信息已上传");
        } else {
            req.setAttribute("error", null);
        }
        req.getRequestDispatcher("/meetingdetails.jsp").forward(req, resp);
    }
}
