package com.wi.airlines;

/**
 * Created by cb-vaibhav on 07/10/18.
 */
public class Seat {

    public char block;
    public Integer seatNumber;

    public Seat(char block, Integer seatNumber) {
        this.block = block;
        this.seatNumber = seatNumber;
    }

    @Override
    public boolean equals(Object obj) {
        Seat s = (Seat) obj;
        return block == s.block && seatNumber.equals(s.seatNumber);
    }

    @Override
    public int hashCode() {
        int code = (int) block + seatNumber;
        return code;
    }
}