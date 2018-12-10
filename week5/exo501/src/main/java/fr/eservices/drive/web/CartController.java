package fr.eservices.drive.web;

import java.io.ByteArrayOutputStream;
import java.io.PrintWriter;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import fr.eservices.drive.dao.ArticleDao;
import fr.eservices.drive.dao.CartDao;
import fr.eservices.drive.dao.DataException;
import fr.eservices.drive.model.Article;
import fr.eservices.drive.model.Cart;
import fr.eservices.drive.model.Order;
import fr.eservices.drive.repository.OrderRepository;
import fr.eservices.drive.web.dto.CartEntry;
import fr.eservices.drive.web.dto.SimpleResponse;
import fr.eservices.drive.web.dto.SimpleResponse.Status;

@Controller
@RequestMapping(path = "/cart")
public class CartController {

    @Autowired
    @Qualifier("mock")
    CartDao daoCart;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    @Qualifier("mock")
    ArticleDao articleDao;

    @ExceptionHandler(DataException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public String dataExceptionHandler(Exception ex) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintWriter w = new PrintWriter(out);
        ex.printStackTrace(w);
        w.close();
        return "ERROR" + "<!--\n" + out.toString() + "\n-->";
    }

    @GetMapping(path = "/{id}.html", produces = MediaType.TEXT_HTML_VALUE + "; UTF-8")
    public String getCart(@PathVariable(name = "id") int id, Model model) throws DataException {
        if (0 > id) {
            throw new DataException("Bad id: " + id);
        }

        // get cart from dao
        Cart cart = daoCart.getCartContent(id);
        // assign to model var "cart"
        model.addAttribute("cart", cart);
        // return view name to display content of /WEB-INF/views/_cart_header.jsp

        return "_cart_header";
    }

    @ResponseBody
    @PostMapping(path = "/{id}/add.json", consumes = "application/json;charset=UTF-8")
    public SimpleResponse add(@PathVariable(name = "id") int id, @RequestBody CartEntry art) {
        SimpleResponse res = new SimpleResponse();

        /*
         * System.out.println( "********************\n" + "***** " +
         * String.format("Add Article %d x [%s] to cart", art.getQty(), art.getId()) +
         * "\n" + "********************" );
         */

        res.message = "Ok";
        res.status = Status.OK;

        Article articleFull = articleDao.find(art.getId());
        // case 1: article doesn't exist
        if (null == articleFull) {
            res.status = Status.ERROR;
            res.message = "Article [" + art.getId() + "] not exists.";
        }

        // case 2: qty < 0
        if (art.getQty() < 0) {
            res.status = Status.ERROR;
            res.message = "Qantity invalid.";
        }

        // Add to the cart
        try {
            Cart cart = daoCart.getCartContent(id);
            cart.getArticles().add(articleFull);
        } catch (DataException e) {
            res.status = Status.ERROR;
            res.message = "An error occurs when adding the article " + articleFull.getName() + " ("
                    + articleFull.getId() + ")";
        }
        return res;
    }

    @RequestMapping("/{id}/validate.html")
    public String validateCart(@PathVariable(name = "id") int id, Model model) throws DataException {

        // get cart by its id
        Cart cart;
        cart = daoCart.getCartContent(id);
        // create an order
        Order order = new Order();
        order.setCurrentStatus(fr.eservices.drive.dao.Status.ORDERED);
        // for each article, add it to the order
        for (Article article : cart.getArticles()) {
            order.getArticles().add(article.getId());
        }
        // set order date
        order.setCreatedOn(new Date());
        order.setCustomerId("chuckNorris");
        // set order amount (sum of each articles' price)
        int amount = cart.getArticles().stream().map(Article::getPrice).reduce(0, (price, price2) -> price + price2);
        order.setAmount(amount);
        // persist everything
        orderRepository.save(order);
        // redirect user to list of orders
        return "redirect:/order/ofCustomer/chuckNorris.html";
    }
}
