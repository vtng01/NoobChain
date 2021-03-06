package com.van;

import com.google.gson.GsonBuilder;

import java.util.ArrayList;

public class NoobChain {

    public static ArrayList<Block> blockchain = new ArrayList<>();
    public static int difficulty = 5;
    public static void main(String[] args) {
        blockchain.add(new Block("Hi im the first block", "0"));
        System.out.println("Trying to mine block 1...");
        blockchain.get(0).mineBlock(difficulty);

        blockchain.add(new Block("Yo im the second block", blockchain.get(blockchain.size() - 1).hash));
        System.out.println("Trying to mine block 2...");
        blockchain.get(1).mineBlock(difficulty);

        blockchain.add(new Block("Hey im the third block", blockchain.get(blockchain.size() - 1).hash));
        System.out.println("Trying to mine block 3...");
        blockchain.get(2).mineBlock(difficulty);

        System.out.println("Blockchain is valid: " + isChainValid());

        String blockChainJson = new GsonBuilder().setPrettyPrinting().create().toJson(blockchain);
        System.out.println("The block chain: ");
        System.out.println(blockChainJson);
    }

    public static boolean isChainValid() {
        Block currentBlock;
        Block previousBlock;

        // loop and check hashes
        for (int i = 1; i < blockchain.size(); i++) {
            currentBlock = blockchain.get(i);
            previousBlock = blockchain.get(i - 1);
            String hashTarget = new String(new char[difficulty]).replace('\0', '0');

            // compare registered hash and calculated hash
            if (! currentBlock.hash.equals(currentBlock.calculateHash())) {
                System.out.println("Current hashes not equal");
                return false;
            }

            // compare previous hash and calculated hash
            if (! previousBlock.hash.equals(currentBlock.previousHash)) {
                System.out.println("previous hashes not equal");
                return false;
            }

            // check if hash is solved
            if (! currentBlock.hash.substring(0, difficulty).equals(hashTarget)) {
                System.out.println("block has not been mined");
                return false;
            }
        }
        return true;
    }
}
