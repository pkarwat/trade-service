package com.example.tradeservice.product.ui.rest;

import com.example.tradeservice.product.api.MatchTradesCommand;
import com.example.tradeservice.product.api.MatchedTradeDto;
import com.example.tradeservice.product.api.MatchingApi;
import com.example.tradeservice.product.api.UnmatchedTradeDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/enrich")
@RequiredArgsConstructor
@Slf4j
class EnrichTradeRestController {

    private final MatchingApi matchingApi;
    private final UnmatchedTradeCsvImporter csvImporter;

    @GetMapping
    ResponseEntity<String> getMatch() {
        return ResponseEntity.status(HttpStatus.OK).body("{\"api\": \"trades matching api\"}");
    }

    @PostMapping(produces = "text/csv")
    ResponseEntity<FileSystemResource> match(@RequestParam final MultipartFile file) {
        log.info("Handling POST trades csv file.");

        try {
            List<UnmatchedTradeDto> csvDtoList = csvImporter.importFromFile(file.getInputStream());
            log.debug("CSV input data: {}", csvDtoList);

            List<MatchedTradeDto> matchedTrades = matchingApi.match(new MatchTradesCommand(csvDtoList));

            File outputFile = csvImporter.objToCsv(matchedTrades);

            return ResponseEntity
                    .status(HttpStatus.OK)
                    .contentLength(outputFile.length())
                    .contentType(MediaType.parseMediaType("text/csv"))
                    .body(new FileSystemResource(outputFile));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
