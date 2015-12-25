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
/**
 * Created by sao76 on 20.12.2015.
 * Главная страница
 */
public class MainPageServlet  extends HttpServlet {
    private AccountService accountService;

    public MainPageServlet(AccountService accountService) {
        this.accountService = accountService;
    }

    protected void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws ServletException, IOException {
        response.setStatus(HttpServletResponse.SC_OK);
        
        Map<String, Object> pageVariables = new HashMap<String, Object>();
        UserProfile profile = accountService.getSessionUser(request);
        
        if(profile != null) {
            pageVariables.put("authStatus", "Hello <b>" + profile.getLogin() + "</b>");
            response.getWriter().println(PageGenerator.getPage("index.html", pageVariables));
        }
        else {
            // Если юзверь не залогинен, выводим ему содержимое страницы signin.html
            response.getWriter().println(PageGenerator.getPage("signin.html"));
        }
    }
}