package entity;

import org.hyperic.sigar.OperatingSystem;

import java.io.Serializable;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Map;

/**
 * Created by Administrator on 2017/4/20/020.
 */
public class Os implements Serializable {
    private String name;
    private String ip;
    private String Kernel;
    private String Vendorname;
    private String Vendor;
    private String DataModel;
    private String Version;
    public Os(){
        InetAddress addr;
        try {
            addr = InetAddress.getLocalHost();
            String IP = addr.getHostAddress();
            this.ip=IP;
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        Map<String, String> map = System.getenv();
        String computerName = map.get("COMPUTERNAME");// 获取计算机名
        this.name =computerName;
        OperatingSystem OS = OperatingSystem.getInstance();
        this.DataModel= OS.getDataModel();
        this.Kernel= OS.getArch();
        this.Vendor= OS.getVendor();
        this.Vendorname= OS.getVendorName();
        this.Version= OS.getVersion();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getKernel() {
        return Kernel;
    }

    public void setKernel(String kernel) {
        Kernel = kernel;
    }

    public String getVendorname() {
        return Vendorname;
    }

    public void setVendorname(String vendorname) {
        Vendorname = vendorname;
    }

    public String getVendor() {
        return Vendor;
    }

    public void setVendor(String vendor) {
        Vendor = vendor;
    }

    public String getDataModel() {
        return DataModel;
    }

    public void setDataModel(String dataModel) {
        DataModel = dataModel;
    }

    public String getVersion() {
        return Version;
    }

    public void setVersion(String version) {
        Version = version;
    }
}
