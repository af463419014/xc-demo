package org.xiechao.demo.test;


import org.json.JSONObject;
import org.xiechao.demo.test.tulingUtils.Aes;
import org.xiechao.demo.test.tulingUtils.Md5;
import org.xiechao.demo.test.tulingUtils.PostServer;

/**
 * Created by yp-tc-m-2682 on 16/7/26.
 */
public class Tmp04TulingSec {

    public static void main(String[] args) {
        //图灵网站上的secret
        String secret = "d1324851fbd0fcf7";
        //图灵网站上的apiKey
        String apiKey = "403a02f387193c142cdbf3f7ec05abef";
//        String cmd = "请问你你觉得你是猪吗";//测试用例
        String cmd = "你是猪吗";//测试用例
        //待加密的json数据
        String data = "{\"key\":\""+apiKey+"\",\"info\":\""+cmd+"\"}";
        //获取时间戳
        String timestamp = String.valueOf(System.currentTimeMillis());

        //生成密钥
        String keyParam = secret+timestamp+apiKey;
        String key = Md5.MD5(keyParam);

        //加密
        Aes mc = new Aes(key);
        data = mc.encrypt(data);

        //封装请求参数
        JSONObject json = new JSONObject();
        json.put("key", apiKey);
        json.put("timestamp", timestamp);
        json.put("data", data);
        //请求图灵api
        String result = PostServer.SendPost(json.toString(), "http://www.tuling123.com/openapi/api");
        System.out.println(result);
    }
}
