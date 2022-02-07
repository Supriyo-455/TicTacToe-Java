package utils;

public class Time {
    public static double timeStarted = System.nanoTime();

    // method to get time since timeStarted
    public static double getTime(){
        return (System.nanoTime() - timeStarted)*1E-9;
    }
}