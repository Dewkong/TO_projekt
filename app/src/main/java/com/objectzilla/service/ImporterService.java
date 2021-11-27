package com.objectzilla.service;

import com.objectzilla.importer.CsvParser;
import com.objectzilla.importer.Importer;
import com.objectzilla.importer.MbankImporter;
import com.objectzilla.importer.SantanderImporter;
import com.objectzilla.model.Bank;
import com.objectzilla.model.Transaction;
import io.reactivex.rxjava3.core.Observable;

import java.io.File;
import java.util.HashMap;

public class ImporterService {
    private final HashMap<Bank, Importer> importers;

    public ImporterService() {
        this.importers = new HashMap<>();
        this.importers.put(Bank.MBANK, new MbankImporter());
        this.importers.put(Bank.SANTANDER, new SantanderImporter());
    }

    public Observable<Transaction> importFromCsv(Bank bank, File file){
        return new CsvParser().parse(file)
                .skip(importers.get(bank).getHeaderLength())
                .map(line -> importers.get(bank).importLine(line));
    }
}
