package cn.edu.ncu.bean;

public class Option {
    private int optionid;
    private String option;

    public Option(int optionid, String option) {
        this.optionid = optionid;
        this.option = option;
    }

    public String getOption() {
        return option;
    }

    public void setOption(String option) {
        this.option = option;
    }

    public int getOptionid() {
        return optionid;
    }

    public void setOptionid(int optionid) {
        this.optionid = optionid;
    }
}
