package com.example.controller;

import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.pojo.Environment;
import com.example.pojo.Posture;
import com.example.server.WebServer;
import com.example.service.EnvironmentService;
import com.example.service.PostureService;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.io.IOException;

@EnableScheduling
@Configuration
public class ScheduleController
{
    @Resource
    private EnvironmentService environmentService;
    @Resource
    private PostureService postureService;

    @Scheduled(fixedRate = 5000)
    public void environment() throws IOException
    {
        long envCount = environmentService.count();
        if (envCount > 0)
        {
            Environment env = environmentService.getOne(new QueryWrapper<Environment>().eq("envID", envCount));
            double temp = env.getTemperature();
            double humid = env.getHumidity();
            double apparenttemp = 0.81 * temp + 0.01 * humid * (0.99 * temp - 14.3) + 46.3;
            WebServer.push("1", msg2Json("200", "send temp", temp));
            WebServer.push("1", msg2Json("200", "send humid", humid));
            if (apparenttemp >= 32)
            {
                WebServer.push("1", msg2Json("200", "send advice", "It is not suitable for exercise now. Take a rest!"));
            }
            else
            {
                WebServer.push("1", msg2Json("200", "send advice", "It is suitable for exercise now. Come on!"));
            }
        }
    }

    @Scheduled(fixedRate = 1000)
    public void posture() throws IOException
    {
        long posCount = postureService.count();
        if (posCount > 0)
        {
            Posture pos = postureService.getOne(new QueryWrapper<Posture>().eq("posID", posCount));
            WebServer.push("1", msg2Json("200", "send status", pos.getStatus()));
        }
    }

    public static String msg2Json(String httpStatus, String msg, Object data)
    {
        String jsonData = JSON.toJSONString(data);
        return "{\"status\":\"" + httpStatus + "\",\"msg\":\"" + msg + "\",\"data\":" + jsonData + "}";
    }
}
