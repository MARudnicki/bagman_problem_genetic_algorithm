package ug.gda.pl.engine;

import ug.gda.pl.parts.City;
import ug.gda.pl.parts.GeneticAlgorithmWrapper;
import ug.gda.pl.parts.Individual;

import java.util.List;

/**
 * Created by Maciek on 02.01.2016.
 */
public interface GeneticAlgorithm {

    List<Individual>  generateRandomPopulation( GeneticAlgorithmWrapper wrapper, List<City>cityList);

    GenericAlgorithmResults generateResults (GeneticAlgorithmWrapper wrapper);
}
