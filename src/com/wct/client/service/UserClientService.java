package com.wct.client.service;

import com.wct.QQCommon.Message;
import com.wct.QQCommon.MessageType;
import com.wct.QQCommon.User;

import java.awt.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;

/**
 * @author WenCT
 * 该类完成用户登录的验证和用户注册的功能
 */
public class UserClientService {
    private User u = new User();
    private Socket socket;
    public boolean checkUser(String userId , String pwd){
        u.setUid(userId);
        u.setPassword(pwd);
        //连接到服务器端
        try {
            socket = new Socket(InetAddress.getByName("127.0.0.1"), 9999);
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            oos.writeObject(u);//发送user对象

            //读取客户端回复的Message对象
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            Message ms = (Message)ois.readObject();

            if(ms.getMessageType().equals(MessageType.MESSAGE_LOGIN_SUCCEED)){ //登录成功
                //创建一个线程和服务端连接
                System.out.println("");


            } else{

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
