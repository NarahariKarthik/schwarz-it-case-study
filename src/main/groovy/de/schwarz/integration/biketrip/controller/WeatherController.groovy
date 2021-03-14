package de.schwarz.integration.biketrip.controller

import de.schwarz.integration.biketrip.entity.Weather
import de.schwarz.integration.biketrip.service.WeatherService
import de.schwarz.integration.util.DateUtil
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/weather-report")
class WeatherController {

    WeatherService weatherService;

    WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping("/findAllDetails")
    ResponseEntity<List<Weather>> findAllDetails() {
        return new ResponseEntity<>(weatherService.findAllDetails(), HttpStatus.OK);
    }

    @GetMapping("/saveAllDetails")
    def saveAllDetails() {
        weatherService.saveAllData()
        return "success"
    }

    @GetMapping("/travellersVsTemperature")
    def displayBarGraph(Model model) {
        List<String[]> tempVsRiders = weatherService.displayTravellersVsTemperature()
        def lineGraph = [:]
        def barGraph = [:]
        tempVsRiders.each {
            def day = DateUtil.formatDate(it[2]).toLocalDate()
            barGraph.put(day, Double.parseDouble(it[1]).intValue())
            lineGraph.put(day, Double.parseDouble(it[0]).intValue())
        }
        model.addAttribute("lineGraph", lineGraph)
        model.addAttribute("barGraph", barGraph)
        return "line-and-column"
    }
}
