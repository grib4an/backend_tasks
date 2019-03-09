package AllServlet;

import Conection.MyServer;
import Controller.Controller;
import Start.Main;
import Model.Cat;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;
import java.util.concurrent.Semaphore;

public class ServletInsertCat extends HttpServlet {
    private static Gson json= new GsonBuilder().setPrettyPrinting().create();
    public Semaphore semaphore=Main.semaphoreRequset;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {


        System.out.println(req.getParameter("-d"));


        if(semaphore.availablePermits()==0) {
            resp.getOutputStream().println("429 Too Many Requests");
            resp.getOutputStream().flush();
            resp.getOutputStream().close();
        }
        else {
            try {
                semaphore.acquire();
                System.out.println("запрос обрабатывается");
                MyServer.newRequest++;

                resp.setCharacterEncoding("UTF-8");

                Set<String> set = req.getParameterMap().keySet();
                String j = set.iterator().next();

                try {
                    Cat cat = json.fromJson(j, Cat.class);
                    System.out.println(cat);
                    String result = cat.checkValues();
                    if (!result.startsWith("i")) {
                        resp.getOutputStream().println(Controller.insertCat(cat));
                        resp.getOutputStream().flush();
                        resp.getOutputStream().close();
                    } else {
                        resp.getOutputStream().println(result);
                        resp.getOutputStream().flush();
                        resp.getOutputStream().close();
                    }
                } catch (Exception e) {
                    resp.getOutputStream().print("\ninvalid data format\n");
                }

                MyServer.newRequest--;
                semaphore.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

    }
}
