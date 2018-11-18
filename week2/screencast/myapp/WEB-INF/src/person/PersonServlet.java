package person;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns= {"/personnes.html"})
public class PersonServlet extends HttpServlet {

    PersonDao dao;

    @Override
    public void init() throws ServletException {
        System.out.println(
            "***********************************\n" +
            "***** servlet PersonServlet init() \n" +
            "***********************************\n"
        );

        dao = new PersonSQLiteImpl("Persons.db");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
    throws ServletException, IOException {

        req.setAttribute("persons", dao.listAll());
        req.getRequestDispatcher("/WEB-INF/jsp/person-list.jsp").forward(req, resp);
    }

}
