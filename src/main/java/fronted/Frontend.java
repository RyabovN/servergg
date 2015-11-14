package fronted;

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
public class Frontend extends HttpServlet {
    private String login = "";

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws ServletException, IOException {
        Map<String, Object> pageVariables = new HashMap<String,Object>();
        pageVariables.put("lastLogin", login == null ? "" : login);
        response.getWriter().println(PageGenerator.);

    }
    public void doPost(HttpServletRequest request,
                      HttpServletResponse response) throws ServletException, IOException {

    }

}
