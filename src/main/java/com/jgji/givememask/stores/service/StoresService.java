package com.jgji.givememask.stores.service;

import java.util.List;

import com.jgji.givememask.stores.model.Store.Stores;

public interface StoresService {
    public List<Stores> getStoresByAddress(String address, String extraAddress);
}
