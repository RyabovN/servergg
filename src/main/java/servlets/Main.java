package servlets;

import  org.eclipse.jetty.server.*;
import  fronted.SignInServlet;
import  fronted.SignUpServlet;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;

import javax.servlet.Servlet;
import javax.servlet.ServletContext;

/**
 * Created by zak on 14.11.2015.
 */
public class Main {
    public static void main(String[] args) throws  Exception{
        int port = 8080;
        System.out.append("Starting at port: ").append(String.valueOf(port));

        AccountService accountService = new AccountService();

        Servlet signin = new SignInServlet(accountService);
        Servlet signup = new SignUpServlet(accountService);

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.addServlet(new ServletHolder(signin), "auth/signin");
        context.addServlet(new ServletHolder(signup), "auth/signup");

        ResourceHandler resource_handler = new ResourceHandler();
        resource_handler.setDirectoriesListed(true);
        resource_handler.setResourceBase("public_html");

        HandlerList handlers = new HandlerList();
        handlers.setHandlers(new Handler[](resource_handler, context));

        Server server = new Server(port);
        server.setHandler(handlers);

        server.start();

        server.join();



    }
}
