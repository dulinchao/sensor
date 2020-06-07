package server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import server.dao.SensorMapper;
import server.pojo.SensorData;
import server.service.Main;
import server.service.SensorService;
import server.service.ServerThread;

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
        if(size==0) return null;
        List<SensorData> list = new ArrayList<>(values);
        return list;
    }

    @ResponseBody
    @GetMapping("/stop/{name}")
    public String stop(@PathVariable(name = "name") String name){
        sensorService.stopSensor(name);
        return "ok";
    }

    @ResponseBody
    @GetMapping("/change")
    public String changeFlashTime(@RequestParam(name = "time") Integer time){
        ServerThread.flashTime = time*1000;
        return "ok";
    }

    @ResponseBody
    @GetMapping("/gethistory")
    public List<SensorData> getHistory(@RequestParam(name = "name") String name){
        return sensorService.getHistory(name);
    }
}
