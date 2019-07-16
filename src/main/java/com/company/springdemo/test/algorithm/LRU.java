package com.company.springdemo.test.algorithm;

import java.util.HashMap;
import java.util.Iterator;

/**
 * @Auther: whs
 * @Date: 2019/2/18 18:28
 * @Description: LRU最近最少使用算法
 */
public class LRU<K, V> implements Iterable<K> {

    private Node head;
    private Node tail;
    private HashMap<K, Node> map;
    private int maxSize;

    private class Node {
        Node pre;
        Node next;
        K k;
        V v;

        public Node(K k, V v) {
            this.k = k;
            this.v = v;
        }
    }

    public LRU(int maxSize) {
        this.maxSize = maxSize;
        this.map = new HashMap<>(maxSize * 4 / 3);
        head = new Node(null, null);
        tail = new Node(null, null);
        head.next = tail;
        tail.pre = head;
    }

    public V get(K key) {
        if (!map.containsKey(key)) {
            return null;
        }
        Node node = map.get(key);
        unlink(node);
        appendHead(node);
        return node.v;
    }

    private void appendHead(Node node) {
        Node next = head.next;
        // 将该节点放在链头，标示最近使用
        node.next = next;
        next.pre = node;
        // 重新指向链头
        node.pre = head;
        node.next = node;
    }

    private void unlink(Node node) {
        Node pre = node.pre;
        Node next = node.next;
        // 删除原来位置
        pre.next = next;
        next.pre = pre;
        // 清除缓存
        node.pre = null;
        node.next = null;
    }

    public void put(K key, V value) {
        if (map.containsKey(key)) {
            Node node = map.get(key);
            unlink(node);
        }
        Node node = new Node(key, value);
        map.put(key, node);
        appendHead(node);
        if (map.size() > maxSize) {
            Node toRemove = removeTail();
            map.remove(toRemove.k);
        }
    }

    private Node removeTail() {
        Node node = tail.pre;
        Node pre = node.pre;
        tail.pre = pre;
        pre.next = tail;
        node.pre = null;
        node.next = null;
        return node;
    }

    @Override
    public Iterator<K> iterator() {

        return new Iterator<K>() {
            private Node cur = head.next;

            @Override
            public boolean hasNext() {
                return cur != tail;
            }

            @Override
            public K next() {
                Node node = cur;
                cur = cur.next;
                return node.k;
            }
        };
    }
}