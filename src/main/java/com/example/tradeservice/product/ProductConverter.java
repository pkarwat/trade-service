package com.example.tradeservice.product;

import com.example.tradeservice.product.infrastructure.api.ProductDao;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
class ProductConverter {

    public static List<Product> convert(List<ProductDao> productDaoList) {
        return productDaoList.stream()
                .map(ProductConverter::convertItem)
                .toList();
    }

    private static Product convertItem(ProductDao productDao) {
        return new Product(
                productDao.getId(),
                productDao.getName()
        );
    }
}
