import java.io.IOException;
import java.io.Writer;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/auth")
public class AuthServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
    throws ServletException, IOException {
        // get login / password from request parameters
        String login = req.getParameter("login");
        String password = req.getParameter("password");

        if ( login == null || password == null ) throw new ServletException("no login/password");
        boolean succeed = "admin@foo.com".equals(login) && "admin".equals(password);

        // if auth is OK,
        if (succeed) {
            // add something in session for next calls,
            HttpSession session = req.getSession();
            session.setAttribute("authenticated", true);
            session.setAttribute("login", login);
            session.setMaxInactiveInterval(180); // 3 minutes (pour test)

            // then redirect to "welcome.jsp"
             RequestDispatcher rd = req.getRequestDispatcher( "/welcome.jsp" );
            rd.forward(req, resp);
        } else {
            // if auth KO
            // set an "errorMessage" in request attribute
            req.setAttribute("errorMessage", "Login / Password invalide");
            // forward to auth.jsp with request dispatcher
            RequestDispatcher rd = req.getRequestDispatcher( "/auth.jsp" );
            rd.forward(req, resp);
        }
    }

    // allow to disconnect with a GET to /auth with any parameter "logout" value
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
    throws ServletException, IOException {
        // check for "logout" parameter
        if (null != req.getParameter("logout")) {
            //   if so : disconnect and show auth.jsp
            HttpSession session = req.getSession();
            session.setAttribute("authenticated", false);
            session.removeAttribute("login");
            // forward to welcome.jsp with request dispatcher
            RequestDispatcher rd = req.getRequestDispatcher( "/welcome.jsp" );
            rd.forward(req, resp);
        } else {
            //   if not : Error 500
            throw new ServletException("logout parameter missing");
        }
    }

}