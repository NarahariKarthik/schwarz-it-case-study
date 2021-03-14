package de.schwarz.integration.biketrip.service

import de.schwarz.integration.biketrip.entity.Weather

interface WeatherService {
    void saveAllData()

    List<Weather> findAllDetails()

    List<String[]> displayTravellersVsTemperature()
}
