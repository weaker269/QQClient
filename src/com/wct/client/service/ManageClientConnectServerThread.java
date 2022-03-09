package com.wct.client.service;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * @author WenCT
 * 该类管理客户端线程
 */
public class ManageClientConnectServerThread {
    //我们把多个线程放入集合中
    private static ConcurrentMap<String,ClientConnectServerThread> cm = new ConcurrentHashMap<>();

    public static void addClientConnectServerThread(String uid , ClientConnectServerThread c){
             cm.put(uid,c);
    }
    public static ClientConnectServerThread getClientConnectServerThread(String uid){
        return cm.get(uid);
    }
}
