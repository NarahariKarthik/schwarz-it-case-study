package de.schwarz.integration.biketrip.service

import de.schwarz.integration.biketrip.entity.BikeTrip
import org.springframework.data.domain.Page

interface BikeTripService {
    Page<BikeTrip> findAll()

    void saveAllTrips()

    List<BikeTrip> findAllTrips()

    List<String[]> displayTravellersInDays()
}
