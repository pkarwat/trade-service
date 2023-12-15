package com.example.tradeservice.product;

import com.example.tradeservice.product.api.MatchTradesCommand;
import com.example.tradeservice.product.api.ProductApi;
import com.example.tradeservice.product.api.MatchedTradeDto;
import com.example.tradeservice.product.infrastructure.api.ProductDao;
import com.example.tradeservice.product.infrastructure.api.ProductRepositoryApi;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
class ProductService implements ProductApi {

    private final ProductRepositoryApi repository;

    @Override
    public List<MatchedTradeDto> match(MatchTradesCommand command) {
        List<ProductDao> productDaoList = repository.getProducts();

        List<Product> productDomainList = ProductConverter.convert(productDaoList);

        return MatchedTradeConverter.convert(productDomainList);
    }
}
