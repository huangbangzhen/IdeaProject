package entity;

/**
 * Created by Administrator on 2017/5/5 0005.
 */
public class mac {
    private String Hwaddr;

    public String getHwaddr() {
        return Hwaddr;
    }

    public void setHwaddr(String hwaddr) {
        Hwaddr = hwaddr;
    }
    public String toString(){
        return this.Hwaddr;
    }
}
