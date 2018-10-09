package com.wi.airlines;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by cb-vaibhav on 08/10/18.
 */
public class BlockMeta {

    private Block block;
    public boolean mostLeft;
    private Set<SEAT_TYPE> seatTypes = new HashSet<>();

    enum SEAT_TYPE {
        WINDOW, AISLE, MIDDLE;

        boolean is(SEAT_TYPE seatType) {
            return name().equalsIgnoreCase(seatType.name());
        }
    }

    public BlockMeta(Block block) {
        this.block = block;
    }

    public BlockMeta addSeatType(SEAT_TYPE seatType) {
        if (seatTypes.contains(seatType)) {
            return this;
        }
        this.seatTypes.add(seatType);
        return this;
    }

}
