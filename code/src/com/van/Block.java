package com.van;

import java.util.Date;

public class Block {
    public String hash;
    public String previousHash;
    private String data;
    private long timeStamp;

    public Block(String data, String previousHash) {
        this.data = data;
        this.previousHash = previousHash;
        this.timeStamp = new Date().getTime(); // time in milliseconds since 1/1/1970
        this.hash = calculateHash();
    }

    public String calculateHash() {
        String calculatedHash = StringUtil.applySha256(
                previousHash +
                        timeStamp +
                        data
        );

        return calculatedHash;
    }
}
