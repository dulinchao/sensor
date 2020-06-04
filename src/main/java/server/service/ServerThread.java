package server.service;

import org.apache.ibatis.session.SqlSession;
import server.dao.SensorMapper;
import server.pojo.SensorData;
import server.utils.MybatisUtils;

import java.io.*;
import java.net.Socket;

public class ServerThread implements Runnable{
    private Socket socket;
    private InputStream inputStream;
    private ObjectInputStream objectInputStream;
    private SqlSession sqlSession;
    private SensorMapper sensorMapper;
    public ServerThread(Socket socket) throws IOException {
        this.socket = socket;
        inputStream = socket.getInputStream();
        objectInputStream = new ObjectInputStream(inputStream);

        //第一步：获得SqlSession对象
        sqlSession = MybatisUtils.getSqlSession();
        //方式一，getMapper：执行sql
        sensorMapper = sqlSession.getMapper(SensorMapper.class);
    }

    public void run() {
        SensorData sensorData;
        while((sensorData = readFromClient())!=null){
            System.out.println(sensorData);
            sensorMapper.insertInfo(sensorData);
            sqlSession.commit();
        }
        try {
            objectInputStream.close();
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private SensorData readFromClient(){
        try {
            SensorData data = (SensorData) objectInputStream.readObject();
            //当socketMap中没有此Socket时，放进去
            if(data!=null && !Main.socketMap.containsKey(data.getName())){
                Main.socketMap.put(data.getName(),socket);
            }
            return data;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
