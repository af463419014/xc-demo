package org.xiechao.demo.test;

import com.fasterxml.jackson.core.JsonParseException;
import org.xiechao.demo.utils.U;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by yp-tc-m-2682 on 16/7/22.
 */
public class Tmp01Map {
    public static void main(String[] args) throws IOException {
        String db2 = "{\"SUMMARY\":\"688011447\",\"TRX_AMOUNT\":-0.0100,\"ACCOUNT_ID\":12907370,\"CREATEDATEONLY\":\"2016-06-01\",\"REQUEST_ID\":null,\"DESC_INFO\":\"ACCOUNTING\",\"COMPLETE_TIME\":1464759240968,\"FRPID\":null,\"ID\":2694141268,\"POST_BALANCE\":39637.8700,\"IN_OUT_FLAG\":\"1\",\"FEE\":0.0000,\"SETTLEABLE\":1,\"SETTLE_FLAG\":0,\"BIZ_TYPE\":\"RECEIVE\",\"PRODUCTCAT\":null,\"DESCRIPTION\":\"bizType=一键支付;requestId=b2bb8c475ef94662845decd2b85efaa3\",\"ADJUST_ACCOUNTTYPE\":null,\"AMOUNT_IN\":\"0.01\",\"TRX_ID\":411606014879980847,\"CHANNEL\":null,\"HISTORY_TYPE\":\"SALES\",\"BATCH_ID\":null,\"AMOUNT_OUT\":\"0\",\"TRX_DATE\":1464759202636,\"REALAMOUNT\":-0.0100,\"BIZTYPE\":\"OL-ONEPAY\",\"RECEIVERID\":null}";
        String phoenix = "{\"SUMMARY\":\"738653568\",\"SETTLEABLE\":1,\"SETTLE_FLAG\":0,\"BIZ_TYPE\":\"RECEIVE\",\"TRX_AMOUNT\":-0.01,\"ACCOUNT_ID\":12907370,\"DESCRIPTION\":\"bizType=一键支付;requestId=d6b1fe8d864c4685b9884a5e84bdf9ac\",\"ADJUST_ACCOUNTTYPE\":null,\"AMOUNT_IN\":0.01,\"REQUEST_ID\":null,\"TRX_ID\":411606308498126535,\"DESC_INFO\":\"ACCOUNTING\",\"HISTORY_TYPE\":\"SALES\",\"COMPLETE_TIME\":\"2016-07-01 00:00:14.226000\",\"BATCH_ID\":null,\"ID\":2754413758,\"AMOUNT_OUT\":0,\"POST_BALANCE\":623424.11,\"TRX_DATE\":\"2016-06-30 23:36:24.214000\",\"IN_OUT_FLAG\":\"1\",\"FEE\":0}";
        Map<String, Object> db2Map = U.OM.readValue(db2,Map.class);
        Map<String, Object> phoenixMap = U.OM.readValue(phoenix,Map.class);


        System.out.println(db2Map.size());
        System.out.println(phoenixMap.size());

//        db2Map.putAll(phoenixMap);
//
//        System.out.println(db2Map.size());
        Set<String> keys=new HashSet<>();
        Set<String> db2Keys=new HashSet<>();
        Set<String> phoenixKeys=new HashSet<>();
        for(String key:db2Map.keySet()){
            keys.add(key);
            db2Keys.add(key);
        }
        for(String key:phoenixMap.keySet()){
            keys.add(key);
            phoenixKeys.add(key);
        }
        for(String key:keys){
            System.out.println("=============");
            if(!db2Keys.contains(key)){
                System.out.println(">>>>db2 is null");
            }
            if(!phoenixKeys.contains(key)){
                System.out.println(">>>>phoenix is null");
            }
            System.out.println("key:"+key+",db2:"+db2Map.get(key)+",phoenix:"+phoenixMap.get(key));
        }
    }
}
