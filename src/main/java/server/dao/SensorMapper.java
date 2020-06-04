package server.dao;

import server.pojo.SensorData;

import java.util.List;

public interface SensorMapper {
    void insertInfo(SensorData sensorData);

    List<SensorData> getInfoByName(String name);
}
