package server.utils;

import java.io.IOException;
import java.net.ServerSocket;

public class SocketUtils {
    private static ServerSocket serverSocket;

    /**
     * 使用单例模式获取一个ServerSocket
     * @return
     * @throws IOException
     */
    public synchronized static ServerSocket getServerSocket(int port) throws IOException {
        if(serverSocket==null){
            serverSocket = new ServerSocket(port);
        }
        return serverSocket;
    }

}
