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
/**
 * Created by zak on 14.11.2015.
 */
public class SignInServlet  extends HttpServlet {
    private AccountService accountService;

    public SignInServlet(AccountService accountService) {
        this.accountService = accountService;
    }

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws ServletException, IOException {
        response.setStatus(HttpServletResponse.SC_OK);

        Map<String, Object> pageVariables = new HashMap<String,Object>();
        pageVariables.put("login", request.getParameter("login") != null
                ? request.getParameter("login") : "");
        pageVariables.put("password", request.getParameter("password") != null
                ?  request.getParameter("password") : "");
        response.getWriter().println(PageGenerator.getPage("authresponse.txt", pageVariables));
    }

    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        response.setStatus(HttpServletResponse.SC_OK);
        Map<String, Object> pageVariables = new HashMap<String, Object>();
        UserProfile profile = accountService.getUser(login);
        if (profile != null && profile.getPassword().equals(password)) {
            pageVariables.put("loginStatus", "You have successfully logged");
        } else {
            pageVariables.put("loginStatus", "Wrong login/password");
        }
        response.getWriter().println(PageGenerator.getPage("authstatus.html", pageVariables));
    }
}