package server.service;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import server.dao.SensorMapper;
import server.pojo.SensorData;
import server.utils.MybatisUtils;

import java.io.*;
import java.net.Socket;
import java.net.SocketException;

public class ServerThread implements Runnable{
    private Socket socket;
    private InputStream inputStream;
    private ObjectInputStream objectInputStream;
    private OutputStream outputStream;
    private ObjectOutputStream objectOutputStream;
    private SqlSession sqlSession;
    private SensorMapper sensorMapper;
    public static int flashTime;  //要求客户端的采集周期

    //初始化socket和流，并且得到连接数据库的mapper
    public ServerThread(Socket socket) throws IOException {
        this.socket = socket;
        inputStream = socket.getInputStream();
        objectInputStream = new ObjectInputStream(inputStream);
        outputStream = socket.getOutputStream();
        objectOutputStream = new ObjectOutputStream(outputStream);
        flashTime = 3000;
        //第一步：获得SqlSession对象
        sqlSession = MybatisUtils.getSqlSession();
        //方式一，getMapper：执行sql
        sensorMapper = sqlSession.getMapper(SensorMapper.class);
    }

    //run方法中试图从流中获取数据，获取到了就存储，最后当socket关闭后关闭流
    public void run() {
        SensorData sensorData;
        while((sensorData = readFromClient())!=null){
            String name = sensorData.getName();

            Main.dataMap.put(name,sensorData);

//            sensorMapper.insertInfo(sensorData);//写入数据库
            sqlSession.commit();
        }
        try {
            outputStream.close();
            objectInputStream.close();
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //从客户端读取SensorData
    private SensorData readFromClient(){
        try {
            SensorData data = (SensorData) objectInputStream.readObject();

            Integer time = flashTime;
            objectOutputStream.writeObject(time);

            //当socketMap中没有此Socket时，放进去
            if(data!=null && !Main.socketMap.containsKey(data.getName())){
                Main.socketMap.put(data.getName(),socket);
            }
            return data;
        } catch (SocketException e){

        } catch(IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

}
