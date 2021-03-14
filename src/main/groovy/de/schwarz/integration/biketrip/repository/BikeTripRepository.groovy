package de.schwarz.integration.biketrip.repository

import de.schwarz.integration.biketrip.entity.BikeTrip
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface BikeTripRepository extends JpaRepository<BikeTrip, Long> {

    @Query(nativeQuery = true, value = """
                        select 
                            travellers as travellers, start_station as startStationName
                        from
                        (
                            select 
                                count(*) travellers
                                 , start_station_name start_station 
                            from 
                                schwarzit.biketrip 
                            group by start_station_name
                        )
    """)
    List<String[]> displayTravellersInDays()
}