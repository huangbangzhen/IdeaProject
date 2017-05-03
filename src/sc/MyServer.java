package sc;

/**
 * Created by Administrator on 2017/4/26/026.
 */

import services.Info;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MyServer {

    private final static Logger logger = Logger.getLogger(MyServer.class.getName());

    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(12345);

        while (true) {
            Socket socket = server.accept();
            invoke(socket);
        }
    }

    private static void invoke(final Socket socket) throws IOException {
        new Thread(new Runnable() {
            public void run() {
                ObjectInputStream is = null;
                ObjectOutputStream os = null;
                try {
                    is = new ObjectInputStream(new BufferedInputStream(socket.getInputStream()));
                    os = new ObjectOutputStream(socket.getOutputStream());
                    ArrayList infomation=new Info().getinfo();
                   os.writeObject(infomation);
                   os.flush();
                } catch (IOException ex) {
                    logger.log(Level.SEVERE, null, ex);
                } finally {
                    try {
                        is.close();
                    } catch(Exception ex) {}
                    try {
                        os.close();
                    } catch(Exception ex) {}
                    try {
                        socket.close();
                    } catch(Exception ex) {}
                }
            }
        }).start();
    }
}