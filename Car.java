package multithreading.myTestsPlusModule.module;

public class Car extends Thread{

    private int waitTime;
    private int startTime;
    private int parkedTime;
    private ParkingLot parkingLot;
    private static int threadQ = 0;
    private int number = ++threadQ;

    public Car(int waitTime, int sTime, int pTime, ParkingLot pLot){
        this.waitTime= waitTime;
        this.startTime = sTime;
        this.parkedTime = pTime;
        this.parkingLot = pLot;
        start();
    }

    public int getWaitTime(){
        return waitTime;
    }

    public void run(){
        try{
            wait(startTime);
            boolean isAccepted = parkingLot.accept(this);
            if(isAccepted){
                wait(parkedTime);
                parkingLot.release(this);
            } else{
                System.out.println(this + "can't wait more");
            }
        } catch (InterruptedException e){
            e.getStackTrace();
        }

    }
    public void wait(int seconds) throws InterruptedException{
        Thread.sleep(seconds*1000);
    }

    @Override
    public String toString(){
        return "Thread #" + number
                + " waitTime: " + waitTime
                + " startTime: " + startTime
                + " parkedTime: " + parkedTime
                + "\n";
    }

}
