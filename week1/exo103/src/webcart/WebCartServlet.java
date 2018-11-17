package webcart;

import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

// add an annotation here to map your servlet on an URL.
@WebServlet("/cart")
public class WebCartServlet extends HttpServlet {

    private static final String TITLE_PAGE = "EXO 103 - Cart";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
    throws ServletException, IOException {
        // get the user session
        HttpSession session = req.getSession();
        // get the cart or create it if not exists
        Cart cart = (Cart)session.getAttribute("cart");
        if (null == cart) {
            cart = new Cart();
        }
        // set attribute to the request
        req.setAttribute("cart", cart);
        // forward to cart.jsp with request dispatcher
        RequestDispatcher rd = req.getRequestDispatcher( "/WEB-INF/cart.jsp" );
        rd.forward(req, res);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
    throws ServletException, IOException, NumberFormatException {
        String pageTitle = TITLE_PAGE + " POST";

        // Get parameters, check null
        String ref = req.getParameter("ref");
        String qty = req.getParameter("qty");

        String content;
        // check if the ref is not blank and the qty is a numeric
        if (StringUtils.isNotBlank(ref) && StringUtils.isNumeric(qty)) {
            // get the user session
            HttpSession session = req.getSession();
            // get the cart or create it if not exists
            Cart cart = (Cart)session.getAttribute("cart");
            if (null == cart) {
                cart = new Cart();
            }
            // add item in the cart
            cart.addToCart(ref, Integer.parseInt(qty));
            // set the cart to the user session
            session.setAttribute("cart", cart);
            // send redirect directive to GET /cart
            res.sendRedirect(req.getContextPath() + "/cart");
        } else {
            content = "<h2>ERROR 400 :</h2>";
            if (StringUtils.isBlank(ref)) {
                content += "<p>Le paramère [ref] est invalide</p>";
            }

            if(!StringUtils.isNumeric(qty)) {
                content += "<p>Le paramètre [qty] doit être un entier</p>";
            }

            // print error 400
            res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            this.buildAndWriteResponse(res, pageTitle, content);
        }
    }

    private void buildAndWriteResponse(HttpServletResponse resp, String title, String content)
        throws IOException {
            PrintWriter out = this.prepareResponse(resp, title);
            this.appendTitle(out);
            out.append(content);
            this.writeResponse(out);
    }

    private void buildAndWriteResponse(HttpServletResponse resp, String title, String content, Cart cart)
        throws IOException {
            PrintWriter out = this.prepareResponse(resp, title);
            this.appendTitle(out);
            if (null != cart && !cart.isEmpty()) {
                out.append("<h3>Récapitulatif du panier</h3>\n");
                cart.print(out);
            }
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
