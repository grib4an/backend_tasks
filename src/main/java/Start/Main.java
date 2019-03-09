package Start;


import Conection.MyServer;
import Model.Cat;
import Task1.StartFirstTask;
import Task2.StartSecondTask;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.nio.charset.Charset;
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
