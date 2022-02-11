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
