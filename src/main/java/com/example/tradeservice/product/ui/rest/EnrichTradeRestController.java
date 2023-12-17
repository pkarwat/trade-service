package com.example.tradeservice.product.ui.rest;

import com.example.tradeservice.product.api.MatchTradesCommand;
import com.example.tradeservice.product.api.MatchedTradeDto;
import com.example.tradeservice.product.api.MatchingApi;
import com.example.tradeservice.product.api.UnmatchedTradeDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/enrich")
@RequiredArgsConstructor
@Slf4j
public class EnrichTradeRestController {

    private final MatchingApi matchingApi;

    @GetMapping
    ResponseEntity<String> getMatch() {
        return ResponseEntity.status(HttpStatus.OK).body("{\"api\": \"trades matching api\"}");
    }

    @PostMapping
//    public ResponseEntity<String> match(@RequestParam("file") final MultipartFile file) {
    public ResponseEntity<String> match(@RequestParam final MultipartFile file) {
        log.info("Handling POST trades csv file.");

        try {
            List<UnmatchedTradeDto> csvDtoList = UnmatchedTradeCsvImporter.importFromFile(file.getInputStream());
            log.debug("CSV input data: {}", csvDtoList);

            List<MatchedTradeDto> matchedTrades = matchingApi.match(new MatchTradesCommand(csvDtoList));
            System.out.println("MOCK : " + matchedTrades + " " + matchedTrades);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return ResponseEntity
                .status(HttpStatus.OK)
                .body("Body foo.");
    }
}
