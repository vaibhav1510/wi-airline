package com.wi.airlines;

/**
 * Created by cb-vaibhav on 07/10/18.
 */
public class Seat {

    private char block;
    private String seatNumber;

    public Seat(char block, String seatNumber) {
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
        String code = block + seatNumber;
        return Integer.parseInt(code);
    }
}