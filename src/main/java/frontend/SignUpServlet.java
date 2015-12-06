package frontend;

import servlets.AccountService;
import servlets.UserProfile;
import templater.PageGenerator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by zak on 14.11.2015.
 */
public class SignUpServlet extends HttpServlet {

    private AccountService accountService;

    public SignUpServlet(AccountService accountService) {
        this.accountService = accountService;
    }

    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("login");
        String password = request.getParameter("password");

        Map<String, Object> pageVariables = new HashMap<String,Object>();
        if (accountService.addUser(name, new UserProfile(name, password, ""))) {
            pageVariables.put("signUpStatus", "New user <b> " + name + " </b>created");
        } else {
            pageVariables.put("signUpStatus", "User with name:<b> " + name + "</b> already exists");
        }

        response.getWriter().println(PageGenerator.getPage("regstatus.html", pageVariables));
        response.setStatus(HttpServletResponse.SC_OK);
    }
}