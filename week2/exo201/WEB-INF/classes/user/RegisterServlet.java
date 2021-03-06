package user;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.validator.routines.EmailValidator;


import java.sql.SQLException;

import java.util.Map;
import java.util.HashMap;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import user.User;
import user.UserDao;
import user.UserDaoSqlite;

@WebServlet(urlPatterns="/register")
public class RegisterServlet extends HttpServlet {

    private static final String TITLE_PAGE = "EXO 201 - Register";
    private static final String FIRST_NAME_PARAM = "firstname";
    private static final String LAST_NAME_PARAM = "lastname";
    private static final String EMAIL_PARAM = "email";
    private static final String PASSWORD_PARAM = "password";
    private static final String PASSWORD_CONFIRM_PARAM = "passwordConfirm";

    private static final EmailValidator emailValidator = EmailValidator.getInstance();

    private UserDao userDao;

    @Override
    public void init() throws ServletException {
        try {
            this.userDao = new UserDaoSqlite("../users.db");
        } catch (SQLException e) {
            throw new ServletException("An error occured when try to connect to the database: users.db" , e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // get the user session
        HttpSession session = req.getSession();

        // add the user registered
        User user = (User)session.getAttribute("user");

        if (null == user) {
            // forward to register.jsp with request dispatcher
            RequestDispatcher rd = req.getRequestDispatcher( "/register.jsp" );
            rd.forward(req, resp);
        } else {
            // we write a success register message
            String content = "<h2>Thank you " + user.getFirstname() + " " + user.getLastname() + "!</h2>" +
                "<p>You has been registered successfully!</p>";
            buildAndWriteResponse(resp, TITLE_PAGE + " Success", content);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            // populate the user
            User newUser = this.populateUser(req);

            // validate the form
            Map<String,String> errors = this.validate(req);

            // If there is errors we redirect on the form with errors map
            if (errors.size() > 0) {
                req.setAttribute("errors", errors);
                req.setAttribute("user", newUser);

                // forward to register.jsp with request dispatcher
                RequestDispatcher rd = req.getRequestDispatcher( "/register.jsp" );
                rd.forward(req, resp);
            } else {
                // register the user
                String password = req.getParameter(PASSWORD_PARAM);
                this.userDao.add(newUser, password);
                // get the user session
                HttpSession session = req.getSession();
                // add the user registered
                session.setAttribute("user", newUser);
                // redirect in GET page /register
                resp.sendRedirect(req.getContextPath() + "/register");
            }
        } catch (SQLException e) {
            // 503 : Service unavailable
            resp.setStatus(HttpServletResponse.SC_SERVICE_UNAVAILABLE );
            buildAndWriteResponse(resp, TITLE_PAGE + " Error", "Oups! An error occured! Please try later!");
        }
    }

    private User populateUser(HttpServletRequest req) {
        User user = new User();
        user.setFirstname(req.getParameter(FIRST_NAME_PARAM));
        user.setLastname(req.getParameter(LAST_NAME_PARAM));
        user.setEmail(req.getParameter(EMAIL_PARAM));
        return user;
    }

    private Map<String,String> validate(HttpServletRequest req) throws SQLException {
        // user values to validate
        String firstName = req.getParameter(FIRST_NAME_PARAM);
        String lastName = req.getParameter(LAST_NAME_PARAM);
        String email = req.getParameter(EMAIL_PARAM);
        String password = req.getParameter(PASSWORD_PARAM);
        String passwordConfirm = req.getParameter(PASSWORD_CONFIRM_PARAM);

        // init the errors map
        Map<String, String> errors = new HashMap<>();

        if (StringUtils.isBlank(firstName)) {
            errors.put(FIRST_NAME_PARAM, "The firstname is required");
        }

        if (StringUtils.isBlank(lastName)) {
            errors.put(LAST_NAME_PARAM, "The lastName is required");
        }

        if (!emailValidator.isValid(email)) {
            errors.put(EMAIL_PARAM, "The email is not valid");
        } else {
            // check if the email already exists in database
            User user = this.userDao.findByEmail(email);
            if (null != user) {
                errors.put(EMAIL_PARAM, "The email already exists");
            }
        }

        if (StringUtils.isNotBlank(password)) {
            if (!password.equals(passwordConfirm)) {
                errors.put(PASSWORD_CONFIRM_PARAM, "The password must be confirmed");
            }
        } else {
            errors.put(PASSWORD_PARAM, "The password is required");
        }

        return errors;
    }

    private void buildAndWriteResponse(HttpServletResponse resp, String title, String content)
        throws IOException {
            PrintWriter out = this.prepareResponse(resp, title);
            this.appendTitle(out);
            out.append(content);
            this.writeResponse(out);
    }

    private void appendTitle(PrintWriter out) {
        out.append("<h1>" + TITLE_PAGE + "</h1>\n");
    }

    private PrintWriter prepareResponse(HttpServletResponse resp, String title)
        throws IOException {
            PrintWriter out = resp.getWriter();
            out.append("<!doctype html>\n");
            out.append("<html><head>\n");
            out.append("<meta charset=\"utf-8\">\n");
            out.append("<title>" + title + "</title>\n");
            out.append("</head><boby>\n");
            return out;
    }

    private void writeResponse(PrintWriter out) {
        out.append("</body></html>");
        out.flush();
    }
}
