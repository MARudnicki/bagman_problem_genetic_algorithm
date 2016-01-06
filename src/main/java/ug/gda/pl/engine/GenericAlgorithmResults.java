package ug.gda.pl.engine;

import ug.gda.pl.parts.City;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Maciek on 02.01.2016.
 */
public class GenericAlgorithmResults {

    List<City> resultCityList = new LinkedList<City>();

    double journesTakes;

    public List<City> getResultCityList() {
        return resultCityList;
    }

    public void setResultCityList(List<City> resultCityList) {
        this.resultCityList = resultCityList;
    }

    public double getJournesTakes() {
        return journesTakes;
    }

    public void setJournesTakes(double journesTakes) {
        this.journesTakes = journesTakes;
    }
}
