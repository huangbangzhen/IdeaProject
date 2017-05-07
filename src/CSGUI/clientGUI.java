package CSGUI;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import entity.*;
import services.reflect;
import util.SQLHelper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Administrator on 2017/4/17/017.
 */
public class clientGUI {
    public clientGUI() {
        getButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    getinfo(e);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });
        save.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    saveinfo(e);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });
        search.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JFrame frame = new JFrame("test");
                frame.setContentPane(new test().date);
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.pack();
                frame.setVisible(true);
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("clientGUI");
        frame.setContentPane(new clientGUI().sever);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }


    //获取信息斌显示在textarea区域
//获取信息模块
    public ArrayList infomation;

    private void serch(MouseEvent e) {
        new test();
    }

    private void getinfo(MouseEvent e) throws IOException {
        int port;
        String IP;
        info.setText("");
        IP = ip.getText();
        port = Integer.parseInt(Port.getText());//转换为int型
        Socket socket = new Socket(IP, port);//创建套接字s
        winfo(socket);
    }

    private void winfo(Socket socket) throws IOException {
        new Thread(new Runnable() {
            public void run() {
                ObjectInputStream is = null;
                ObjectOutputStream os = null;
                try {
                    os = new ObjectOutputStream(socket.getOutputStream());
                    is = new ObjectInputStream(new BufferedInputStream(socket.getInputStream()));
                    infomation = (ArrayList) is.readObject();
                    Memory memory = (Memory) infomation.get(0);
                    Os os1 = (Os) infomation.get(1);
                    Who who = (Who) infomation.get(2);
                    ArrayList<CPU> cpuarray = (ArrayList<CPU>) infomation.get(3);
                    ArrayList<Disk> diskarray = (ArrayList<Disk>) infomation.get(4);
                    Net net = (Net) infomation.get(5);
                    StringBuilder string = new StringBuilder();
                    string.append("\n**************************");
                    string.append("memory\n");
                    string.append(new reflect().getreflect(memory));
                    string.append("\n**************************");
                    string.append("OS\n");
                    string.append(new reflect().getreflect(os1));
                    string.append("\n**************************");
                    string.append("WHO\n");
                    string.append(new reflect().getreflect(who));
                    string.append("\n**************************");
                    string.append("NET\n");
                    string.append(new reflect().getreflect(net));
                    string.append("\n**************************");
                    string.append("DISK\n");
                    for (int i = 0; i < diskarray.size(); i++) {
                        string.append(new reflect().getreflect(diskarray.get(i)));
                    }
                    string.append("\n**************************");
                    string.append("CPU\n");
                    for (int i = 0; i < cpuarray.size(); i++) {
                        string.append(new reflect().getreflect(cpuarray.get(i)));
                    }
                    string.append(new reflect().getreflect(cpuarray));
                    string.append("\n**************************");
                    info.setText(string.toString());
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        is.close();
                        os.close();
                        socket.close();
                    } catch (Exception ex) {
                    }
                }
            }
        }).start();
    }


    //存储模块,获取数据后存进数据库

    private void saveinfo(MouseEvent e) throws IOException {
        Memory memory = (Memory) infomation.get(0);
        Os os = (Os) infomation.get(1);
        Who who = (Who) infomation.get(2);
        ArrayList<CPU> cpuarray = (ArrayList<CPU>) infomation.get(3);
        ArrayList<Disk> diskarray = (ArrayList<Disk>) infomation.get(4);
        Net net = (Net) infomation.get(5);
        SQLHelper sqLhelper = new SQLHelper();
        sqLhelper.getConnection();
        Date date = new Date();//获得系统时间.
        String nowTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);//将时间格式转换成符合Timestamp要求的格式.
        Timestamp dates = Timestamp.valueOf(nowTime);//把时间转换
        String sql[] = {
                "INSERT INTO Memory (total,free,used,usedper,time,Hwaddr) VALUES (?,?,?,?,?,?)",
                "INSERT INTO Os (name,ip,Kernel,Vendorname,Vendor,DataModel,Version,time,Hwaddr) VALUES (?,?,?,?,?,?,?,?,?)",
                "INSERT INTO Who (UserNmae,Name,IP,UserDomain,time,Hwaddr) VALUES (?,?,?,?,?,?)",
                "INSERT INTO Net (Name,IP,Broadcast,Hwaddr,Netmask,Descrip,Type,time) VALUES (?,?,?,?,?,?,?,?)",
                "INSERT IGNORE INTO mac (Name,Hwaddr) VALUES (?,?)"
        };
        String[][] parameters = {
                {String.valueOf(memory.getTotal()), String.valueOf(memory.getFree()), String.valueOf(memory.getUsed()), String.valueOf(memory.getUsedper()), String.valueOf(dates), net.getHwaddr()},
                {os.getName(), os.getIp(), os.getKernel(), os.getVendorname(), os.getVendor(), os.getDataModel(), os.getVersion(), String.valueOf(dates), net.getHwaddr()},
                {who.getUserNmae(), who.getName(), who.getIP(), who.getUserDomain(), String.valueOf(dates), net.getHwaddr()},
                {net.getName(), net.getIP(), net.getBroadcast(), net.getHwaddr(), net.getNetmask(), net.getDescrip(), net.getType(), String.valueOf(dates)},
                {net.getName(), net.getHwaddr()}
        };
        sqLhelper.executeUpdateMultiParams(sql, parameters);
        String sqlc = "INSERT INTO CPU (num,MHZ,Vendor,Model,Usedper,time,Hwaddr) VALUES (?,?,?,?,?,?,?)";
        String sqld = "INSERT INTO Disk (Num,DevName,SysTypeName,total,Free,Usedper,time,Hwaddr) VALUES (?,?,?,?,?,?,?,?)";
        for (int i = 0; i < cpuarray.size(); i++) {
            CPU cpu = cpuarray.get(i);
            String[] parac = {String.valueOf(cpu.getnum()), String.valueOf(cpu.getMHZ()), cpu.getVendor(), cpu.getModel(), String.valueOf(cpu.getUsedper()), String.valueOf(dates), net.getHwaddr()};
            sqLhelper.executeUpdate(sqlc, parac);
        }
        for (int i = 0; i < diskarray.size(); i++) {
            Disk disk = diskarray.get(i);
            String[] parad = {String.valueOf(disk.getNum()), disk.getDevName(), disk.getSysTypeName(), String.valueOf(disk.getTotal()), String.valueOf(disk.getFree()), String.valueOf(disk.getUsedper()), String.valueOf(dates), net.getHwaddr()};
            sqLhelper.executeUpdate(sqld, parad);
        }
        JOptionPane.showInternalMessageDialog(sever, "save success", "save success", JOptionPane.INFORMATION_MESSAGE);

    }


    JTextField ip;
    private JTextField Port;
    private JButton getButton;
    private JTextArea info;
    private JButton save;
    private JButton search;


    private JPanel sever;

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        sever = new JPanel();
        sever.setLayout(new GridLayoutManager(2, 5, new Insets(0, 0, 0, 0), -1, -1));
        sever.setName("manage");
        sever.setPreferredSize(new Dimension(600, 450));
        sever.setToolTipText("manage");
        final JLabel label1 = new JLabel();
        label1.setText("IP");
        sever.add(label1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        ip = new JTextField();
        ip.setText("localhost");
        sever.add(ip, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(100, -1), null, 0, false));
        final JLabel label2 = new JLabel();
        label2.setText("port");
        sever.add(label2, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        Port = new JTextField();
        Port.setText("10000");
        sever.add(Port, new GridConstraints(0, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(100, -1), null, 0, false));
        getButton = new JButton();
        getButton.setText("get");
        sever.add(getButton, new GridConstraints(0, 4, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new GridLayoutManager(2, 1, new Insets(0, 0, 0, 0), -1, -1));
        sever.add(panel1, new GridConstraints(1, 4, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        save = new JButton();
        save.setText("save");
        panel1.add(save, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        search = new JButton();
        search.setText("search");
        panel1.add(search, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JScrollPane scrollPane1 = new JScrollPane();
        sever.add(scrollPane1, new GridConstraints(1, 0, 1, 4, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        info = new JTextArea();
        info.setLineWrap(true);
        scrollPane1.setViewportView(info);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return sever;
    }
}
