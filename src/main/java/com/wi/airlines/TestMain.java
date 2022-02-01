package com.wi.airlines;

/**
 * Created by cb-vaibhav on 07/10/18.
 */
public class TestMain {

    public static void main1(String[] args) throws Exception {
        int[][] arr = {{3, 2}, {3, 4}, {2, 3}};
        App app = new AppLoader().startApp(arr);

        int numOfPassangers = 21;
        for (int i = 0; i < numOfPassangers; i++) {
            Seat s = app.bookNext(new Passenger(i + 1));
            if (s == null) {
                System.out.println("\nNO MORE SEAT AVAILABLE");
                app.printSeatMatrix();
                System.exit(0);
            }
        }
    }

    public static void main(String[] args) throws Exception {
        int[][] arr = {{3, 2}, {3, 4}, {2, 3}};
        new App(arr).printSeatMatrix();
    }

}
