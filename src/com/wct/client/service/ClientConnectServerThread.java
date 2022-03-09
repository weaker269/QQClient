package com.wct.client.service;

import com.wct.QQCommon.Message;
import com.wct.QQCommon.MessageType;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

/**
 * @author WenCT
 * 该类为客户端线程类
 */
public class ClientConnectServerThread extends Thread{
    private Socket socket;

    public ClientConnectServerThread(Socket socket) {
        this.socket = socket;
    }

    public Socket getSocket() {
        return socket;
    }

    @Override
    public void run() {
        while(true){
            try {
                System.out.println("客户端线程，等待读取服务端发送的消息");
                ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                Message message = (Message)ois.readObject();
                if(message.getMessageType().equals(MessageType.MESSAGE_RET_ONLINE_USER)){
                    String[] sp = message.getContent().split(" ");
                    System.out.println("=========当前在线用户列表========");
                    for(String str : sp){
                        System.out.println("用户:  " + str);
                    }
                }else{

                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
