package org.xiechao.demo.test;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.xiechao.demo.utils.U;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.IOException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by yp-tc-m-2682 on 16/7/26.
 */
public class Tmp03JuheAI {

    public static void main(String[] args) throws Exception {
        System.out.println("ai:"+ai("你好"));
    }


    private static String ai(String info) throws Exception {
//        String url="http://op.juhe.cn/robot/index";
//        String key="37418acd696b71669289ac5df2cc8ad7";
//        Map<String,String> parr=new HashMap<>();

//        parr.put("key",key);
//        parr.put("info",info);
//        parr.put("dtype","");
//        parr.put("loc","");
//        parr.put("lon","");
//        parr.put("lat","");
//        parr.put("userid","");


        String url="https://www.juhe.cn/box/newtest";
//        String key="37418acd696b71669289ac5df2cc8ad7";
        Map<String,String> parr=new HashMap<>();

//        parr.put("key",key);
        parr.put("requesttypesel","GET");
        parr.put("apiid","335");
        parr.put("params","{\"info\":\"你是猪吗\",\"dtype\":\"\",\"loc\":\"\",\"lon\":\"\",\"lat\":\"\",\"userid\":\"\"}");


        return post(url,parr);
    }

    private static CloseableHttpClient client = HttpClients.createDefault();
    private static String post(String url,Map<String,String> parr) throws Exception {

        client=new SSLClient();

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


class SSLClient extends DefaultHttpClient {
    public SSLClient() throws Exception{
        super();
        SSLContext ctx = SSLContext.getInstance("TLS");
        X509TrustManager tm = new X509TrustManager() {
            @Override
            public void checkClientTrusted(X509Certificate[] chain,
                                           String authType) throws CertificateException {
            }
            @Override
            public void checkServerTrusted(X509Certificate[] chain,
                                           String authType) throws CertificateException {
            }
            @Override
            public X509Certificate[] getAcceptedIssuers() {
                return null;
            }
        };
        ctx.init(null, new TrustManager[]{tm}, null);
        SSLSocketFactory ssf = new SSLSocketFactory(ctx, SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
        ClientConnectionManager ccm = this.getConnectionManager();
        SchemeRegistry sr = ccm.getSchemeRegistry();
        sr.register(new Scheme("https", 443, ssf));
    }
}