package ug.gda.pl.guava;

import com.google.common.base.Predicate;
import org.apache.commons.lang3.StringUtils;
import ug.gda.pl.parts.City;

/**
 * Created by Maciek on 02.01.2016.
 */
public class Predicates {

    public static Predicate<City> containsCityPredicate(final City cityFromDad){
        return new Predicate<City>() {
            public boolean apply(City city) {
                return StringUtils.equals(city.getName(),cityFromDad.getName());
            }
        };
    }
    public static Predicate<City> blankCityPredicate(){
        return new Predicate<City>() {
            public boolean apply(City city) {
                return StringUtils.isBlank(city.getName());
            }
        };
    }

}
