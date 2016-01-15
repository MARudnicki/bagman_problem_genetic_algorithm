package ug.gda.pl;

import ug.gda.pl.engine.GenericAlgorithmResults;
import ug.gda.pl.engine.GeneticAlgorithm;
import ug.gda.pl.engine.GeneticAlgorithmImpl;
import ug.gda.pl.fitness.FitnessFunctionImpl;
import ug.gda.pl.parts.City;
import ug.gda.pl.parts.GeneticAlgorithmWrapper;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        CsvReader csvReader = new CsvReader();
        List<City> cityList = csvReader.readCsv();
        GeneticAlgorithm geneticAlgorithm = new GeneticAlgorithmImpl();

        GeneticAlgorithmWrapper geneticAlgorithmWrapper = new GeneticAlgorithmWrapper();
            geneticAlgorithmWrapper.setElliteSize(20);
            geneticAlgorithmWrapper.setFitness(new FitnessFunctionImpl());
            geneticAlgorithmWrapper.setIterationNumber(5);
            geneticAlgorithmWrapper.setPopulationSize(20);
            geneticAlgorithmWrapper.setPopulation(geneticAlgorithm.generateRandomPopulation(geneticAlgorithmWrapper,cityList));

        GenericAlgorithmResults results = geneticAlgorithm.generateResults(geneticAlgorithmWrapper);
        printResults(results);
    }

    private static void printResults(GenericAlgorithmResults results){
        System.out.println("Najmniejsza odleglosc wynosi km "+ -Math.round(results.getJournesTakes()/1000));

        System.out.println("Lista kolejno odwiedzonych miast dla najkrotszej podrozy : ");
        for(City city : results.getResultCityList()){
            System.out.print(" "+ city.getName()+" ->");
        }
    }
}
