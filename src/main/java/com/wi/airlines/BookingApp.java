package com.wi.airlines;

/**
 * Hello world!
 */
public interface BookingApp {
    public Seat bookNext(Passenger p) throws Exception;

    public void printSeatMatrix() throws Exception;

}
