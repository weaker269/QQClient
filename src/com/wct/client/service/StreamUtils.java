package com.wct.client.service;

import java.io.*;

/**
 * @author WenCT
 */
public class StreamUtils {
    /**
     * 功能：将输入流转换成byte[]
     * @param is
     * @return
     * @throws IOException
     */
    public static byte[] streamToByteArray(InputStream is) throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        byte[] b = new byte[1024];
        int len;
        while((len = is.read(b))!=-1){
             bos.write(b,0,len);
        }
        byte[] array = bos.toByteArray();
        bos.close();
        return array;
    }

    /**
     * 功能：把InputStream转换成String
     * @param is
     * @return
     * @throws IOException
     */
    public static String streamToString(InputStream is) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder builder = new StringBuilder();
        String line;
        while((line = reader.readLine())!=null){
            builder.append(line + "\r\n");
        }
        return builder.toString();
    }
}
