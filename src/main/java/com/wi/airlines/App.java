package com.wi.airlines;

import com.sun.org.apache.xerces.internal.xs.datatypes.ByteList;

import java.util.*;

/**
 * Created by cb-vaibhav on 07/10/18.
 */
public class App implements BookingApp {

    @Override
    public Seat bookNext(Passenger p) throws Exception {
        int currentRow = 0;
        int maxRows = Integer.MAX_VALUE;
        while (currentRow < maxRows) {
            for (Block b : blocks) {
                maxRows = b.rows;
                String seatNumber = null;
                Seat s = new Seat(b.code, seatNumber);
                return s;
            }
            return null;
        }
        return null;
    }

    TreeSet<Block> blocks = new TreeSet<>();
    Map<Seat, Passenger> bookedSeats = new HashMap<>();

    public App(int[][] arr) {
        initBlocks(arr);
        initSeats();
    }

    private void initSeats() {
        Iterator<Block> itr = blocks.iterator();
        while (itr.hasNext()) {
            Block block = itr.next();
            for (int i = 0; i < block.cols; i++) {
                for (int j = 0; j < block.rows; j++) {
                    String seatNumber = i + "" + j;
                    Seat s = new Seat(block.code, seatNumber);
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

    private void initBlocks(int[][] arr) {
        int blockCode = 65;
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
    }


    private TreeMap<Block, Map<String, Passenger>> seatMatrix = new TreeMap<>();

    @Override
    public void printSeatMatrix() throws Exception {
        //        for (int block = 0; block < blocks.size(); block++) {
//            System.out.println("BLOCK-" + block);
//            int cols = arr[block][0];
//            int rows = arr[block][1];
//            Map<String, Passenger> matrix = seatMatrix.get(block);
//            for (int i = 0; i < cols; i++) {
//                for (int j = 0; j < rows; j++) {
//                    String seatNumber = i + "" + j;
//                    if (matrix == null || !matrix.containsKey(seatNumber)) {
//                        System.out.printf("%3s", "xx");
//                    } else {
//                        System.out.printf("%3s", matrix.get(seatNumber).id());
//                    }
//                }
//                System.out.println();
//            }
//        }
    }
}