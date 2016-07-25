package org.xiechao.demo.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;

/**
 * Created by yp-tc-m-2682 on 16/7/21.
 */
public class U {
    public static final Logger LOG= LoggerFactory.getLogger(U.class);

    public static final ObjectMapper OM=new ObjectMapper();

    public static final String UTF="UTF-8";
    public static final String ISO="ISO-8859-1";

    static {
        OM.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS"));
    }

    /**
     * 返回一个大于等于0,小于a的整数
     * @param a
     * @return
     */
    public int rand(int a){
        return  rand(0,a);
    }

    /**
     * 返回一个大于等于a,小于b的整数
     * @param a
     * @return
     */
    public int rand(int a,int b){

        return (int)(a+Math.random()*(b-a));
    }
}
