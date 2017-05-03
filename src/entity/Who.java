package entity;

import java.io.Serializable;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Map;

/**
 * Created by Administrator on 2017/4/20/020.
 */
public class Who implements Serializable {
    private String UserNmae;
    private String Name;
    private String IP;
    private String UserDomain;
    public Who(){
        InetAddress addr;
        try {
            addr = InetAddress.getLocalHost();
            String ip = addr.getHostAddress();
            this.IP=ip;
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        Map<String, String> map = System.getenv();
        String userName = map.get("USERNAME");// 获取用户名
        String computerName = map.get("COMPUTERNAME");// 获取计算机名
        String userDomain = map.get("USERDOMAIN");// 获取计算机域名
        this.UserNmae=userName;
        this.Name=computerName;
        this.UserDomain=userDomain;
    }


    public String getUserNmae() {
        return UserNmae;
    }

    public void setUserNmae(String userNmae) {
        UserNmae = userNmae;
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

    public String getUserDomain() {
        return UserDomain;
    }

    public void setUserDomain(String userDomain) {
        UserDomain = userDomain;
    }
}
