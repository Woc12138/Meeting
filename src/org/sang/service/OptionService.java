package org.sang.service;

import org.sang.bean.Option;
import org.sang.bean.OptionInfo;
import org.sang.dao.OptionDao;
import org.sang.dao.OptionInfoDao;

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
