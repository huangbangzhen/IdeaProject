package entity;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/4/20/020.
 */
public class CPU implements Serializable{
public int num;
private  int MHZ;
private String Vendor;
private String Model;
private double Usedper;
public CPU() {
    super();
}

    public int getnum() {
        return num;
    }

    public void setnum(int num) {
        this.num = num;
    }

    public int getMHZ() {
        return MHZ;
    }

    public void setMHZ(int MHZ) {
        this.MHZ = MHZ;
    }

    public String getVendor() {
        return Vendor;
    }

    public void setVendor(String vendor) {
        Vendor = vendor;
    }

    public String getModel() {
        return Model;
    }

    public void setModel(String model) {
        Model = model;
    }

    public double getUsedper() {
        return  Usedper;
    }

    public void setUsedper(double usedper) {
        Usedper = usedper;
    }
}
