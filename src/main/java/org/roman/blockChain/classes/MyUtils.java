package org.roman.blockChain.classes;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

public class MyUtils {

    public String applyHash(String input) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(input.getBytes("UTF-8"));
            StringBuilder buffer = new StringBuilder();
            for (byte aHash : hash) {
                String hex = Integer.toHexString(0xff & aHash);
                if (hex.length() == 1) buffer.append('0');

                buffer.append(hex);
            }
            return buffer.toString();

        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            throw new RuntimeException();
        }
    }

    public boolean isBlockPresent(List<Block> blocks, int oldUser, int blockId) {
        return blocks.stream()
                .filter(b -> b.userId == oldUser)
                .anyMatch(b -> b.blockCount == blockId);
    }

    public void moveBlock(List<Block> blocks, int block, int newUser) {
        for (Block b : blocks) {
            if (b.blockCount == block) {
                b.userId = newUser;
            }
        }
    }

}
