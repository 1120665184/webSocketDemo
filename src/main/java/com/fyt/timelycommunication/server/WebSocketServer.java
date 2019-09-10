package com.fyt.timelycommunication.server;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

@ServerEndpoint("/websocket")
@Component
@Slf4j
public class WebSocketServer {

    public static Set<WebSocketServer> webSocketSet = new CopyOnWriteArraySet<>();

    private Session session;


    @OnOpen
    public void onOpen(Session session){
        this.session = session;
        webSocketSet.add(this);
        log.info("有新窗口开始监听");

        try {
            this.sendMessage("连接成功");
        }catch (IOException e){
            log.error("webSocket IO异常");
        }
    }

    @OnClose
    public void onClose() {
        webSocketSet.remove(this);
        log.info("有一连接关闭");
    }

    @OnMessage
    public void onMessage(String message,Session session){
        log.info("接收到{}的信息:{}",session.getId(),message);

    }

    @OnError
    public void onError(Session session,Throwable error){
        log.error("{}错误异常：{}",session.getId(),error.getLocalizedMessage());
        error.printStackTrace();
    }

    /**
     * 给客户端发送消息
     * @param message
     * @throws IOException
     */
    public void sendMessage(String message) throws IOException {
        this.session.getBasicRemote().sendText(message);
    }

}
