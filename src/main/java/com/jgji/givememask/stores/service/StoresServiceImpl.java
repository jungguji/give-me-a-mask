package com.jgji.givememask.stores.service;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import com.jgji.givememask.stores.model.Store;
import com.jgji.givememask.stores.model.Store.Stores;
import com.jgji.givememask.util.Haversine;

@Service("StoresService")
public class StoresServiceImpl implements StoresService {

    private static final String MASK_URL = "https://8oi9s0nnth.apigw.ntruss.com/corona19-masks/v1/storesByGeo/json";
    private static final String KAKAO_LOCAL_MAP_URL = "https://dapi.kakao.com/v2/local/search/address.json";
    private static final String KAKAO_APP_KEY = "KakaoAK 71915571a3719081dfad3ecca9197242";
    private static final int MAP_RADIUS = 1500;
    private static final NumberFormat formatter = new DecimalFormat("#0.00000");    
   
    public List<Stores> getStoresByAddress(String address, String extraAddress) {
        Map<String, Double> userLocationMap = getUserAddressLocation(address);
        
        String url = MASK_URL + getURI(userLocationMap);
        
        RestTemplate restTemplate = new RestTemplate();
        Store stores = restTemplate.getForObject(url, Store.class);
        
        List<Stores> storeList = stores.getStores();
        
        for (int i = 0; i < storeList.size(); i++) {
            System.out.println(storeList.get(i));
        }
        
        convertRemainStats(storeList);
        sort(storeList, userLocationMap);
        
        Stores local = new Stores();
        local.setLat(userLocationMap.get("y"));
        local.setLng(userLocationMap.get("x"));
        
        storeList.add(0, local);
        
        return storeList;
    }
    
    @SuppressWarnings({ "rawtypes", "unchecked" })
    private Map<String, Double> getUserAddressLocation(String address) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType( MediaType.APPLICATION_JSON );
        headers.add(HttpHeaders.AUTHORIZATION, KAKAO_APP_KEY);
        
        String url = KAKAO_LOCAL_MAP_URL + "?query=" + address;
        
        HttpEntity entity = new HttpEntity(headers);
        
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<Map> test = restTemplate.exchange(url, HttpMethod.GET, entity, Map.class);
        
        Map<String, List<Map>> map = test.getBody();
        
        Map<String, Double> document = map.get("documents").get(0);
        
        return document;
    }
    
    private String getURI(Map<String, Double> userLocationMap) {
        Double lat = userLocationMap.get("y");
        Double lng = userLocationMap.get("x");
        
        StringBuilder sb = new StringBuilder();
        sb.append("?lat=").append(lat);
        sb.append("&lng=").append(lng);
        sb.append("&m=").append(MAP_RADIUS);
        
        return sb.toString();
    }
    
    private List<Stores> convertRemainStats(List<Stores> storeList) {
        
        for(Iterator<Stores> it = storeList.iterator() ; it.hasNext() ; ) {
              Stores stores = it.next();
              
              String remainStats = stores.getRemain_stat();
              if (StringUtils.isEmpty(remainStats)) {
                  it.remove();
                  continue;
              }
              
              if ("break".equals(remainStats)) {
                  remainStats = "4";
              } else if ("empty".equals(remainStats)) {
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
        
        return storeList;
    }
    
    private void sort(List<Stores> storeList, Map<String, Double> userLocation) {
        for (Stores store : storeList) {
            Double distance = Haversine.distance(userLocation.get("y"), userLocation.get("x"), store.getLat(), store.getLng());
            
            store.setDistance(formatter.format(distance));
        }
        
        Collections.sort(storeList, new Comparator<Stores>() {
            
            @Override
            public int compare(Stores stores1, Stores stores2) { 
                return stores1.getDistance().compareTo(stores2.getDistance());
           }
        });
    }
}
