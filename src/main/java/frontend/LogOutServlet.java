package frontend;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import servlets.UserProfile;
import servlets.AccountService;
import templater.PageGenerator;

public class LogOutServlet  extends HttpServlet {
    private AccountService accountService;

    public LogOutServlet(AccountService accountService) {
        this.accountService = accountService;
    }

    protected void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws ServletException, IOException {
        response.setStatus(HttpServletResponse.SC_OK);
        
        Map<String, Object> pageVariables = new HashMap<String, Object>();
        UserProfile profile = accountService.getSessionUser(request);
        
        if(profile != null) {
            accountService.removeSessionUser(request, profile);
            pageVariables.put("logOutStatus", "You exit from this site");
        }
        else {
            pageVariables.put("logOutStatus", "You are not logined in this site");
        }
        response.getWriter().println(PageGenerator.getPage("logoutstatus.html", pageVariables));
    }
}