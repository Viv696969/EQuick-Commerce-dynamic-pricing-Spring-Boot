package com.vivekemipre.dynamicpricing.util;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Component
public class RedisUtility {

    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    private final int pinCodeBucketSize = 5;
    private final long ttl=10;

    private int getPinCodeBucket(int pinCode){
        return  (pinCode / this.pinCodeBucketSize) * pinCodeBucketSize;
    }

    public String getKey(String city,String productId,int pinCode){
       return city+"-"+getPinCodeBucket(pinCode)+"-"+productId;
    }

    @Async
    public void increaseDemand(String city,String productId,int pinCode,int count){
        String key=getKey(city,productId,pinCode);

        String demand=redisTemplate.opsForValue().get(key);
        Long currentTtl = redisTemplate.getExpire(key, TimeUnit.MINUTES);

        if(demand==null){
            redisTemplate.opsForValue().set(key,String.valueOf(count),this.ttl, TimeUnit.MINUTES);
        }
        else{
            int currentDemand=Integer.parseInt(demand);
            int newDemand = currentDemand + count;


            int nearestHundred = ((currentDemand + 99) / 100) * 100;
            double threshold = nearestHundred * 0.7;

            if (currentTtl == null || currentTtl <= 0) {
                currentTtl = this.ttl;
            }

            if (currentDemand > threshold) {
                currentTtl += 5;
            }
            redisTemplate.opsForValue().set(key, String.valueOf(newDemand), currentTtl, TimeUnit.MINUTES);
        }

    }

    public int getProductDemand(String city,String productId,int pinCode){
        String key=getKey(city,productId,pinCode);
        String demand=redisTemplate.opsForValue().get(key);
        return demand!=null?Integer.parseInt(demand):0;
    }

    public Map<String, Integer> getProductDemandBatched(List<String> keys) {
        List<String> values = redisTemplate.opsForValue().multiGet(keys);
        Map<String, Integer> result = new HashMap<>();

        for (int i = 0; i < keys.size(); i++) {
            String val = values.get(i);
            result.put(keys.get(i), val!=null?Integer.parseInt(val):0);

        }

        return result;
    }




}
