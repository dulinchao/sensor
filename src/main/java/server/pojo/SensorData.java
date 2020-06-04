package server.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class SensorData implements Serializable {
    private String name;    //传感器的名字
    private int temperature;    //温度
    private int humidity;   //湿度
    private double pressure;   //压力
    private int concentrationOfCO2; //二氧化碳浓度

    public SensorData(String name, int temperature, int humidity, double pressure, int concentrationOfCO2) {
        this.name = name;
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = (double)(Math.round(pressure*100)/100.0);
        this.concentrationOfCO2 = concentrationOfCO2;
    }
}
