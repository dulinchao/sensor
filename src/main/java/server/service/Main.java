package server.service;

import server.pojo.SensorData;
import server.utils.SocketUtils;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Main implements Runnable{
    //存储所有socket的map，key为传感器名，value为对应socket
    public static ConcurrentHashMap<String,Socket> socketMap = new ConcurrentHashMap<String, Socket>();
    //存储所有最新数据的map，key为传感器名，value为对应SensorData
    public static ConcurrentHashMap<String,SensorData> dataMap = new ConcurrentHashMap<String,SensorData>();

    public void run() {
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
