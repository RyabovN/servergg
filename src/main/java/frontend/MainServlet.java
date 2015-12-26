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

public class MainServlet  extends HttpServlet {
    private AccountService accountService;

    public MainServlet(AccountService accountService) {
        this.accountService = accountService;
    }

    protected void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws ServletException, IOException {
        response.setStatus(HttpServletResponse.SC_OK);
        
        Map<String, Object> pageVariables = new HashMap<String, Object>();
        UserProfile profile = accountService.getSessionUser(request);
        
        if(profile != null) {
            pageVariables.put("authStatus", "Welcome, " + profile.getLogin() + "! Nice to meet u");
            response.getWriter().println(PageGenerator.getPage("index.html", pageVariables));
        }
        else {
            response.getWriter().println(PageGenerator.getBackPage("signin.html"));
        }
    }
}