package com.wi.airlines;

import com.sun.javafx.collections.ImmutableObservableList;
import org.omg.CORBA.INTERNAL;

import java.util.*;

/**
 * Created by cb-vaibhav on 07/10/18.
 */
public class App {

    private static App inst;

    public static App createInst(TreeSet<Block> blocks) {
        if (inst != null) {
            return inst;
        }
        inst = new App(blocks);
        return inst;
    }

    TreeSet<Block> blocks = new TreeSet<>();
    private TreeMap<Integer, List<Seat>> seatMatrix = new TreeMap<>();

    private App(TreeSet<Block> blocks) {
        this.blocks = blocks;
    }

    Map<Seat, Passenger> bookedSeats = new HashMap<>();


    public Seat bookNext(Passenger p) throws Exception {
        int currentRow = 0;
        int maxRows = Integer.MAX_VALUE;
        while (currentRow < maxRows) {
            for (Block b : blocks) {
                maxRows = b.rows;
                int seatNumber = 1;
                Seat s = new Seat(b.code, seatNumber);
                return s;
            }
            return null;
        }
        return null;
    }


    public void printSeatMatrix() throws Exception {
        for (int row : seatMatrix.keySet()) {
            List<Seat> seats = seatMatrix.get(row);
            char blockNumber = '@';

            for (Seat seat : seats) {
                boolean blockChange = blockNumber != seat.block;
                blockNumber = seat.block;
                if (blockChange) {
                    String braces = blockNumber == 'A' ? "[" : "] [";
                    System.out.print(braces);
                }

                String placeHolder = blockChange ? "%s" : "%4s";
                if (bookedSeats.containsKey(seat)) {
                    System.out.printf(placeHolder, bookedSeats.get(seat).id());
                } else {
//                    System.out.printf(placeHolder, "XX");
                    System.out.printf("%4s", (blockNumber + seat.seatNumber.toString()));
                }
            }
            System.out.println("]");
        }
    }


    private void addToSeatMatrix(int row, Seat seat) {
        if (seatMatrix.containsKey(row)) {
            seatMatrix.get(row).add(seat);
            return;
        }
        List<Seat> seats = new ArrayList<>();
        seats.add(seat);
        seatMatrix.put(row, seats);
    }

    public App(int[][] arr) {
        int blockCode = 65;// A,B,C
        for (int i = 0; i < arr.length; i++) {
            int cols = arr[i][0];
            int rows = arr[i][1];
            Integer counter = 1;
            for (int m = 0; m < cols; m++) {
                for (int n = 0; n < rows; n++) {
                    Seat seat = new Seat((char) blockCode, counter++);
                    addToSeatMatrix(m + 1, seat);
                }
            }
            blockCode++;
        }
    }
}