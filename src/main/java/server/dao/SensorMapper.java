package server.dao;

import server.pojo.SensorData;

import java.util.List;

public interface SensorMapper {
    //添加历史记录
    void insertInfo(SensorData sensorData);

    //查询历史纪录
    List<SensorData> getInfoByName(String name);

}
