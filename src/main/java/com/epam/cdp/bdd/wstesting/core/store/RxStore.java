package com.epam.cdp.bdd.wstesting.core.store;

import java.util.HashMap;

public class RxStore {
    private static RxStore instance = null;
    private HashMap<String, Object> rxStore = new HashMap();

    private RxStore() {

    }

    public static RxStore getInstance() {
        if (instance == null) {
            instance = new RxStore();
        }
        return instance;
    }

    public Object putDataIntoStore(String key, Object value) {
        return rxStore.put(key, value);
    }

    public Object getDataFromStore(String key) {
        return rxStore.get(key);
    }
}
