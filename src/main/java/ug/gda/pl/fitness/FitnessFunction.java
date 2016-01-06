package ug.gda.pl.fitness;

import ug.gda.pl.parts.City;

import java.util.List;

/**
 * Created by Maciek on 31.12.2015.
 */
public interface FitnessFunction {

    double generateResult(List<City> cityList);

}
