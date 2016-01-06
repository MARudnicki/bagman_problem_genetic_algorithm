package ug.gda.pl.parts;

import ug.gda.pl.fitness.FitnessFunction;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by Maciek on 31.12.2015.
 */

/*
•	wielkość populacji
•	metoda selekcji (rankingowa, ruletka, turniejowa)
•	sposób krzyżowania (jednopunktowy, dwupunktowy)
•	mutacja (%)
•	wielkość elity
•	heurystyczna funkcja oceny - fitness

 */
public class GeneticAlgorithmWrapper {

    private int populationSize;
    private int mutationPersent;
    private int elliteSize;
    private FitnessFunction fitness;
    private int iterationNumber;
    private List<Individual> population=new LinkedList<Individual>();
    private Map<Integer,Double> generationResults=new LinkedHashMap<Integer, Double>();

    public Map<Integer, Double> getGenerationResults() {
        return generationResults;
    }

    public void setGenerationResults(Map<Integer, Double> generationResults) {
        this.generationResults = generationResults;
    }

    public List<Individual> getPopulation() {
        return population;
    }

    public void setPopulation(List<Individual> population) {
        this.population = population;
    }

    public int getIterationNumber() {
        return iterationNumber;
    }

    public void setIterationNumber(int iterationNumber) {
        this.iterationNumber = iterationNumber;
    }

    public int getPopulationSize() {
        return populationSize;
    }

    public void setPopulationSize(int populationSize) {
        this.populationSize = populationSize;

    }

    public int getMutationPersent() {
        return mutationPersent;
    }

    public void setMutationPersent(int mutationPersent) {
        this.mutationPersent = mutationPersent;
    }

    public int getElliteSize() {
        return elliteSize;
    }

    public void setElliteSize(int elliteSize) {
        this.elliteSize = elliteSize;
    }

    public FitnessFunction getFitness() {
        return fitness;
    }

    public void setFitness(FitnessFunction fitness) {
        this.fitness = fitness;
    }
}
