package ru.otus.cachehw;


import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.WeakHashMap;

public class MyCache<K, V> implements HwCache<K, V> {
    // Надо реализовать эти методы

    private final WeakHashMap<K, V> cache = new WeakHashMap<>();
    private final List<WeakReference<HwListener>> listeners = new ArrayList<>();

    private void notify(K key, V value, String action) {
        for (int i = listeners.size() - 1; i >= 0; i--) {

            var listener = listeners.get(i).get();
            if (listener == null) {
                listeners.remove(i);
                continue;
            }

            listener.notify(key, value, action);
        }
    }

    @Override
    public void put(K key, V value) {
        if (cache.containsKey(key)) {
            cache.put(key, value);
            notify(key, value, "updated");
        } else {
            cache.put(key, value);
            notify(key, value, "inserted");
        }
    }

    @Override
    public void remove(K key) {
        V v = cache.remove(key);
        notify(key, v, "removed");
    }

    @Override
    public V get(K key) {
        V v = cache.getOrDefault(key, null);
        notify(key, v, "get");
        return v;
    }

    @Override
    public void addListener(HwListener<K, V> listener) {
        listeners.add(new WeakReference<>(listener));
    }

    @Override
    public void removeListener(HwListener<K, V> listener) {
        for (int i = listeners.size() - 1; i >= 0; i--) {
            var lstnr = listeners.get(i).get();
            if (lstnr == null) {
                listeners.remove(i);
                continue;
            }

            if (lstnr == listener) {
                listeners.remove(i);
            }
        }
    }
}
