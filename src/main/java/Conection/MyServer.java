package Conection;

import AllServlet.*;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

public class MyServer extends Thread {

    public static short newRequest=0;
    public static int maxSize=2;

    public MyServer(){}

    @Override
    public void run() {
        ServletContextHandler context=new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.addServlet(new ServletHolder(new ServletPing()), "/ping");
        context.addServlet(new ServletHolder(new ServletCatsList()), "/cats");
        context.addServlet(new ServletHolder(new ServletInsertCat()),"/cat");

        org.eclipse.jetty.server.Server server=new org.eclipse.jetty.server.Server(8080);
        server.setHandler(context);


        try {
            server.start();
            System.out.println("Связь с сервером установлена");
        } catch (Exception e) {
            System.out.println("Связь с сервером не установлена");
            e.printStackTrace();
        }
    }

    public int getMaxSize() {
        return maxSize;
    }

}
