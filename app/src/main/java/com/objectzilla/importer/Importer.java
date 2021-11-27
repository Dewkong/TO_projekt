package com.objectzilla.importer;

import com.objectzilla.model.Transaction;
import io.reactivex.rxjava3.core.Observable;

import java.io.File;
import java.util.List;

public interface Importer {
    Observable<Transaction> importLine(List<String> line);
}
