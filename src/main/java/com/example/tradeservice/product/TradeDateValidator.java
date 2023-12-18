package com.example.tradeservice.product;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.text.ParseException;
import java.text.SimpleDateFormat;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TradeDateValidator {

    private static final String TRADE_DATE_FORMAT = "yyyyMMdd";

    static boolean isValid(String tradeDate) {
        try {
            SimpleDateFormat df = new SimpleDateFormat(TRADE_DATE_FORMAT);
            df.setLenient(false);
            df.parse(tradeDate);
            return true;
        } catch (ParseException e) {
            log.error("Trade date <{}> not in valid format: <yyyyMMdd>", tradeDate);
            return false;
        }
    }
}
