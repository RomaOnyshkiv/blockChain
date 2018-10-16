package org.roman.blockChain.classes;

import java.util.Date;

public class Block {


    public int userId;
    public String hash;
    private String previousHash;
    private String data;
    private long timeStamp;
    private int nonce;
    public int blockCount;

    public Block(int userId, String data, String previousHash, int blockCount) {
        this.userId = userId;
        this.data = data;
        this.previousHash = previousHash;
        this.timeStamp = new Date().getTime();
        this.hash = calculateHash();
        this.blockCount = blockCount;
    }

    private String calculateHash() {
        return new MyUtils().applyHash(previousHash + Long.toString(timeStamp)
                + Integer.toString(nonce + userId) + data);
    }

    public void mineBlock() {
        int difficulty = 5;
        String target = new String(new char[5]).replace('\0', '0');
        while(!hash.substring(0, 5).equals(target)) {
            nonce ++;
            hash = calculateHash();
        }
        System.out.println("Block Mined!!! : " + hash);
    }

}
