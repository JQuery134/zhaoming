package com.example.administrator.yeepost;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Administrator on 2016/2/17/017.
 */


public class Utils {

    public static String getTextFromStream(InputStream is){
		//创建一个1024字节长度的缓冲区
        byte[] b = new byte[1024];
        int len = 0;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            while((len = is.read(b)) != -1){
                bos.write(b, 0, len);
            }
            String text = new String(bos.toByteArray());
            bos.close();
            return text;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }
}
