package com.fyt.timelycommunication.config;

import com.fyt.timelycommunication.server.WebSocketServer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;


@Configuration
@Slf4j
public class WebSocketConfig {

    public WebSocketConfig(){
        log.debug("WebSocket初始化...");
    }

    @Bean
    public ServerEndpointExporter serverEndpointExporter(){
        return new ServerEndpointExporter();
    }


}
