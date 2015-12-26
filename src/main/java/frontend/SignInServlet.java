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

public class SignInServlet  extends HttpServlet {
    private AccountService accountService;

    public SignInServlet(AccountService accountService) {
        this.accountService = accountService;
    }

    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        response.setStatus(HttpServletResponse.SC_OK);
        
        Map<String, Object> pageVariables = new HashMap<String, Object>();
        UserProfile profile = accountService.getUser(login);
        
        if (profile != null && profile.getPassword().equals(password)) {
            // Успешная авторизация на сайте
            pageVariables.put("loginStatus", "<b>" + login + "</b>, you have successfully logged");
            accountService.addSessionUser(request, profile);   // Добавляем в сессию
        } else {
            pageVariables.put("loginStatus", "Wrong login/password");
        }
        response.getWriter().println(PageGenerator.getPage("loginstatus.html", pageVariables));
    }
}