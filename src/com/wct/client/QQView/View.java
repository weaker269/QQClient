package com.wct.client.QQView;

import java.util.Scanner;

/**
 * @author WenCT
 */
public class View {
    public static void main(String[] args) {
        View view = new View();
        view.mainView();
    }
    private boolean loop = true;
    private String opt;
    public void mainView(){
        Scanner scanner = new Scanner(System.in);
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
                    if(true){
                        System.out.println("\n==============欢迎用户" + uid + "================");
                        System.out.println("\t\t 1.显示在线用户列表");
                        System.out.println("\t\t 2.群发消息");
                        System.out.println("\t\t 3.私聊消息");
                        System.out.println("\t\t 4.发送文件");
                        System.out.println("\t\t 9.退出系统");
                        String opt2 = scanner.next();
                        if(opt2.equals("1")) System.out.println("显示在线用户列表");
                        else if(opt2.equals("2")) System.out.println("群发消息");
                        else if(opt2.equals("3")) System.out.println("私聊消息");
                        else if(opt2.equals("4")) System.out.println("发送文件");
                        else if(opt2.equals("5")) System.out.println("退出系统");
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

    }
}
