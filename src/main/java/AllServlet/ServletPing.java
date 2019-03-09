package AllServlet;

import Conection.MyServer;
import Start.Main;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.Semaphore;

public class ServletPing extends HttpServlet {

    public Semaphore semaphore=Main.semaphoreRequset;


    public void doGet(HttpServletRequest reguest, HttpServletResponse response) throws IOException {


        if(semaphore.availablePermits()==0){
            response.getOutputStream().println("429 Too Many Requests");
            response.getOutputStream().flush();
            response.getOutputStream().close();
        }
        else {

            try {
                semaphore.acquire();
                System.out.println("запрос обрабатывается");
                MyServer.newRequest++;
                response.setCharacterEncoding("UTF-8");
                response.getWriter().println("Cats Service. Version 0.1");
                Thread.sleep(20000);
                MyServer.newRequest--;
                semaphore.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

}
