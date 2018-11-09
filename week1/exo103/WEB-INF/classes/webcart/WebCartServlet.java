package webcart;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// TODO: add an annotation here to map your servlet on an URL.
public class WebCartServlet extends HttpServlet {
	
	Cart myCart = new Cart();
	

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) 
	throws ServletException, IOException {
		PrintWriter out = res.getWriter();
		
		// TODO : print a html form using printwriter.
	}

	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) 
	throws ServletException, IOException {
		PrintWriter out = res.getWriter();
		
		// TODO : Get parameters, check null
		
		// TODO : print reference and quantity

	}
	
	
	

}
