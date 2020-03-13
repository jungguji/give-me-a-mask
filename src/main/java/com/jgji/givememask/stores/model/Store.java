package com.jgji.givememask.stores.model;

import java.util.ArrayList;
import java.util.List;

public class Store {
    int count;
    List<Stores> stores = new ArrayList<Stores>();
    
    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<Stores> getStores() {
        return this.stores;
    }

    
    @Override
    public String toString() {
        return "count >>> " + getCount() + " getStoresInfo >>> " + getStores().toString();
    }

    public static class Stores {
        String code;
        String name;
        String addr;
        String type;
        double lat;
        double lng;
        String stock_at;
        String remain_stat;
        String created_at;
        String distance;
        
        public String getCode() {
            return code;
        }
        public void setCode(String code) {
            this.code = code;
        }
        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
        public String getAddr() {
            return addr;
        }
        public void setAddr(String addr) {
            this.addr = addr;
        }
        public String getType() {
            return type;
        }
        public void setType(String type) {
            this.type = type;
        }
        public double getLat() {
            return lat;
        }
        public void setLat(double lat) {
            this.lat = lat;
        }
        public double getLng() {
            return lng;
        }
        public void setLng(double lng) {
            this.lng = lng;
        }
        public String getStock_at() {
            return stock_at;
        }
        public void setStock_at(String stock_at) {
            this.stock_at = stock_at;
        }
        public String getRemain_stat() {
            return remain_stat;
        }
        public void setRemain_stat(String remain_stat) {
            this.remain_stat = remain_stat;
        }
        public String getCreated_at() {
            return created_at;
        }
        public void setCreated_at(String created_at) {
            this.created_at = created_at;
        }
        public String getDistance() {
            return distance;
        }
        public void setDistance(String distance) {
            this.distance = distance;
        }
        
        @Override
        public String toString() {
            return "code >> " + code + " name >> " + name + " address >> " + addr 
                    + "type >> " + type + " lat >> " + lat + " lng >> " + lng + " stock_at >> " + stock_at 
                    + "remian_stat >> " + remain_stat + " created_at >> " + created_at;
        }
    }
}
