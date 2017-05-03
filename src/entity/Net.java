package entity;

import org.hyperic.sigar.NetInterfaceConfig;
import org.hyperic.sigar.Sigar;
import org.hyperic.sigar.SigarException;

import java.io.Serializable;
import java.util.Map;

/**
 * Created by Administrator on 2017/4/20/020.
 */
public class Net implements Serializable {
    private String Name;
    private String IP;
    private String Broadcast;
    private String Hwaddr;
    private String Netmask;
    private String Descrip;
    private String Type;
    public  Net() {
        Map<String, String> map = System.getenv();
        String computerName = map.get("COMPUTERNAME");// 获取计算机名
        this.Name =computerName;
        Sigar sigar = null;
        sigar = new Sigar();
        try {
            String[] ifaces = sigar.getNetInterfaceList();
            NetInterfaceConfig cfg = sigar.getNetInterfaceConfig(ifaces[1]);
            this.IP=cfg.getAddress();// IP地址
            this.Broadcast=cfg.getBroadcast();// 网关广播地址
            this.Hwaddr=cfg.getHwaddr();// 网卡MAC地址
            this.Netmask=cfg.getNetmask();// 子网掩码
            this.Descrip=cfg.getDescription();// 网卡描述信息
            this.Type=cfg.getType();//
        } catch (SigarException e) {
            e.printStackTrace();
        }
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getIP() {
        return IP;
    }

    public void setIP(String IP) {
        this.IP = IP;
    }

    public String getBroadcast() {
        return Broadcast;
    }

    public void setBroadcast(String broadcast) {
        Broadcast = broadcast;
    }

    public String getHwaddr() {
        return Hwaddr;
    }

    public void setHwaddr(String hwaddr) {
        Hwaddr = hwaddr;
    }

    public String getDescrip() {
        return Descrip;
    }

    public void setDescrip(String descrip) {
        Descrip = descrip;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public String getNetmask() {
        return Netmask;
    }

    public void setNetmask(String netmask) {
        Netmask = netmask;
    }
}
