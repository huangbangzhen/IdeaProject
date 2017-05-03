package services;

import util.SQLHelper;

/**
 * Created by Administrator on 2017/5/2/002.
 */
public class sqltest {
    public static void main(String[] args) {
        SQLHelper mysql =new SQLHelper();
        String sql = "insert into test(name,password) values (?,?)";
        String[] parameters = { "huang", "12345" };
        mysql.executeUpdate(sql,parameters);

    }

}
