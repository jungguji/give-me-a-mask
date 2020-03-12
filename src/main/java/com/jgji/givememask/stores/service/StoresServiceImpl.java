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
    
    public List<Stores> getStoresByAddress(String address, String extraAddress) {
        String url = MAIN_URL;
        
        String addr = setAddress(address, extraAddress);
        if (!StringUtils.isEmpty(addr)) {
            url += "?address=" + addr;
        }
        
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType( MediaType.APPLICATION_JSON );
        
        RestTemplate restTemplate = new RestTemplate();
        Store stores = restTemplate.getForObject(url, Store.class);
        
        List<Stores> storeList = stores.getStores();
        
        convertRemainStats(storeList);
        
        for (int i = 0; i < storeList.size(); i++) {
            System.out.println(storeList.get(i));
        }
        
        return storeList;
    }
    
    private String setAddress(String address, String extraAddress) {
        address = address.trim();
        extraAddress = extraAddress.trim();
        
        StringBuilder sb = new StringBuilder();
        
        String[] array = address.split(" ");
        
        array[0] = convertState(array[0]);
        
        if (!StringUtils.isEmpty(extraAddress)) {
            array[2] = extraAddress;
        }
        
        sb.append(array[0]);
        sb.append(" ");
        sb.append(array[1]);
        sb.append(" ");
        sb.append(array[2]);
        
        return sb.toString();
    }
    
    private String convertState(String state) {
        String result = "";
        switch (state) {
            case "서울":
                result = "서울특별시";
                break;
                
            case "부산":
            case "대구":
            case "인천":
            case "광주":
            case "대전":
            case "울산":
                result = state + "광역시";
                break;
    
            case "경기":
                result = "경기도";
                break;
            case "강원":
                result = "강원도";
                break;
            case "경북":
                result = "경상북도";
                break;
            case "경남":
                result = "경상남도";
                break;
            case "전북":
                result = "전라북도";
                break;
            case "전남":
                result = "전라남도";
                break;
            case "세종":
                result = "세종특별자치시";
                break;
            case "제주":
                result = "제주특별자치도";
                break;
                
            default:
                break;
        }
        
        return result;
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
