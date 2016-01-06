package ug.gda.pl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ug.gda.pl.engine.GenericAlgorithmResults;
import ug.gda.pl.engine.GeneticAlgorithm;
import ug.gda.pl.engine.GeneticAlgorithmImpl;
import ug.gda.pl.fitness.FitnessFunctionImpl;
import ug.gda.pl.parts.City;
import ug.gda.pl.parts.GeneticAlgorithmWrapper;
import ug.gda.pl.parts.Individual;

import java.util.List;

public class Main {
    static Logger log = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        log.info("getting started !");

        CsvReader csvReader = new CsvReader();
        List<City> cityList = csvReader.readCsv();
        GeneticAlgorithm geneticAlgorithm = new GeneticAlgorithmImpl();

        GeneticAlgorithmWrapper geneticAlgorithmWrapper = new GeneticAlgorithmWrapper();
            geneticAlgorithmWrapper.setElliteSize(50);
            geneticAlgorithmWrapper.setFitness(new FitnessFunctionImpl());
            geneticAlgorithmWrapper.setIterationNumber(5);
            geneticAlgorithmWrapper.setMutationPersent(0);
            geneticAlgorithmWrapper.setPopulationSize(10);
            geneticAlgorithmWrapper.setPopulation(geneticAlgorithm.generateRandomPopulation(geneticAlgorithmWrapper,cityList));

        GenericAlgorithmResults results = geneticAlgorithm.generateResults(geneticAlgorithmWrapper);

        log.info("Najmniejsza odleglosc wynosi {} "+ results.getJournesTakes());
        System.out.print("Najmniejsza odleglosc wynosi {} "+ results.getJournesTakes());
    }
}
