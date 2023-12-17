package com.example.tradeservice.product.ui.rest;

import com.example.tradeservice.product.api.MatchedTradeDto;
import com.example.tradeservice.product.api.UnmatchedTradeDto;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
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
        CsvMapper mapper = new CsvMapper();
        mapper.configure(JsonGenerator.Feature.IGNORE_UNKNOWN, true);

        CsvSchema schema = CsvSchema.builder().setUseHeader(true)
                .addColumn("date")
//                .addColumn("product_name")
//                .addColumn("currency")
//                .addColumn("price")
                .build();

        ObjectWriter writer = mapper.writerFor(MatchedTradeDto.class).with(schema);

        try {
            File tempFile = File.createTempFile("employee", "csv");
            FileOutputStream fos = new FileOutputStream(tempFile);
            writer.writeValues(fos);
//            CsvGenerator todo https://dev.to/ratulsharker/csv-generation-from-large-json-response-in-spring-2bnn
            return tempFile;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
