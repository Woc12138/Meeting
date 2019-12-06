package cn.edu.ncu.service;

import cn.edu.ncu.bean.MeetingRoom;
import cn.edu.ncu.dao.MeetingRoomDao;

import java.util.List;

/**
 * Created by sang on 2017/8/18.
 */
public class MeetingRoomService {
    private MeetingRoomDao meetingRoomDao = new MeetingRoomDao();
    public int insert(MeetingRoom meetingRoom) {
        return meetingRoomDao.insert(meetingRoom);
    }
    public List<MeetingRoom> getAllMeetingRoom(){
        return meetingRoomDao.getAllMeetingRoom();
    }
    public int update(MeetingRoom meetingRoom) {
        return meetingRoomDao.update(meetingRoom);
    }
}
