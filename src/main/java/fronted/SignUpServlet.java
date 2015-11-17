package fronted;

import servlets.AccountService;
import servlets.UserProfile;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by zak on 14.11.2015.
 */
public class SignUpServlet {

    private AccountService accountService;

    public SignUpServlet(AccountService accountService) { this.accountService}

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String password = request.getParameter("password");

        Map<String,Object> pageVariables = new HashMap<String, Object>();
        if (accountService.addUser(name,new UserProfile(name, password, ""))) {
            pageVariables.put("SignUpStatus", "New user Created");
        } else {
            pageVariables.put("SignUpStatus", "User with name: "+name+" is not found");
        }

        response.getWriter().println(PageGenerator.getPage("signupstatus.html"));
        response.setStatus(HttpServletResponse.SC_OK);s

    }
    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws ServletException, IOException {

    }
}
