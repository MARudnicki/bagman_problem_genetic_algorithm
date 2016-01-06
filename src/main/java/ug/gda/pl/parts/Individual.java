package ug.gda.pl.parts;

import java.util.List;

/**
 * Created by Maciek on 02.01.2016.
 */
public class Individual {

    public Individual(List<City> cityList) {
        this.cityList = cityList;
    }

    double fitnesResult;
    List<City> cityList;

    public double getFitnesResult() {
        return fitnesResult;
    }

    public void setFitnesResult(double fitnesResult) {
        this.fitnesResult = fitnesResult;
    }

    public List<City> getCityList() {
        return cityList;
    }

    public void setCityList(List<City> cityList) {
        this.cityList = cityList;
    }
}
