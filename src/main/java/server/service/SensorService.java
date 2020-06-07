package server.service;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;
import server.dao.SensorMapper;
import server.pojo.SensorData;
import server.utils.MybatisUtils;

import java.io.IOException;
import java.net.Socket;
import java.util.List;

@Service
public class SensorService {

    public void stopSensor(String name){
        Socket socket = Main.socketMap.get(name);
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
