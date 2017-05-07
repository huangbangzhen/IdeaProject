package util;

/**
 * Created by Administrator on 2017/5/6 0006.
 */
import java.awt.FlowLayout;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import javax.swing.JComboBox;
import javax.swing.JFrame;
public class Test extends JFrame {
    public Test() {
        Vector<String> items = new Vector<String>();
        String sql="SELECT * from mac limit 5";
        SQLHelper sqlHelper=new SQLHelper();
        ResultSet rs = null;
        try {
            rs = sqlHelper.executeQuery("SELECT * from mac limit 5");
            while (rs.next()) {
                items.add(rs.getString("Hwaddr")+rs.getString("id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        JComboBox comboBox = new JComboBox(items);
        this.setLayout(new FlowLayout());
        this.add(comboBox);
    }
    public static void main(String[] args) {
        Test t = new Test();
        t.setSize(200, 200);
        t.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        t.setVisible(true);
    }
}