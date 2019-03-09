package AllServlet;


import Conection.MyServer;
import Controller.Controller;
import Start.Main;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.Semaphore;

public class ServletCatsList extends HttpServlet {

    public Semaphore semaphore= Main.semaphoreRequset;

    public void doGet(HttpServletRequest reguest, HttpServletResponse response) throws IOException {


        if(semaphore.availablePermits()==0){
            response.getOutputStream().println("429 Too Many Requests");
            response.getOutputStream().flush();
            response.getOutputStream().close();
        }else {
            try {
                semaphore.acquire();
                System.out.println("запрос обрабатывается");
                MyServer.newRequest++;

                response.setCharacterEncoding("UTF-8");

                if (reguest.getParameterMap().size() == 0) {
                    response.getOutputStream().print(String.valueOf(Controller.catsList()));
                    response.getOutputStream().flush();
                    response.getOutputStream().close();

                } else if (reguest.getParameter("attribute") != null && reguest.getParameter("order") != null &&
                        reguest.getParameter("offset") != null && reguest.getParameter("limit") != null) {
                    response.getOutputStream().println(String.valueOf(Controller.catsListAllRequest(reguest.getParameter("attribute"), reguest.getParameter("order"),
                            reguest.getParameter("offset"), reguest.getParameter("limit"))));
                    response.getOutputStream().flush();
                    response.getOutputStream().close();

                } else if (reguest.getParameter("attribute") != null && reguest.getParameter("order") != null) {
                    response.getOutputStream().println(String.valueOf(Controller.catsListOrder(reguest.getParameter("attribute"), reguest.getParameter("order"))));
                    response.getOutputStream().flush();
                    response.getOutputStream().close();

                } else if (reguest.getParameter("offset") != null && reguest.getParameter("limit") != null) {
                    response.getOutputStream().println(String.valueOf(Controller.catsListLimit(reguest.getParameter("offset"), reguest.getParameter("limit"))));
                    response.getOutputStream().flush();
                    response.getOutputStream().close();
                } else {
                    response.getOutputStream().print("\nincorrectly entered request\n enter request again\n");
                    response.getOutputStream().flush();
                    response.getOutputStream().close();
                }

                MyServer.newRequest--;
                semaphore.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }


}
