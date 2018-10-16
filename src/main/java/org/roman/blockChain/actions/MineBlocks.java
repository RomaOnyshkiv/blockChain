package org.roman.blockChain.actions;

import com.google.gson.GsonBuilder;
import org.roman.blockChain.classes.Block;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class MineBlocks {

    public ArrayList<Block> myBlochChain = new ArrayList<>();

    private void maineBlocks(int userId, int blocksNumber) {
        int b = myBlochChain.stream().mapToInt(c -> c.blockCount).max().orElse(0);
        for (int i = 0; i < blocksNumber; i++) {
            if (myBlochChain.size() == 0) {
                myBlochChain.add(new Block(userId,"Im " + (b + i + 1) + " block in chain", "0", (b + i + 1)));
                System.out.println("Trying to Mine block " + (b + i + 1) + "... ");
                myBlochChain.get(myBlochChain.size() - 1).mineBlock();
            } else {
                myBlochChain.add(new Block(userId,"Im " + (b + i + 1) + " block in chain", myBlochChain.get(myBlochChain.size() - 1).hash, (b + i + 1)));
                System.out.println("Trying to Mine block " + (b + i + 1) + "... ");
                myBlochChain.get(myBlochChain.size() - 1).mineBlock();
            }
        }
    }

    public String getCreatedBlocks(int userId, int blocksNumber) {

        String blockChain;

        if (blocksNumber == 0) {
            blockChain = "Number of blocks cannot by '0'";
        } else {
            maineBlocks(userId, blocksNumber);
            blockChain = new GsonBuilder().setPrettyPrinting().create().toJson(myBlochChain);
        }

        return blockChain;
    }

    public String getBlocksForUser(int user) {
        String blocks;
        if (myBlochChain.size() != 0) {
            blocks = new GsonBuilder().setPrettyPrinting().create()
                    .toJson(myBlochChain.stream().filter(e -> e.userId == user)
                            .collect(Collectors.toList()));
        } else {
            blocks = "User " + user + " do not have any blocks";
        }
        return blocks;
    }

    public String getAllBlocks() {
        String blocks;
        if (myBlochChain.size() != 0) {
            blocks = new GsonBuilder().setPrettyPrinting().create().toJson(myBlochChain);
        } else {
            blocks = "No blocks found";
        }
        return blocks;
    }
}
