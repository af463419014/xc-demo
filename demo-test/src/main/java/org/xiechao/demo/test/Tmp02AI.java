package org.xiechao.demo.test;

import org.apache.http.HttpResponse;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.xiechao.demo.utils.AES;
import org.xiechao.demo.utils.AEScrypt;
import org.xiechao.demo.utils.MD5;
import org.xiechao.demo.utils.U;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by yp-tc-m-2682 on 16/7/25.
 */
public class Tmp02AI {

    public static void main(String[] args) throws Exception {
        System.out.println(tuling("今天天气好吗"));
    }


    public static String tuling(String info) throws Exception {
        String key="403a02f387193c142cdbf3f7ec05abef";
        String secret="d1324851fbd0fcf7";
        String  url="http://www.tuling123.com/openapi/api";
        String timestamp=""+1469430594848L;//new Date().getTime();
        Map<String,String> param=new HashMap<>();
        param.put("key",key);
        param.put("info",info);

        String rs=post(url,param);
        return new String(rs.getBytes(U.ISO),U.UTF);

//
//
//        String keyParam=key+secret+timestamp;
//        System.out.println("md5 value:"+keyParam);
//        String aesKey= MD5.encode(keyParam).toLowerCase();
//        System.out.println("md5 code:"+aesKey);
//
//        System.out.println("aes value:"+U.OM.writeValueAsString(param));
//        String data= AES.encrypt2(U.OM.writeValueAsString(param), aesKey);
//        System.out.println("aes code:"+data);
//        Map<String,String> parr=new HashMap<>();
//        parr.put("key",key);
//        parr.put("timestamp",timestamp);

//        data="U2FsdGVkX194zCaOFf5b1w+aoTGussbloc+mg3Q3/aijx+AI8/G67KTUw2jNw6YJ9LagLoiggnOww3vzvVfHweuM0KiiavEd0nBmJFn7IVg=";
//        parr.put("data",data);

//        System.out.println(parr);
//
    }

    private static CloseableHttpClient client = HttpClients.createDefault();
    private static String post(String url,Map<String,String> parr) throws IOException {
        HttpRequestBase method=new HttpPost(url);

				JSONObject jsonParam=new JSONObject();
//        List<NameValuePair> kvs=new ArrayList<>();
        for (String key : parr.keySet()) {
						jsonParam.put(key, parr.get(key));
//            kvs.add(new BasicNameValuePair(key,parr.get(key)));
        }
				StringEntity entity=new StringEntity(jsonParam.toString(), U.UTF);
				((HttpEntityEnclosingRequestBase) method).setEntity(entity);
//        ((HttpEntityEnclosingRequestBase) method).setEntity(new UrlEncodedFormEntity(kvs));
        HttpResponse result=client.execute(method);
        return EntityUtils.toString(result.getEntity());
    }
}
