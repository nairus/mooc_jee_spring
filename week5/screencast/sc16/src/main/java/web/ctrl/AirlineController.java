package web.ctrl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import dao.AirlineRepository;
import flights.Airline;

@Controller
public class AirlineController {

    private static Logger log = LoggerFactory.getLogger(AirlineController.class);

    // inject an AirlineRepository
    @Autowired
    AirlineRepository repository;

    @RequestMapping("/airline/{id}")
    public String findAirline(Model model, @PathVariable int id) {
        log.debug("GET Airline info {}", id);

        // TODO : Use repository to get airline
        Airline airline = repository.findById(id);

        model.addAttribute("airline", airline);
        return "airline-info";
    }

    @RequestMapping("/airlines")
    public String list(Model model, @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "") String country) {
        log.debug("GET Airlines page {} for country {}", page, country);
        int size = 100;
        page--; // because spring pagination starts at 0.

        // use repository to get list of airline
        PageRequest pageRequest = new PageRequest(page, size);
        Page<Airline> list;
        if (StringUtils.isEmpty(country)) {
            list = repository.findAll(pageRequest);
            log.debug("findAll : {} airlines found", list.getTotalElements());
        } else {
            list = repository.findByCountry(country, pageRequest);
            log.debug("findByCountry : {} airlines found", list.getTotalElements());
        }

        model.addAttribute("list", list);

        // use repository to get countries
        List<String> countries = repository.selectDistinctCountries();
        model.addAttribute("countries", countries);

        return "airline-list";
    }

}
