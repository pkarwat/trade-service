package com.example.tradeservice.product;

import com.example.tradeservice.product.api.MatchTradesCommand;
import com.example.tradeservice.product.api.MatchingApi;
import com.example.tradeservice.product.api.MatchedTradeDto;
import com.example.tradeservice.product.infrastructure.api.ProductDao;
import com.example.tradeservice.product.infrastructure.api.ProductRepositoryApi;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
class MatchingTradesService implements MatchingApi {

    private final ProductRepositoryApi repository;
    private final MatchingTradesDomainService matchingTradesDomainService;

    @Override
    public List<MatchedTradeDto> match(MatchTradesCommand command) {
        //TODO validation
        log.info("Processing <MatchTradesCommand> to match  {} trades with products.", command.getTrades().size());
        List<ProductDao> productDaoList = repository.getProducts();

        // In case of business logic, we would use domain layer`s objects here
        //List<Product> productDomainList = ProductConverter.convert(productDaoList);
        return matchingTradesDomainService.match(command.getTrades(), productDaoList);
    }
}
