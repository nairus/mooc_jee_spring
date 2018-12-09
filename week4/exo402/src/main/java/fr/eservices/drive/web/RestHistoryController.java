package fr.eservices.drive.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.eservices.drive.dao.DataException;
import fr.eservices.drive.dao.StatusHistory;

// define as a REST Controller in spring context
@RestController
@RequestMapping("/history")
public class RestHistoryController {

    // Inject reference from spring context
    @Autowired
    @Qualifier("mock")
    HistorySource historySource;

    // map this opetation to GET only
    @GetMapping("/{orderId}.json")
    public List<StatusHistory> getHistory(@PathVariable int orderId) {
        return historySource.orderHistory(orderId);
    }

    // map this operation to POST only
    @PostMapping("/{orderId}.json")
    public String addStatus(@PathVariable int orderId, @RequestBody StatusHistory history) {
        // try to add a status,
        // return "Ok" or "Error" if exception thrown
        try {
            historySource.addHistoryStatus(orderId, history);
            return "{\"status\": \"Ok\"}";
        } catch (DataException e) {
            return "{\"status\": \"Error\"}";
        }
    }
}
