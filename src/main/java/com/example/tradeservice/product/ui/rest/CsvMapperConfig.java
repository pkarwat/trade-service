package com.example.tradeservice.product.ui.rest;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.dataformat.csv.CsvGenerator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CsvMapperConfig {

    @Bean
    CsvMapper csvMapper() {
        CsvMapper csvMapper = new CsvMapper();
        csvMapper.configure(JsonGenerator.Feature.IGNORE_UNKNOWN, true);
        csvMapper.configure(CsvGenerator.Feature.STRICT_CHECK_FOR_QUOTING, true);
        csvMapper.setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE);

        return csvMapper;
    }
}
