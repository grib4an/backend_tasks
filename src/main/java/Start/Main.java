package Start;


import Conection.MyServer;
import Task1.StartFirstTask;
import Task2.StartSecondTask;

import java.util.concurrent.Semaphore;

public class Main {

    private static MyServer createMyServer =new MyServer();
    public static Semaphore semaphoreRequset=new Semaphore(createMyServer.getMaxSize());

    public static void main(String[] args) throws InterruptedException {

        StartFirstTask firstTask=new StartFirstTask();
        StartSecondTask secondTask=new StartSecondTask();

        createMyServer.start();

        createMyServer.join();
    }

}
