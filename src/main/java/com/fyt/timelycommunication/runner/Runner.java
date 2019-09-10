package com.fyt.timelycommunication.runner;

import com.fyt.timelycommunication.server.WebSocketServer;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class Runner implements ApplicationRunner {
    @Override
    public void run(ApplicationArguments args) throws Exception {
        while (true){
            String date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss"));
            for(WebSocketServer socket :WebSocketServer.webSocketSet){
                socket.sendMessage(date);
            }
            Thread.sleep(1000);
        }
    }
}
