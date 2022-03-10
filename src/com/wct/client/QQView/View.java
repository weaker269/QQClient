package com.wct.client.QQView;

import com.wct.client.service.ClientConnectServerThread;
import com.wct.client.service.ManageClientConnectServerThread;
import com.wct.client.service.UserClientService;

import java.net.Socket;
import java.util.Scanner;

/**
 * @author WenCT
 */
public class View {
    public static void main(String[] args) {
        View view = new View();
        view.mainView();
        System.out.println("客户端退出...");
    }
    private boolean loop = true;
    private String opt;
    private UserClientService userClientService = new UserClientService();
    Scanner scanner = new Scanner(System.in);
    public void mainView(){
        while(loop){
            System.out.println("================欢迎来到网络QQ通信系统================");
            System.out.println("\t\t 1. 登录系统");
            System.out.println("\t\t 9. 退出系统");
            opt = scanner.next();
            switch (opt){
                case "1" :
                    System.out.println("请输入UID: ");
                    String uid = scanner.next();
                    System.out.println("请输入密码: ");
                    String password = scanner.next();
                    if(userClientService.checkUser(uid,password)){
                         secondView();
                    }
                    break;
                case "9" :
                    loop = false;
                    break;
            }
        }
        scanner.close();
    }
    public void secondView(){
        while (loop) {
            System.out.println("\n==============欢迎用户" + userClientService.getU().getUid() + "================");
            System.out.println("\t\t 1.显示在线用户列表");
            System.out.println("\t\t 2.群发消息");
            System.out.println("\t\t 3.私聊消息");
            System.out.println("\t\t 4.发送文件");
            System.out.println("\t\t 9.退出系统");
            String opt2 = scanner.next();
            if(opt2.equals("1")){
                userClientService.onlineUserList();
            }
            else if(opt2.equals("2")) System.out.println("群发消息");
            else if(opt2.equals("3")){
                System.out.println("私聊消息");
                System.out.println("请输入接收者uid: ");
                Scanner input = new Scanner(System.in);
                String receiver = input.next();
                userClientService.sendMessage(receiver);
            }
            else if(opt2.equals("4")) System.out.println("发送文件");
            else if(opt2.equals("9")) {
                System.out.println("退出系统");
                loop = false;
                userClientService.userExit();
                break;
            }
        }
    }
}
