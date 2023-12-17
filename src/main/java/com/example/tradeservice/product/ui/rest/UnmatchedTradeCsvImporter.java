package com.example.tradeservice.product.ui.rest;

import com.example.tradeservice.product.api.UnmatchedTradeDto;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UnmatchedTradeCsvImporter {
    public static List<UnmatchedTradeDto> importFromFile(InputStream inputStream) {
        try (final MappingIterator<UnmatchedTradeDto> readValues = new CsvMapper()
                .readerFor(UnmatchedTradeDto.class)
                .with(CsvSchema.emptySchema().withHeader())
                .readValues(inputStream)) {
            ArrayList<UnmatchedTradeDto> result = new ArrayList<>(readValues.readAll());
            System.out.println("SIZEE: " + result);
            return result;
        } catch (IOException e) {
            //log.error(Parsing unamtched trades csv failed, e);
            throw new RuntimeException(e);  //TODO delete throwing exception, log.error
        }
    }
}
