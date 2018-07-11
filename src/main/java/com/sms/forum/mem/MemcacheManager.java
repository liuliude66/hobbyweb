package com.sms.forum.mem;

import net.spy.memcached.MemcachedClient;

import java.io.IOException;
import java.net.InetSocketAddress;

public class MemcacheManager {

    // 缓存客户端
    private MemcachedClient memcacheCient;

    private MemcacheManager() {
        try {
            memcacheCient = new MemcachedClient(new InetSocketAddress("127.0.0.1", 11211));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private final static class singleton {
        public static final MemcacheManager INSTANCE = new MemcacheManager();
    }

    public static MemcacheManager get() {
        return singleton.INSTANCE;
    }

    public void add(String key, Object value, int milliseconds) {
        memcacheCient.add("temp-" + key, milliseconds, value);
    }

    public void add(String key, Object value) {
        memcacheCient.add(key, 3600, value);
    }

    public void remove(String key, int milliseconds) {
        memcacheCient.delete("temp-" + key);
    }

    public void remove(String key) {
        memcacheCient.delete("temp-" + key);
    }

    public void update(String key, Object value, int milliseconds) {
        memcacheCient.replace("temp-" + key, milliseconds, value);
    }

    public void update(String key, Object value) {
        memcacheCient.replace(key, 3600, value);
    }

    public Object get(String key) {
        return memcacheCient.get("temp-" + key);
    }
}
