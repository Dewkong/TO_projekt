package com.objectzilla.importer;

import com.opencsv.CSVReader;
import io.reactivex.rxjava3.core.Observable;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;

public class CsvParser implements Parser{

    @Override
    public Observable<List<String>> parse(File file) {

        return Observable.create(observer -> {
            CSVReader csvReader = new CSVReader(new FileReader(file));

            String[] values;
            while ((values = csvReader.readNext()) != null) {
                observer.onNext(Arrays.asList(values));
            }
            observer.onComplete();
        });

    }
}
