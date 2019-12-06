package cn.edu.ncu.service;

import cn.edu.ncu.bean.Option;
import cn.edu.ncu.bean.OptionInfo;
import cn.edu.ncu.dao.OptionDao;
import cn.edu.ncu.dao.OptionInfoDao;

import java.util.List;

public class OptionService {
    private OptionDao optionDao = new OptionDao();
    private OptionInfoDao optioninfoDao = new OptionInfoDao();

    public List<Option> getOptions() {
        return optionDao.getOptions();
    }
    public int update(int empid, String IDnumber,String gender,String location, String roomrequired, int mid) {
        return optioninfoDao.update(empid, IDnumber, gender, location, roomrequired, mid);
    }
    public int justifyupdate(int empid, int mid) {
        return optioninfoDao.justifyupdate(empid, mid);
    }
    public OptionInfo getOptionInfoByEmpid(int empsid, int mid) {
        return optioninfoDao.getOptionInfoByEmpid(empsid, mid);
    }
}
