package org.sang.dao;

import org.sang.bean.OptionInfo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OptionInfoDao {
    public int justifyupdate(int empid, int mid) {
        if (isUpdate(empid, mid)) {
            return -1;
        } else {
            return 1;
        }
    }
    //上传参会人员填写的信息
    public int update(int empid, String IDnumber,String gender,String location, String roomrequired, int mid) {
        if (isUpdate(empid, mid)) {
            return -1;
        }
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = DBUtils.getConnection();
            ps = con.prepareStatement("INSERT INTO optioninfo(employeeid,IDnumber,gender,location,roomrequired,meetingid) VALUES (?,?,?,?,?,?)");
            ps.setInt(1, empid);
            ps.setString(2, IDnumber);
            ps.setString(3, gender);
            ps.setString(4, location);
            ps.setString(5, roomrequired);
            ps.setInt(6, mid);
            return ps.executeUpdate();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.close(ps);
            DBUtils.close(con);
        }
        return 0;
    }

    //得到参会人员填写的参会信息
    public List<OptionInfo> getOptionInfo(String[] empsid, int mid) {
        List<OptionInfo> list = new ArrayList<>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = DBUtils.getConnection();
            ps = con.prepareStatement("select * from optioninfo WHERE employeeid=? AND meetingid=?");
            for (String empid : empsid) {
                ps.setInt(1, Integer.parseInt(empid));
                ps.setInt(2, mid);
                rs = ps.executeQuery();
                if(rs.next()){
                    while (rs.next()) {
                        list.add(new OptionInfo(rs.getString("IDnumber"), rs.getString("gender"), rs.getString("location"), rs.getString("roomrequired")));
                        System.out.println("rs: "+list);
                    }
                } else {
                    list.add(new OptionInfo(null, null, null, null));
                }
            }
            System.out.println("optioninfodao: "+list);
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


    public boolean isUpdate(int empid, int mid) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = DBUtils.getConnection();
            ps = con.prepareStatement("SELECT * from optioninfo WHERE employeeid=? AND meetingid=?");
            ps.setInt(1, empid);
            ps.setInt(2, mid);
            rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.close(rs);
            DBUtils.close(ps);
            DBUtils.close(con);
        }
        return false;
    }
}
