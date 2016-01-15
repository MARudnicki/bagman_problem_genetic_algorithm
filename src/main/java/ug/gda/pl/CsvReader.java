package ug.gda.pl;

import com.google.common.base.Splitter;
import ug.gda.pl.config.AppConfig;
import ug.gda.pl.parts.City;

import java.io.*;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Maciek on 31.12.2015.
 */
public class CsvReader {
    String line = "";
    String splitBy = ";";

    public List<City> readCsv() {

        List cityList = new LinkedList();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(AppConfig.csvFile));

            while ((line = bufferedReader.readLine()) != null) {

                try {
                    List<String> list = Splitter.on(splitBy).splitToList(line);
                    cityList.add(new City(list.get(0), Double.parseDouble(list.get(1)), Double.parseDouble(list.get(2))));
                } catch (NumberFormatException e) {
                    throw new NumberFormatException("Blad danych wejsciowych dla "+line+" Sprawdz sredniki itd. "+e);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if(cityList.size()<2) throw new RuntimeException("Musza byc co najmniej 2 miasta !");

        return cityList;
    }
}
