package entity;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/4/20/020.
 */
public class Disk implements Serializable {
    private int Num;
    private String DevName;
    private String SysTypeName;
    private int total;
    private int Free;
    private double Usedper;
    public Disk(){

    }
    public int getNum() {
        return Num;
    }

    public void setNum(int num) {
        Num = num;
    }

    public String getDevName() {
        return DevName;
    }

    public void setDevName(String devName) {
        DevName = devName;
    }

    public String getSysTypeName() {
        return SysTypeName;
    }

    public void setSysTypeName(String sysTypeName) {
        SysTypeName = sysTypeName;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getFree() {
        return Free;
    }

    public void setFree(int free) {
        Free = free;
    }

    public double getUsedper() {
        return Usedper;
    }

    public void setUsedper(double usedper) {
        Usedper = usedper;
    }
}
