package servlets;

import frontend.SignInServlet;
import frontend.SignUpServlet;
import frontend.LogOutServlet;
import frontend.MainServlet;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import javax.servlet.Servlet;

public class Main {
    public static void main(String[] args) throws  Exception{
        int port = 8088;
        System.out.append("Starting at port: ").append(String.valueOf(port));

        AccountService accountService = new AccountService();
        Servlet signin = new SignInServlet(accountService);
        Servlet signup = new SignUpServlet(accountService);
        Servlet logout = new LogOutServlet(accountService);
        Servlet main = new MainServlet(accountService);

        ServletContextHandler  context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.addServlet(new ServletHolder(signin), "/auth/signin");
        context.addServlet(new ServletHolder(signup), "/auth/signup");
        context.addServlet(new ServletHolder(main), "");
        context.addServlet(new ServletHolder(logout), "/auth/logout");

        ResourceHandler resource_handler = new ResourceHandler();
        resource_handler.setDirectoriesListed(true);
        resource_handler.setResourceBase("\\public_html");

        HandlerList handlers = new HandlerList();
        handlers.setHandlers(new Handler[]{resource_handler, context});

        Server server = new Server(port);
        server.setHandler(handlers);

        server.start();
        server.join();

    }
}
