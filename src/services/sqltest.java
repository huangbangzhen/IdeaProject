package services;

import util.SQLHelper;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 2017/5/2/002.
 */
public class sqltest {
    public static void main(String[] args) {
        SQLHelper mysql =new SQLHelper();
        Date date = new Date();//���ϵͳʱ��.
        String nowTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);//��ʱ���ʽת���ɷ���TimestampҪ��ĸ�ʽ.
        Timestamp dates =Timestamp.valueOf(nowTime);//��ʱ��ת��
        String sql = "insert into test(name,password,time) values (?,?,?)";
        String[] parameters = { "huang", "12345", String.valueOf(dates)};
        mysql.executeUpdate(sql,parameters);
    }
}
