package de.schwarz.integration.biketrip.repository

import de.schwarz.integration.biketrip.entity.Weather
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface WeatherRepository extends JpaRepository<Weather, Long> {

    @Query(nativeQuery = true, value = """
                    select
                        temperature, travellers, dayTime
                    from
                    (
                        select 
                            avg(temperature) as temperature, avg(travellers) as travellers, day as dayTime
                        from
                        (
                            select 0 temperature, count(*) travellers, trunc(start_time) day from schwarzit.biketrip group by  trunc(start_time)
                            union all
                            select round(avg(temperature), 2) temperature, 0 travellers, trunc(date_time) day from schwarzit.weather  group by  trunc(date_time)
                        )
                        group by day
                    )
    """)
    List<String[]> displayTravellersVsTemperature()
}