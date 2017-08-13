package com.dewaldv.kata05;

import static org.apache.commons.codec.digest.DigestUtils.md5Hex;

public class Dictionary {

    private final boolean[] bitmap;

    public Dictionary() {
        this.bitmap = new boolean[0xfffff];
    }

    public void add(String element) {
        bitmap[elementIdx(element)] = true;
    }

    public boolean contains(String element) {
        return bitmap[elementIdx(element)];
    }

    private int elementIdx(String element) {
        String md5sum = md5Hex(element);
        return Integer.parseInt(md5sum.substring(5, 10), 16);
    }
}
