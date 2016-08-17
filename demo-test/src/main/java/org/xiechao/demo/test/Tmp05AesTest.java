package org.xiechao.demo.test;

import org.xiechao.demo.test.tulingUtils.Aes;
import org.xiechao.demo.utils.AES;

/**
 * Created by yp-tc-m-2682 on 16/7/26.
 */
public class Tmp05AesTest {

    public static void main(String[] args) {
        String text="哈哈你好";
        String key="d1324851fbd0fcf71469516346569403a02f387193c142cdbf3f7ec05abef";
        System.out.println(AES.encrypt2(text,key));

        Aes aes=new Aes(key);
        System.out.println(aes.encrypt(text));
    }
}
