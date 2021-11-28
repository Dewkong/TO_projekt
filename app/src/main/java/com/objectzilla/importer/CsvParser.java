package com.objectzilla.importer;

import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import io.reactivex.rxjava3.core.Observable;

import java.io.File;
import java.io.FileReader;
import java.util.Arrays;
import java.util.List;

public class CsvParser implements Parser {

    @Override
    public Observable<List<String>> parse(File file) {

        return Observable.create(observer -> {
            CSVReader csvReader = new CSVReaderBuilder(new FileReader(file))
                    .withCSVParser(new CSVParserBuilder().withSeparator(';').build())
                    .build();

            String[] values;
            while ((values = csvReader.readNext()) != null) {
                observer.onNext(Arrays.asList(values));
            }
            observer.onComplete();
        });

    }
}
