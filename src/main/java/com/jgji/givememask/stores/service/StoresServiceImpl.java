package com.jgji.givememask.stores.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import com.jgji.givememask.stores.model.Store;
import com.jgji.givememask.stores.model.Store.Stores;

@Service("StoresService")
public class StoresServiceImpl implements StoresService {

    private static String URL = "https://8oi9s0nnth.apigw.ntruss.com/corona19-masks/v1/storesByAddr/json";
    
    public List<Stores> getStoresByAddress(String address) {
        RestTemplate restTemplate = new RestTemplate();
        
        if (!StringUtils.isEmpty(address)) {
            URL += "?address=" + address;
        }
        
        Store stores = restTemplate.getForObject(URL, Store.class);
        List<Stores> StoreList = stores.getStores();
        
        for (int i = 0; i < StoreList.size(); i++) {
            System.out.println(StoreList.get(i));
        }
        
        return StoreList;
        
    }
}
