package com.wi.airlines;

/**
 * Created by cb-vaibhav on 08/10/18.
 */
public class AppLoader implements BookingApp {

    public App startApp(int[][] arr) {
        return new App(arr);
    }

    @Override
    public Seat bookNext(Passenger p) throws Exception {
        throw new RuntimeException("not supported");
    }

    @Override
    public void printSeatMatrix() throws Exception {
        throw new RuntimeException("not supported");
    }
}
