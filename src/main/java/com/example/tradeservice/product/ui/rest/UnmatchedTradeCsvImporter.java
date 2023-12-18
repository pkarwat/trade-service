package com.example.tradeservice.product.ui.rest;

import com.example.tradeservice.product.api.MatchedTradeDto;
import com.example.tradeservice.product.api.UnmatchedTradeDto;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvGenerator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Slf4j
@Service
public class UnmatchedTradeCsvImporter {

    private final CsvMapper csvMapper;
    private final CsvSchema schema = CsvSchema.builder().setUseHeader(true)
            .addColumn("date")
            .addColumn("product_name")
            .addColumn("currency")
            .addColumn("price")
            .build();

    public List<UnmatchedTradeDto> importFromFile(InputStream inputStream) {
        try (final MappingIterator<UnmatchedTradeDto> readValues = csvMapper
                .readerFor(UnmatchedTradeDto.class)
                .with(CsvSchema.emptySchema().withHeader())
                .readValues(inputStream)) {
            return new ArrayList<>(readValues.readAll());
        } catch (IOException e) {
            //log.error(Parsing unamtched trades csv failed, e);
            throw new RuntimeException(e);
        }
    }

    public File objToCsv(List<MatchedTradeDto> matchedTrades) {
        try {
            File tempFile = File.createTempFile("trade", "csv");
            FileOutputStream fos = new FileOutputStream(tempFile);
            try (CsvGenerator csvGenerator = csvMapper.getFactory().createGenerator(fos)) {
                csvGenerator.setSchema(schema);
                matchedTrades.forEach(dto -> {
                    try {
                        log.debug("Writing: {}", dto);

                        csvGenerator.writeObject(
                                new MatchedTradeCsvModel(
                                        dto.getDate(),
                                        dto.getProductName(),
                                        dto.getCurrency(),
                                        dto.getPrice().stripTrailingZeros().toPlainString())
                        );
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });
                return tempFile;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
