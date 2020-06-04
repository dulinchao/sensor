package client;

import server.pojo.SensorData;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Random;

public class ClientThread implements Runnable{
    private Socket socket;
    private String address;
    private int port;
    private String name;

    public ClientThread(String address, int port, String name) {
        this.address = address;
        this.port = port;
        this.name = name;
    }

    public void run() {
        try {
            socket = new Socket(address,port);
            OutputStream outputStream = socket.getOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);

            Random random = new Random();
            for (int i = 0; i < 1; i++) {
                objectOutputStream.writeObject(new SensorData(name,random.nextInt(10)+20,random.nextInt(50)+50,random.nextDouble()+0.5,random.nextInt(5)+10));
                objectOutputStream.flush();
                Thread.currentThread().sleep(2000);
            }

            objectOutputStream.writeObject(null); //这句是为了让服务端知道客户端已经输出完了，并且要关闭输出流
            objectOutputStream.close();
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
