package de.schwarz.integration.biketrip.controller

import de.schwarz.integration.biketrip.entity.BikeTrip
import de.schwarz.integration.biketrip.service.BikeTripService
import de.schwarz.integration.util.DateUtil
import org.springframework.data.domain.Page
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/bike-trip")
class BikeTripController {

    BikeTripService bikeTripService;

    BikeTripController(BikeTripService bikeTripService) {
        this.bikeTripService = bikeTripService;
    }

    @GetMapping("/findAllTrips")
    ResponseEntity<Page<BikeTrip>> findAllTrips() {
        return new ResponseEntity<>(bikeTripService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/saveAllTrips")
    def saveAllTrips(Model model) {
        bikeTripService.saveAllTrips()
        return "success"
    }

    @GetMapping("/travellersEachDay")
    def displayBarGraph(Model model) {
        List<String[]> travellersEachDay = bikeTripService.displayTravellersInDays()
        def barGraph = [:]
        travellersEachDay.each {
            barGraph.put(it[1], Double.parseDouble(it[0]).intValue());
        }
        model.addAttribute("barGraph", barGraph)
        return "column"
    }

}