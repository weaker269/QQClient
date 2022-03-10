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
import java.util.List;

/**
 * @author WenCT
 * 该类完成用户登录的验证和用户注册的功能
 */
public class UserClientService {
    private User u = new User();
    private Socket socket;

    public User getU() {
        return u;
    }

    public boolean checkUser(String userId , String pwd){
        boolean b = false;
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
                System.out.println("用户"+ userId + "登录成功");
                ClientConnectServerThread clientConnectServerThread = new ClientConnectServerThread(socket);
                //启动客户端线程
                clientConnectServerThread.start();
                //将线程加入到一个集合中
                ManageClientConnectServerThread.addClientConnectServerThread(userId,clientConnectServerThread);
                b = true;
            } else{
                System.out.println("用户" + userId + "登录失败");
                socket.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  b;
    }
    //拉取在线用户列表
    public void onlineUserList(){
        Message ms = new Message();
        ms.setMessageType(MessageType.MESSAGE_GET_ONLINE_USER);
        ms.setSender(u.getUid());
        try {
            //获取用户通信线程
            ClientConnectServerThread clientConnectServerThread =
                    ManageClientConnectServerThread.getClientConnectServerThread(u.getUid());
            //获取socket
            Socket socket = clientConnectServerThread.getSocket();

            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            oos.writeObject(ms);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //用户请求退出系统
    public void userExit(){
        Message message = new Message();
        message.setSender(u.getUid());
        message.setMessageType(MessageType.MESSAGE_CLIENT_EXIT);
        System.out.println(u.getUid() + "请求退出客户端");
        try {
            ClientConnectServerThread clientConnectServerThread =
                    ManageClientConnectServerThread.getClientConnectServerThread(u.getUid());
            Socket socket = clientConnectServerThread.getSocket();

            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            oos.writeObject(message);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
