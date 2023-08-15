package com.example.audit;

import org.springframework.boot.actuate.info.Info;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class InfoContributor implements org.springframework.boot.actuate.info.InfoContributor {

    @Override
    public void contribute(Info.Builder builder){
        Map<String,String> halpeMap = new HashMap<>();
        halpeMap.put("system","web application for halpe church");
        halpeMap.put("version","1.0.0");
        builder.withDetail("halpe-church-info",halpeMap);

    }
}
