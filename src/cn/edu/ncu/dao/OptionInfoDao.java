package cn.edu.ncu.dao;

import cn.edu.ncu.bean.OptionInfo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
    public OptionInfo getOptionInfoByEmpid(int empsid, int mid) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = DBUtils.getConnection();
            ps = con.prepareStatement("select * from optioninfo WHERE employeeid=? AND meetingid=?");
                ps.setInt(1, empsid);
                ps.setInt(2, mid);
                rs = ps.executeQuery();
                while (rs.next()) {
                    return new OptionInfo(rs.getString("IDnumber"), rs.getString("gender"), rs.getString("location"), rs.getString("roomrequired"));
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
        return null;
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
