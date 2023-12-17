package com.example.tradeservice.product.ui.rest;

import com.example.tradeservice.product.api.MatchedTradeDto;
import com.example.tradeservice.product.api.UnmatchedTradeDto;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvGenerator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Slf4j
public class UnmatchedTradeCsvImporter {

    public static List<UnmatchedTradeDto> importFromFile(InputStream inputStream) {
        try (final MappingIterator<UnmatchedTradeDto> readValues = new CsvMapper()
                .readerFor(UnmatchedTradeDto.class)
                .with(CsvSchema.emptySchema().withHeader())
                .readValues(inputStream)) {
            return new ArrayList<>(readValues.readAll());
        } catch (IOException e) {
            //log.error(Parsing unamtched trades csv failed, e);
            throw new RuntimeException(e);  //TODO delete throwing exception, log.error
        }
    }

    public static File objToCsv(List<MatchedTradeDto> matchedTrades) {
        CsvSchema schema = CsvSchema.builder().setUseHeader(true)
                .addColumn("date")
                .addColumn("productName")
                .addColumn("currency")
                .addColumn("price")
                .build();

        try {
            final File tempFile = File.createTempFile("employee", "csv");
            final FileOutputStream fos = new FileOutputStream(tempFile);
            final CsvMapper csvMapper = new CsvMapper();
            csvMapper.configure(JsonGenerator.Feature.IGNORE_UNKNOWN, true);
            final CsvGenerator csvGenerator = csvMapper.getFactory().createGenerator(fos);  //try with resources
            csvGenerator.setSchema(schema);

            matchedTrades.forEach(dto -> {
                try {
                    log.debug("Writing: {}", dto);
                    csvGenerator.writeObject(dto);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
            return tempFile;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
