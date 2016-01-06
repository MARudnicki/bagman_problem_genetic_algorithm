package ug.gda.pl.guava;

import com.google.common.base.Function;
import org.apache.commons.lang3.StringUtils;
import ug.gda.pl.parts.City;

/**
 * Created by Maciek on 02.01.2016.
 */
public class Functions {

    public static Function<City, City> addCity (final City cityFromDad){
        return new Function<City, City>() {
            public City apply(City city) {
                return cityFromDad;
            }
        };
    }
}
