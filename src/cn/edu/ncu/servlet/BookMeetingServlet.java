package cn.edu.ncu.servlet;

import cn.edu.ncu.bean.Option;
import cn.edu.ncu.service.OptionService;
import cn.edu.ncu.bean.MeetingRoom;
import cn.edu.ncu.service.MeetingRoomService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by sang on 2017/8/21.
 */
public class BookMeetingServlet extends HttpServlet {
    private MeetingRoomService meetingRoomService = new MeetingRoomService();
    private OptionService optionService = new OptionService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json;charset=utf-8");
        List<MeetingRoom> mrs = meetingRoomService.getAllMeetingRoom();
        req.setAttribute("mrs", mrs);
        List<Option> oplists = optionService.getOptions();
        req.setAttribute("oplists", oplists);
        req.getRequestDispatcher("/bookmeeting.jsp").forward(req, resp);
    }
}
