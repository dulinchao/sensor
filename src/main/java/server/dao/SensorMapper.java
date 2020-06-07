package server.dao;

import server.pojo.SensorData;

import java.util.List;

public interface SensorMapper {
    //添加历史记录
    void insertInfo(SensorData sensorData);

    //查询历史纪录
    List<SensorData> getInfoByName(String name);

    //查询最新记录
    SensorData getNowData(String name);

    //插入最新记录
    void insertNowData(SensorData sensorData);

    //更新最新记录
    void updateNowData(SensorData sensorData);

    //查询所有最新记录
    List<SensorData> selectAllData();
}
