package com.example.tradeservice.product.ui.rest;

import com.example.tradeservice.product.api.MatchTradesCommand;
import com.example.tradeservice.product.api.MatchedTradeDto;
import com.example.tradeservice.product.api.MatchingApi;
import com.example.tradeservice.product.api.UnmatchedTradeDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/enrich")
@RequiredArgsConstructor
public class EnrichTradeRestController {

    private final MatchingApi matchingApi;

    @GetMapping
    ResponseEntity<String> getMatch() {
        System.out.println("get()");
        return ResponseEntity.status(HttpStatus.OK).body("{\"name\": \"hello\"}");
    }

    @PostMapping
//    public ResponseEntity<String> match(@RequestParam("file") final MultipartFile file) {
    public ResponseEntity<String> match(@RequestParam final MultipartFile file) {
        System.out.println(" match()");

        try {
            List<UnmatchedTradeDto> unmatchedDtoTrades = UnmatchedTradeCsvImporter.importFromFile(file.getInputStream());
            List<MatchedTradeDto> matchedTrades = matchingApi.match(new MatchTradesCommand(unmatchedDtoTrades));
            // TODO response as csv
            System.out.println("MATCHEDDDD: " + matchedTrades + " " + matchedTrades);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return ResponseEntity
                .status(HttpStatus.OK)
                .body("Body foo.");
    }
}
