package web.ctrl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import dao.AirlineDao;
import flights.Airline;

// set as a controller
@Controller
public class AirlineController {

    private static Logger log = LoggerFactory.getLogger(AirlineController.class);

    // inject dao
    @Autowired
    AirlineDao airlineDao;

    // map to URL "/airline/{id}"
    @RequestMapping("/airline/{id}")
    public String findAirline(
            // TODO : inject model
            // define id as a parameter from URL
            @PathVariable int id) {
        log.debug("GET Airline info {}", id);
        Airline airline = airlineDao.find(id);
        // TODO : use model to set airline attribute

        return "airline-info";
    }

}
