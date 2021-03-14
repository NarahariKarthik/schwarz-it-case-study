package de.schwarz.integration.biketrip.entity

import groovy.transform.ToString
import groovy.transform.TupleConstructor

import javax.persistence.*
import java.time.LocalDateTime

@ToString
@TupleConstructor
@Entity
@Table(schema = 'schwarzit', name = 'weather')
class Weather {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id
    String name
    LocalDateTime dateTime
    Double maximumTemperature
    Double minimumTemperature
    Double temperature
    Double windChill
    Double heatIndex
    Double precipitation
    Double snowDepth
    Double windSpeed
    Double windGust
    Double visibility
    String weatherType
    String conditions
}
