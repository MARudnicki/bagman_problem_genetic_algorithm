package ug.gda.pl.fitness;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ug.gda.pl.parts.City;
import ug.gda.pl.config.AppConfig;

import java.util.Iterator;
import java.util.List;

/**
 * Created by Maciek on 31.12.2015.
 */
public class FitnessFunctionImpl implements FitnessFunction{
    Logger log = LoggerFactory.getLogger(FitnessFunctionImpl.class);


    public double generateResult(List<City> cityList) {

        double journey=0;

        City startCity = cityList.get(0);

        String name = startCity.getName();
        double longtitude = startCity.getLongitude();
        double latitude = startCity.getLatitude();

        for(int index=1 ; index<cityList.size() ; index++){
            City city = cityList.get(index);

            double journeyBeetwenCities = Math.round(Math.sqrt(
                    Math.pow((longtitude-city.getLongitude()),2) +
                            Math.pow((latitude-city.getLatitude()),2))*10000.0)/10000.0;

            log.info(" Journey from "+name+" to "+city.getName()+" takes "+journeyBeetwenCities);
            journey+=journeyBeetwenCities;

            longtitude = city.getLongitude();
            latitude = city.getLatitude();
            name = city.getName();
        }

        log.info("All journey takes "+journey);
        return -journey;
    }
}
