package cn.edu.ncu.service;

import cn.edu.ncu.bean.Option;
import cn.edu.ncu.dao.MeetingOptionsDao;
import cn.edu.ncu.bean.Employee;
import cn.edu.ncu.bean.Meeting;
import cn.edu.ncu.dao.EmployeeDao;
import cn.edu.ncu.dao.MeetingDao;
import cn.edu.ncu.dao.MeetingParticipantsDao;

import java.util.List;

/**
 * Created by sang on 2017/8/21.
 */
public class MeetingService {
    private MeetingDao meetingDao = new MeetingDao();
    private EmployeeDao employeeDao = new EmployeeDao();
    private MeetingOptionsDao meetingoptionsDao = new MeetingOptionsDao();
    private List<Employee> emps;
    private List<Option> options;

    public List<Employee> getEmps() {
        return emps;
    }
    public List<Option> getOptions(int i) {
        return options;
    }

    private MeetingParticipantsDao meetingParticipantsDao = new MeetingParticipantsDao();
    private MeetingOptionsDao meetingOptionsDao = new MeetingOptionsDao();

    public void insert(Meeting meeting, String[] empids, String[] optionids) {
        int insert = meetingDao.insert(meeting);
        meetingParticipantsDao.insert(insert, empids);
        meetingOptionsDao.insert(insert, optionids);
    }

    public List<Meeting> searchMeeting(String meetingname, String roomname, String reservername, String reservefromdate, String reservetodate, String meetingfromdate, String meetingtodate, int page, int count) {
        return meetingDao.searchMeeting(meetingname, roomname, reservername, reservefromdate, reservetodate, meetingfromdate, meetingtodate, page, count);
    }

    public int getCount(String meetingname, String roomname, String reservername, String reservefromdate, String reservetodate, String meetingfromdate, String meetingtodate) {
        return meetingDao.getCount(meetingname, roomname, reservername, reservefromdate, reservetodate, meetingfromdate, meetingtodate);
    }

    public Meeting getMeetingDetailsByMeetingId(int mid) {
        Meeting meeting = meetingDao.getMeetingById(mid);
        this.emps = employeeDao.getEmpByMeetingId(meeting.getMeetingid());
        this.options = meetingoptionsDao.getOptionsByMeetingId(meeting.getMeetingid());
        return meeting;
    }
    public List<Meeting> getMyBookingMeeting(int empid) {
        return meetingDao.getMyBookingMeeting(empid);
    }
    public int cancelMeeting(int mid, String reason) {
        return meetingDao.cancelMeeting(mid, reason);
    }
    public List<Meeting> getMyMeeting(int empid) {
        return meetingDao.getMyMeeting(empid);
    }

}
