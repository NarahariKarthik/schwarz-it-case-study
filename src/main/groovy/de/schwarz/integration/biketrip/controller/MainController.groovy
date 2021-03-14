package de.schwarz.integration.biketrip.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping

@Controller
class MainController {

    @RequestMapping("/")
    def viewHomePage() {
        return "index"
    }
}
