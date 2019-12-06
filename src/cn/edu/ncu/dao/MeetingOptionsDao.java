package cn.edu.ncu.dao;

import cn.edu.ncu.bean.Option;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MeetingOptionsDao {
    public void insert(int meetingid, String[] optionids) {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = DBUtils.getConnection();
            ps = con.prepareStatement("insert into meetingoptions (meetingid, optionid) values (?,?);");
            for (String optid : optionids) {
                ps.setInt(1, meetingid);
                ps.setInt(2, Integer.parseInt(optid));
                ps.addBatch();
            }
            ps.executeBatch();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.close(ps);
            DBUtils.close(con);
        }
    }

    //通过会议ID得到对应的option选项
    public List<Option> getOptionsByMeetingId(int mid) {
        List<Option> list = new ArrayList<>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = DBUtils.getConnection();
            ps = con.prepareStatement("SELECT * FROM alloption WHERE optionid IN(SELECT optionid FROM meetingoptions WHERE meetingid=?)");
            ps.setInt(1,mid);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Option(rs.getInt("optionid"), rs.getString("option")));
            }
            return list;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.close(rs);
            DBUtils.close(ps);
            DBUtils.close(con);
        }
        return list;
    }
}
