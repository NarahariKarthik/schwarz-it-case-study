package de.schwarz.integration.biketrip.entity


import groovy.transform.ToString
import groovy.transform.TupleConstructor

import javax.persistence.*
import java.time.LocalDateTime

@ToString
@TupleConstructor
@Entity
@Table(schema = 'schwarzit', name = 'biketrip')
class BikeTrip {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id
    Long startStationId
    Long endStationId
    String startStationName
    String endStationName
    LocalDateTime startTime
    LocalDateTime stopTime
    Long tripDuration
    Integer bikeId
    @Enumerated(EnumType.STRING)
    Gender gender
}