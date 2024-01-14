package org.charaf.customerservice.web;

import org.charaf.customerservice.config.GlobalConfig;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ConfigTestController {

    private GlobalConfig globalConfig;


    public ConfigTestController(GlobalConfig globalConfig) {
        this.globalConfig = globalConfig;
    }


//    @GetMapping("/testoConfig")
//    public List<Integer> getCustomerConfig(){
//        return List.of(globalConfig.getX(),globalConfig.getY());
//    }



}
