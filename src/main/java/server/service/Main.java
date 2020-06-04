package server.service;

import server.utils.SocketUtils;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class Main {
    //存储所有socket的map，key为传感器名，value为对应socket
    public static ConcurrentHashMap<String,Socket> socketMap = new ConcurrentHashMap<String, Socket>();
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = SocketUtils.getServerSocket(6066);
            while(true){
                Socket socket = serverSocket.accept();
                new Thread(new ServerThread(socket)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
