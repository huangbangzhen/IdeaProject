package sc;

        import entity.CPU;
        import entity.Net;

        import java.io.BufferedInputStream;
        import java.io.IOException;
        import java.io.ObjectInputStream;
        import java.io.ObjectOutputStream;
        import java.net.Socket;
        import java.util.ArrayList;
        import java.util.logging.Level;
        import java.util.logging.Logger;

        import static entity.cpuarray.CPUarray;

public class MyClient {

    private final static Logger logger = Logger.getLogger(MyClient.class.getName());

    public static void main(String[] args) throws Exception {
            Socket socket = null;
            ObjectOutputStream os = null;
            ObjectInputStream is = null;

            try {
                socket = new Socket("localhost", 12345);

                os = new ObjectOutputStream(socket.getOutputStream());
                is = new ObjectInputStream(new BufferedInputStream(socket.getInputStream()));
                ArrayList infomation= (ArrayList)is.readObject();
                Net net= (Net) infomation.get(5);
                System.out.println(net.getNetmask());
                System.out.println(net.getBroadcast()+"-----"+net.getDescrip()+"-----"+net.getHwaddr()+"-----"+net.getIP()+"-----"+net.getName()+"-----"+net.getNetmask()+"-----"+net.getType());
                ArrayList<CPU> acpu= (ArrayList<CPU>) infomation.get(3);//CPU信息用集合存
                CPU n = acpu.get(0);
                System.out.println(n.getMHZ());
            } catch(IOException ex) {
                logger.log(Level.SEVERE, null, ex);
            } finally {
                try {
                    is.close();
                } catch (Exception ex) {
                }
                try {
                    os.close();
                } catch (Exception ex) {
                }
                try {
                    socket.close();
                } catch (Exception ex) {
                }
            }
    }
}