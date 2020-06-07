package server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import server.pojo.SensorData;
import server.service.Main;
import server.service.SensorService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Controller
public class DataController {
    @Autowired
    private SensorService sensorService;

    @GetMapping("/")
    public String toIndex(){
        new Thread(new Main()).start();
        return "index";
    }
    @ResponseBody
    @PostMapping("/getdata")
    public List<SensorData> getData(){
        Collection<SensorData> values = Main.dataMap.values();
        final int size = values.size();
        List<SensorData> list = new ArrayList<>(values);
        return list;
    }

    @ResponseBody
    @GetMapping("/stop/{name}")
    public String stop(@PathVariable(name = "name") String name){
        sensorService.stopSensor(name);
        return "ok";
    }
}
