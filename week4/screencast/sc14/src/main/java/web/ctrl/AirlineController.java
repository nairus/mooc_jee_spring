package web.ctrl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import dao.AirlineDao;

// TODO : set as a controller
@Controller
public class AirlineController {

    private static Logger log = LoggerFactory.getLogger(AirlineController.class);

    // TODO : inject dao
    AirlineDao airlineDao;

    // TODO : map to URL "/airline/{id}"
    @RequestMapping("/airline/{id}")
    public String findAirline(
            // TODO : inject model
            // TODO : define id as a parameter from URL
            @PathVariable int id) {
        log.debug("GET Airline info {}", id);
        // Airline airline = airlineDao.find(id);
        // TODO : use model to set airline attribute

        return "airline-info";
    }

}
