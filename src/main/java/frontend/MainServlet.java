package frontend;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import servlets.AccountService;
import templater.PageGenerator;
import servlets.UserProfile;

public class MainPageServlet  extends HttpServlet {
    private AccountService accountService;

    public MainPageServlet(AccountService accountService) {
        this.accountService = accountService;
    }

    protected void doPost(HttpServletRequest request,
                      HttpServletResponse response) throws ServletException, IOException {
        response.setStatus(HttpServletResponse.SC_OK);
        
        Map<String, Object> pageVariables = new HashMap<String, Object>();
        
    }
}