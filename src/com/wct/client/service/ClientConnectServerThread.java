package com.wct.client.service;

import com.wct.QQCommon.Message;
import com.wct.QQCommon.MessageType;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.Scanner;

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
                }
                else if(message.getMessageType().equals(MessageType.MESSAGE_CLIENT_EXIT_SUCCESS)){
                    System.out.println(message.getReceiver() + " 成功退出客户端");
                    ManageClientConnectServerThread.removeClientConnectServerThread(message.getReceiver());
                    socket.close();
                    break;
                }
                else if(message.getMessageType().equals(MessageType.MESSAGE_COMM_MES)){
                    System.out.println(message.getSendTime()+ "  接收到来自 " + message.getSender() + " 的消息");
                    System.out.println("消息是: " + message.getContent());
                }
                else if(message.getMessageType().equals(MessageType.MESSAGE_ERROR_RECEIVER_OFFLINE)){
                    System.out.println(message.getContent());
                }
                else if(message.getMessageType().equals(MessageType.MESSAGE_FILE_MES)){
                    System.out.println(message.getSendTime()+ "  接收到来自 " + message.getSender() + " 的文件");
                    String defaultPath = "E:/IDEAU_CODE/hsp_lesson/qqDefaultSaveDir/" + message.getFileName();//默认路径
                    Scanner scanner = new Scanner(System.in);
                    BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(defaultPath));
                    bos.write(message.getFileBytes());
                    System.out.println("文件已保存至: " + defaultPath);
                }
                else{

                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
