package ug.gda.pl.engine;

import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.collect.Collections2;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.Iterators;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ug.gda.pl.fitness.FitnessFunction;
import ug.gda.pl.guava.Functions;
import ug.gda.pl.guava.Predicates;
import ug.gda.pl.parts.City;
import ug.gda.pl.parts.GeneticAlgorithmWrapper;
import ug.gda.pl.parts.Individual;

import java.util.*;


/**
 * Created by Maciek on 02.01.2016.
 */
public class GeneticAlgorithmImpl implements GeneticAlgorithm {
    Logger log = LoggerFactory.getLogger(GeneticAlgorithmImpl.class);
    // metoda Order crossover (OX, Davis, 1985)


    public List<Individual> generateRandomPopulation(GeneticAlgorithmWrapper wrapper, List<City>cityList){
        log.info("generowanie losowej startowej populacji {} osobnikow",wrapper.getPopulationSize());
        List<Individual>randomPopulation = new ArrayList<Individual>(wrapper.getPopulationSize());

        for(int index=0 ;index<wrapper.getPopulationSize() ; index++){
            List<City> newCityList = new ArrayList<City>(cityList);
            Collections.shuffle(newCityList);

            randomPopulation.add(new Individual(newCityList));

        }
        return randomPopulation;
    }

    public GenericAlgorithmResults generateResults(GeneticAlgorithmWrapper wrapper) {
        log.info("generowanie wynikow ");

        for (int generation = 0; generation < wrapper.getIterationNumber(); generation++) {
            log.info("Zaczynam generacje {}" + generation);

            generateFitnesResults(wrapper.getFitness(), wrapper.getPopulation());
            elliteFilter(wrapper);

            getBestSollution(wrapper, generation);
            if(generation < wrapper.getIterationNumber()-1) buildNextGeneration(wrapper);

            log.info("Generacja nr {} gotowa " + generation);
        }

        List<Individual> individualsToSort = new ArrayList<Individual>(wrapper.getPopulation());
        Collections.sort(individualsToSort, fitnesComparator());
        wrapper.setPopulation(individualsToSort);

        GenericAlgorithmResults genericAlgorithmResults = new GenericAlgorithmResults();
        genericAlgorithmResults.setResultCityList(wrapper.getPopulation().get(0).getCityList());
        genericAlgorithmResults.setJournesTakes(wrapper.getPopulation().get(0).getFitnesResult());
        return genericAlgorithmResults;

    }

    private Comparator<Individual> fitnesComparator(){
        return new Comparator<Individual>() {
            public int compare(Individual o1, Individual o2) {
                return String.valueOf(o1.getFitnesResult()).compareTo(String.valueOf(o2.getFitnesResult()));
            }
        };
    }

    public void buildNextGeneration(GeneticAlgorithmWrapper wrapper) {
        List<Pair<Individual, Individual>> parentPairs = generateParentPairs(wrapper);
        int numberOfChildToProduce = wrapper.getPopulationSize();
        int childPreduced = 0;

        Iterator<Pair<Individual, Individual>> parentPairIterator = parentPairs.iterator();
        List<Individual> newGeneration = new LinkedList<Individual>();

        while (childPreduced < numberOfChildToProduce) {
            if (parentPairIterator.hasNext()) {
                newGeneration.add(generateChild(parentPairIterator.next()));
                childPreduced++;
            }
            else parentPairIterator = parentPairs.iterator();
        }
        if (newGeneration.size() != wrapper.getPopulationSize())
            throw new RuntimeException("Nowa populacja ma niewlasciwa liczbe");
        wrapper.setPopulation(newGeneration);
    }

    public Individual generateChild(Pair<Individual, Individual> parentsPair) {
        int numberOfCities = parentsPair.getLeft().getCityList().size();
        Individual mom = parentsPair.getLeft();
        Individual dad = parentsPair.getRight();
        int cityFromMom = (int) Math.round(numberOfCities / 2);
        int cityFromDad = numberOfCities - cityFromMom;

        //add mam list
        Random random = new Random();
        int startMomCityIndex = random.nextInt(numberOfCities-1);
        int startDadCityIndex = random.nextInt(numberOfCities-1);
        int startChildIndex = random.nextInt(numberOfCities-1);

        List<City> newCityList = new ArrayList<City>();
        for(int i = 0 ; i < numberOfCities ; i++){
            newCityList.add(new City());
        }

        //from mom
        for (int i = 0; i < cityFromMom; i++) {
            newCityList.set(startChildIndex, mom.getCityList().get(startMomCityIndex));
            startChildIndex++;
            startMomCityIndex++;
            if (startChildIndex >= numberOfCities) startChildIndex = 0;
            if (startMomCityIndex >= numberOfCities) startMomCityIndex = 0;
        }

        //from dad
        int cityFromDadToAdd = cityFromDad;
        while (cityFromDadToAdd > 0) {
            City nextCityFromDad = dad.getCityList().get(startDadCityIndex);
            if (FluentIterable.from(newCityList).filter(Predicates.containsCityPredicate(nextCityFromDad)).isEmpty()) {
                for(int index = 0 ; index < numberOfCities ; index ++){
                    if(StringUtils.isBlank(newCityList.get(index).getName())){
                        newCityList.set(index , nextCityFromDad);
                        cityFromDadToAdd--;
                        break;
                    }
                }
            }
            startDadCityIndex++;
            if (startDadCityIndex >= numberOfCities) startDadCityIndex = 0;
        }

        return new Individual(newCityList);
    }


    public List<Pair<Individual, Individual>> generateParentPairs(GeneticAlgorithmWrapper wrapper) {
        List<Pair<Individual, Individual>> parentsList = new ArrayList<Pair<Individual, Individual>>();
        List<Individual> population = new ArrayList<Individual>(wrapper.getPopulation());
        Collections.shuffle(population);

        int howManyPairs = wrapper.getPopulation().size()/2;
        for (int index = 0; index < howManyPairs; ++index) {
            int mom = index;
            int dad = index+1;
            log.info("Dobieram w pare osobnika {} i {}", mom, dad);
            parentsList.add(new ImmutablePair<Individual, Individual>(population.get(mom), population.get(dad)));
        }
        return parentsList;
    }

    //elliteSize 1-100
    public void elliteFilter(GeneticAlgorithmWrapper wrapper) {
        Preconditions.checkArgument(wrapper.getElliteSize()>0&&wrapper.getElliteSize()<100,"Ellite size musi byc pomiedzy 0 a 100");

        List<Individual> population = wrapper.getPopulation();
        int ellitePersentSize = wrapper.getElliteSize();

        Collections.sort(population, fitnesComparator());

        double elliteRealSize = Math.round(population.size()*ellitePersentSize/100);
        List<Individual> elliteList = new LinkedList<Individual>();
        for (int index = 0; index < elliteRealSize; index++) {
            elliteList.add(population.get(index));
        }
        wrapper.setPopulation(elliteList);

    }

    public void generateFitnesResults(FitnessFunction fitness, List<Individual> population) {

        for (Individual individual : population) {
            individual.setFitnesResult(fitness.generateResult(individual.getCityList()));
        }
    }

    public void getBestSollution(GeneticAlgorithmWrapper wrapper, int generation) {
        wrapper.getGenerationResults().put(generation, wrapper.getPopulation().get(0).getFitnesResult());
        System.out.println("Najlepsze rozwiazanie dla generacji +" +generation +" wynosi "+(-wrapper.getPopulation().get(0).getFitnesResult())+" m");

        for(City city : wrapper.getPopulation().get(0).getCityList()){
            System.out.print(" "+ city.getName()+" ->");
        }
    }
}
