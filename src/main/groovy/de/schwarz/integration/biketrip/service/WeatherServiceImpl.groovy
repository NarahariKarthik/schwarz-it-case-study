package de.schwarz.integration.biketrip.service

import de.schwarz.integration.biketrip.entity.Weather
import de.schwarz.integration.biketrip.repository.WeatherRepository
import de.schwarz.integration.util.DateUtil
import de.schwarz.integration.util.FlatFileProcessor
import org.apache.commons.csv.CSVFormat
import org.apache.commons.csv.CSVRecord
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class WeatherServiceImpl implements WeatherService {

    @Autowired
    WeatherRepository weatherRepository;

    WeatherServiceImpl(WeatherRepository weatherRepository) {
        this.weatherRepository = weatherRepository
    }
    static final String PATH = "./src/main/resources/static/weather-report"

    @Override
    void saveAllData() {
        weatherRepository.saveAll(findAllDetails())
    }

    def numToDouble(String number) {
        if (number && number.trim().length() > 0) {
            number.toDouble()
        }
    }

    @Override
    List<Weather> findAllDetails() {
        new FlatFileProcessor(directory: PATH).process {
            Reader reader ->
                Iterable<CSVRecord> records = CSVFormat
                        .DEFAULT
                        .withDelimiter(',' as char)
                        .withFirstRecordAsHeader()
                        .parse(reader)
                        .collect { CSVRecord record ->
                            new Weather(
                                    name: record.get("Name")
                                    , dateTime: DateUtil.formatDate(record.get("Date time"))
                                    , maximumTemperature: numToDouble(record.get("Maximum Temperature"))
                                    , minimumTemperature: numToDouble(record.get("Minimum Temperature"))
                                    , temperature: numToDouble(record.get("Minimum Temperature"))
                                    , windChill: numToDouble(record.get("Wind Chill"))
                                    , heatIndex: numToDouble(record.get("Heat Index"))
                                    , precipitation: numToDouble(record.get("Precipitation"))
                                    , snowDepth: numToDouble(record.get("Snow Depth"))
                                    , windSpeed: numToDouble(record.get("Wind Speed"))
                                    , windGust: numToDouble(record.get("Wind Gust"))
                                    , visibility: numToDouble(record.get("Visibility"))
                                    , weatherType: record.get("Weather Type")
                                    , conditions: record.get("Conditions"))
                        }
        }
    }

    @Override
    List<String[]> displayTravellersVsTemperature() {
        return weatherRepository.displayTravellersVsTemperature()
    }
}
