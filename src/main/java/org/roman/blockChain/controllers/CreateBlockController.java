package org.roman.blockChain.controllers;

import org.roman.blockChain.DTO.GeneerateBlockDto;
import org.roman.blockChain.DTO.SendToAnotheUserDto;
import org.roman.blockChain.actions.MineBlocks;
import org.roman.blockChain.classes.MyUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
public class CreateBlockController {

    private MineBlocks mineBlocks = new MineBlocks();
    private MyUtils myUtils = new MyUtils();

    @RequestMapping(value = "/addBlocks", method = RequestMethod.POST)
    public ResponseEntity mineBlock(@RequestBody GeneerateBlockDto generateBlocksDTO) {
        int userId = generateBlocksDTO.getUserId();
        int count = generateBlocksDTO.getCountOfBlocks();
        mineBlocks.getCreatedBlocks(userId, count);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @RequestMapping(value = "/getBlocks/{id}", method = RequestMethod.GET)
    public String getBlocksForUser(@PathVariable int id) {
        return mineBlocks.getBlocksForUser(id);
    }

    @RequestMapping(value = "/getBlocks", method = RequestMethod.GET)
    public String getAllBlocks() {
        return mineBlocks.getAllBlocks();
    }

    @RequestMapping("/")
    public String index() {
        return "Welcome";
    }

    @RequestMapping(value = "/moveBlock", method = RequestMethod.POST)
    public ResponseEntity sentBlockToAnotherUser(@RequestBody SendToAnotheUserDto sendToAnotheUserDTO) {
        int oldUser = sendToAnotheUserDTO.getOldUserId();
        int newUser = sendToAnotheUserDTO.getNewUserId();
        int block = sendToAnotheUserDTO.getBlockIs();
        if (myUtils.isBlockPresent(mineBlocks.myBlochChain, oldUser, block)) {
            mineBlocks.myBlochChain.stream().filter(b -> b.blockCount == block)
                    .forEach(b -> myUtils.moveBlock(mineBlocks.myBlochChain, block, newUser));
            return ResponseEntity.status(HttpStatus.ACCEPTED).build();
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }



}
