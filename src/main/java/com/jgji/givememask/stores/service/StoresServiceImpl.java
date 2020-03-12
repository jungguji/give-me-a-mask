package com.jgji.givememask.stores.service;

import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import com.jgji.givememask.stores.model.Store;
import com.jgji.givememask.stores.model.Store.Stores;

@Service("StoresService")
public class StoresServiceImpl implements StoresService {

    private static final String MAIN_URL = "https://8oi9s0nnth.apigw.ntruss.com/corona19-masks/v1/storesByAddr/json";
    
    public List<Stores> getStoresByAddress(String address) {
        RestTemplate restTemplate = new RestTemplate();
        
        String url = MAIN_URL;
        
        if (!StringUtils.isEmpty(address)) {
            url += "?address=" + address;
        }
        
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType( MediaType.APPLICATION_JSON );
        
        System.out.println(url.toString());
        Store stores = restTemplate.getForObject(url, Store.class);
        List<Stores> storeList = stores.getStores();
        
        convertRemainStats(storeList);
        
        for (int i = 0; i < storeList.size(); i++) {
            System.out.println(storeList.get(i));
        }
        
        return storeList;
    }
    
    private List<Stores> convertRemainStats(List<Stores> storeList) {
        
        for(Iterator<Stores> it = storeList.iterator() ; it.hasNext() ; ) {
              Stores stores = it.next();
              
              String remainStats = stores.getRemain_stat();
              if (StringUtils.isEmpty(remainStats)) {
                  it.remove();
                  continue;
              }
              
              if ("empty".equals(remainStats)) {
                  remainStats = "3";
              } else if ("few".equals(remainStats)) {
                  remainStats = "2";
              } else if ("some".equals(remainStats)) {
                  remainStats = "1";
              } else {
                  remainStats = "0";
              }
              
              stores.setRemain_stat(remainStats);
          }
        
        Collections.sort(storeList, new Comparator<Stores>() {
            
            @Override
            public int compare(Stores stores1, Stores stores2) { 
                return stores1.getRemain_stat().compareTo(stores2.getRemain_stat()); 
           }
            
        });

        
        return storeList;
    }
}
