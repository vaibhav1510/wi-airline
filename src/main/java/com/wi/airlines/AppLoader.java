package com.wi.airlines;

import java.util.Iterator;
import java.util.TreeSet;

/**
 * Created by cb-vaibhav on 08/10/18.
 */
public class AppLoader {

    public App startApp(int[][] arr) {
        TreeSet<Block> blocks = initBlocks(arr);
        initSeats(blocks);
        return App.createInst(blocks);
    }

    private TreeSet<Block> initBlocks(int[][] arr) {
        TreeSet<Block> blocks = new TreeSet<>();
        int blockCode = 65;// A,B,C
        Block block = null;
        for (int i = 0; i < arr.length; i++) {
            int cols = arr[i][0];
            int rows = arr[i][1];
            block = new Block((char) blockCode++, cols, rows);
            BlockMeta meta = new BlockMeta(block);
            if (i != 0 && i != arr.length - 1) {
                meta.addSeatType(BlockMeta.SEAT_TYPE.AISLE);
                if (cols > 2) {
                    meta.addSeatType(BlockMeta.SEAT_TYPE.MIDDLE);
                }
                continue;
            }
            if (i == 0) { // FIRST
                meta.addSeatType(BlockMeta.SEAT_TYPE.WINDOW);
                meta.mostLeft = true;
                if (cols == 1) {
                    continue;
                } else if (arr.length > 1) {
                    if (cols == 2) {
                        meta.addSeatType(BlockMeta.SEAT_TYPE.AISLE);
                    } else {
                        meta.addSeatType(BlockMeta.SEAT_TYPE.AISLE);
                        meta.addSeatType(BlockMeta.SEAT_TYPE.MIDDLE);
                    }
                }
            }
            if (i == arr.length - 1 && i != 0) { // LAST
                meta.addSeatType(BlockMeta.SEAT_TYPE.WINDOW);
                if (cols == 1) {
                    continue;
                } else if (arr.length > 1) {
                    if (cols == 2) {
                        meta.addSeatType(BlockMeta.SEAT_TYPE.AISLE);
                    } else {
                        meta.addSeatType(BlockMeta.SEAT_TYPE.AISLE);
                        meta.addSeatType(BlockMeta.SEAT_TYPE.MIDDLE);
                    }
                }
            }
            block.blockMeta(meta);
        }
        blocks.add(block);
        return blocks;
    }

    private void initSeats(TreeSet<Block> blocks) {
        Iterator<Block> itr = blocks.iterator();
        while (itr.hasNext()) {
            Block block = itr.next();
            int counter = 1;
            for (int i = 0; i < block.cols; i++) {
                for (int j = 0; j < block.rows; j++) {
                    Seat s = new Seat(block.code, counter++);
                    BlockMeta.SEAT_TYPE type = null;
                    if (j > 0 && j < block.rows - 1) {
                        type = BlockMeta.SEAT_TYPE.MIDDLE;
                    } else if (j == 0) {
                        type = block.meta.mostLeft ? BlockMeta.SEAT_TYPE.WINDOW : BlockMeta.SEAT_TYPE.AISLE;
                    } else if (j == block.rows - 1) { // =last
                        type = itr.hasNext() ? BlockMeta.SEAT_TYPE.AISLE : BlockMeta.SEAT_TYPE.WINDOW;
                    }
                    block.addSeat(s, type);
                }
            }
        }
    }
}