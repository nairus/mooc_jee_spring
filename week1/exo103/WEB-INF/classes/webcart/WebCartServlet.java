package webcart;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

// add an annotation here to map your servlet on an URL.
@WebServlet("/cart")
public class WebCartServlet extends HttpServlet {

    Cart myCart = new Cart();

    private static final String TITLE_PAGE = "EXO 103 - Cart";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
    throws ServletException, IOException {
        // print a html form using printwriter.
        String content = "<h1>" + TITLE_PAGE + "</h1>" +
            "<form action=\"/exo103/cart\" method=\"POST\">" +
            "<div>REF: <input type=\"text\" name=\"ref\"></div>" +
            "<div>QTY: <input type=\"text\" name=\"qty\"></div>" +
            "<input type=\"submit\" value=\"Valider\">" +
            "</form>";

        this.writeResponse(res, TITLE_PAGE + " Form", content);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
    throws ServletException, IOException {

        // Get parameters, check null
        String ref = req.getParameter("ref");
        String qty = req.getParameter("qty");

        String content;
        if (this.isEmpty(ref) || this.isEmpty(qty)) {
            // print error 400
            res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            content = "Les paramètres [ref] et [qty] sont manquants";
        } else {
            // print reference and quantity
            content = "<h1>" + TITLE_PAGE + "</h1>" +
                "<div>REF: " + ref + "</div>" +
                "<div>QTY: " + qty + "</div>" ;
        }

        this.writeResponse(res, TITLE_PAGE + " Form", content);
    }

    private void writeResponse(HttpServletResponse resp, String title, String content)
        throws IOException {
        String html = "<!doctype html>\n"+
            "<html>" +
            "<head>" +
            "<meta charset=\"utf-8\">" +
            "<title>" + title + "</title>" +
            "</head>" +
            "<body>" +
            content +
            "</body>" +
            "</html>";
        PrintWriter out = resp.getWriter();
        out.write(html);
    }

    private boolean isEmpty(String str) {
        if (null == str) {
            return true;
        }

        if ("" == str) {
            return true;
        }

        return false;
    }

}
