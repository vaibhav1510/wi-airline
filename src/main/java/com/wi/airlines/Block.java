package com.wi.airlines;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cb-vaibhav on 07/10/18.
 */
public class Block {

    public char code;
    private int seatsCount;

    public BlockMeta meta;

    private List<Seat> windowSeats = new ArrayList<>();
    private List<Seat> aisleSeats = new ArrayList<>();
    private List<Seat> middleSeats = new ArrayList<>();


    public int cols;
    public int rows;

    public Block(char code, int cols, int rows) {
        this.code = code;
        this.cols = cols;
        this.rows = rows;
        this.seatsCount = cols * rows;
    }

    public Block blockMeta(BlockMeta meta) {
        this.meta = meta;
        return this;
    }


    public void addSeat(Seat s, BlockMeta.SEAT_TYPE type) {
        switch (type) {
            case WINDOW:
                windowSeats.add(s);
                return;
            case AISLE:
                aisleSeats.add(s);
            case MIDDLE:
                middleSeats.add(s);
        }
    }

    private void init(int cols, int rows, boolean areWindowSeats) {
        for (int i = 0; i < cols; i++) {
            for (int j = 0; j < rows; j++) {
                String seatNumber = i + "" + j;
                if (i == 0 && areWindowSeats) {
                    windowSeats.add(new Seat(code, seatNumber));
                    continue;
                }
                if (i == cols - 1 && areWindowSeats) {

                }
            }
        }

    }

    @Override
    public boolean equals(Object obj) {
        return code == ((Block) obj).code;
    }

    @Override
    public int hashCode() {
        return (int) code;
    }
}
