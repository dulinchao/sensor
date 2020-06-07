package server.service;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import server.dao.SensorMapper;
import server.pojo.SensorData;
import server.utils.MybatisUtils;

import java.io.IOException;
import java.net.Socket;
import java.util.List;

@Service
public class SensorService {
    /**
     * 停止某个socket
     * @param name
     */
    public void stopSensor(String name){
        Socket socket = Main.socketMap.get(name);
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<SensorData> getHistory(String name){
        //第一步：获得SqlSession对象
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        //方式一，getMapper：执行sql
        SensorMapper sensorMapper = sqlSession.getMapper(SensorMapper.class);
        return sensorMapper.getInfoByName(name);
    }
}
