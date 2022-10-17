package multithreading.myTestsPlusModule.module;

import java.util.*;

public class ParkingLot {
    private int limitOfCars;
    private List<Thread> parkedCars = new ArrayList<Thread>();
    private List<Thread> visitedCars = new ArrayList<Thread>();

    public ParkingLot(int limitOfCars){
        this.limitOfCars = limitOfCars;
    }

    public List<Thread> getVisitedCars(){
        return visitedCars;
    }

    public synchronized boolean accept(Car thread) throws InterruptedException{
        while(limitOfCars == 0) {
            wait(thread.getWaitTime());
            if(limitOfCars == 0) {
                return false;
            }
            else break;
        }
        limitOfCars--;
        parkedCars.add(thread);
        notifyAll();
        return true;
    }

    public synchronized void release(Car thread){
        if(parkedCars.contains(thread)){
            visitedCars.add(thread);
            limitOfCars++;
            parkedCars.remove(thread);
        }
    }

}
