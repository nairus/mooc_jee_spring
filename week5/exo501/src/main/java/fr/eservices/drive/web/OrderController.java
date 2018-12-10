package fr.eservices.drive.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import fr.eservices.drive.model.Order;
import fr.eservices.drive.repository.OrderRepository;

@Controller
@RequestMapping(path = "/order")
public class OrderController {

    // Autowire this
    @Autowired
    OrderRepository repoOrder;

    @RequestMapping(path = "/ofCustomer/{custId}.html", produces = MediaType.TEXT_HTML_VALUE + "; UTF-8")
    public String list(@PathVariable String custId, Model model) {

        // use repo to get orders of a customer
        List<Order> orders = repoOrder.findByCustomerId(custId);
        // assign in model as "orders"
        model.addAttribute("orders", orders);
        // return order list view

        return "order_list";
    }

}
