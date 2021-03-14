package de.schwarz.integration.biketrip.service

import de.schwarz.integration.biketrip.entity.BikeTrip
import de.schwarz.integration.biketrip.entity.Gender
import de.schwarz.integration.biketrip.repository.BikeTripRepository
import de.schwarz.integration.util.DateUtil
import de.schwarz.integration.util.FlatFileProcessor
import org.apache.commons.csv.CSVFormat
import org.apache.commons.csv.CSVRecord
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class BikeTripServiceImpl implements BikeTripService {

    BikeTripRepository bikeTripRepository;

    @Autowired
    BikeTripServiceImpl(BikeTripRepository bikeTripRepository) {
        this.bikeTripRepository = bikeTripRepository
    }

    static final String PATH = "./src/main/resources/static/biketrip/"

    @Override
    Page<BikeTrip> findAll() {
        bikeTripRepository.findAll(Pageable.ofSize(100))
    }

    @Override
    List<BikeTrip> findAllTrips() {
        new FlatFileProcessor(directory: PATH).process { Reader reader ->
            Iterable<CSVRecord> records = CSVFormat
                    .DEFAULT
                    .withDelimiter(',' as char)
                    .withFirstRecordAsHeader()
                    .parse(reader)
                    .collect { CSVRecord record ->
                        new BikeTrip(startStationId: record.get("start station id").toLong()
                                , endStationId: record.get("end station id").toLong()
                                , startStationName: record.get("start station name")
                                , endStationName: record.get("end station name")
                                , startTime: DateUtil.formatDate(record.get("starttime"))
                                , stopTime: DateUtil.formatDate(record.get("stoptime"))
                                , tripDuration: record.get("tripduration").toLong()
                                , bikeId: record.get("bikeid").toInteger()
                                , gender: Gender.from(Integer.parseInt(record.get("gender"))).name())
                    }
        }
    }

    @Override
    List<String[]> displayTravellersInDays() {
        bikeTripRepository.displayTravellersInDays()
    }

    @Override
    void saveAllTrips() {
        bikeTripRepository.saveAll(findAllTrips())
    }
}
