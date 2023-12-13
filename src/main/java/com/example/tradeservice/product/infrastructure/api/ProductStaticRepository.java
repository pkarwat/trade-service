package com.example.tradeservice.product.infrastructure.api;

import java.util.List;

public class ProductStaticRepository implements ProductRepositoryApi {

    @Override
    public List<ProductDao> getProducts() {
        return List.of(
                new ProductDao(1, "Treasury Bills Domestic"),
                new ProductDao(2, "Corporate Bonds Domestic"),
                new ProductDao(3, "REPO Domestic"),
                new ProductDao(4, "Interest rate swaps International"),
                new ProductDao(5, "OTC Index Option"),
                new ProductDao(6, "Currency Options"),
                new ProductDao(7, "Reverse Repos International"),
                new ProductDao(8, "REPO International"),
                new ProductDao(9, "766A_CORP BD"),
                new ProductDao(10, "766B_CORP BD")
        );
    }
}
