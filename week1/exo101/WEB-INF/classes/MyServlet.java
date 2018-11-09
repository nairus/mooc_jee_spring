import java.io.IOException;
import java.io.Writer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.DecimalFormat;

@WebServlet("/dist")
public class MyServlet extends HttpServlet {

    private static final String TITLE_PAGE = "Exo1 - Calculer la distance entre 2 points";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
    throws ServletException, IOException {
        String content = "<h1>" + TITLE_PAGE + "</h1>" +
            "<form method=\"POST\" action=\"/exo101/dist\">" +
            "<div>Point 1: <input type=\"text\" name=\"p1lat\">,<input type=\"text\" name=\"p1lng\"></div>" +
            "<div>Point 2: <input type=\"text\" name=\"p2lat\">,<input type=\"text\" name=\"p2lng\"></div>" +
            "<input type=\"submit\" value=\"Envoyer\">" +
            "</form>";

        this.writeResponse(resp, TITLE_PAGE + " - Form", content);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
    throws ServletException, IOException {
        // get first point latitude / longitude
        String p1lat = req.getParameter("p1lat");
        String p1lng = req.getParameter("p1lng");

       // get second point latitude / longitude
        String p2lat = req.getParameter("p2lat");
        String p2lng = req.getParameter("p2lng");

        // compute distance between two points
        double lat1 = Double.parseDouble(p1lat);
        double lng1 = Double.parseDouble(p1lng);
        double lat2 = Double.parseDouble(p2lat);
        double lng2 = Double.parseDouble(p2lng);
        double dist = this.distance(lat1, lng1, lat2, lng2);

        DecimalFormat df = new DecimalFormat("#.#");
        // display distance, in kilometer with 1 decimal
        String content = "<h1>" + TITLE_PAGE + "</h1>" +
            "<p>Distance calculée : " +
            df.format(dist) + " km</p>";

        writeResponse(resp, TITLE_PAGE + " - Result", content);
    }

    private static final int EARTH_RADIUS = 6371; // Approx Earth radius in KM

    private double distance(double startLat, double startLong, double endLat, double endLong) {
        double dLat  = Math.toRadians((endLat - startLat));
        double dLong = Math.toRadians((endLong - startLong));

        startLat = Math.toRadians(startLat);
        endLat   = Math.toRadians(endLat);

        double a = haversin(dLat) + Math.cos(startLat) * Math.cos(endLat) * haversin(dLong);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return EARTH_RADIUS * c; // <-- d
    }

    private double haversin(double val) {
        return Math.pow(Math.sin(val / 2), 2);
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
        Writer out = resp.getWriter();
        out.write(html);
    }

}