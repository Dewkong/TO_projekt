package com.objectzilla.importer;

import io.reactivex.rxjava3.core.Observable;

import java.io.File;
import java.util.List;

public interface Parser {
    Observable<List<String>> parse(File file);
}
