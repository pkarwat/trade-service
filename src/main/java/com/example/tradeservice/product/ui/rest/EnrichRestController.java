package com.example.tradeservice.product.ui.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1/enrich")
public class EnrichRestController {

    @GetMapping
    ResponseEntity<String> getMatch() {
        System.out.println("get()");
        return ResponseEntity
                .status(HttpStatus.OK)
                .body("GET body foo");
    }

    @PostMapping
//    public ResponseEntity<String> match(@RequestParam("file") final MultipartFile file) {
    public ResponseEntity<String> match(@RequestParam final MultipartFile file) {
        System.out.println(" match()");

        return ResponseEntity
                .status(HttpStatus.OK)
                .body("Body foo.");
    }
}
