package br.com.gestao.api.product.mapper;

import br.com.gestao.api.product.domain.Product;
import br.com.gestao.api.product.dto.ProductRequest;
import br.com.gestao.api.product.dto.ProductResponse;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    public Product toEntity(ProductRequest request) {
        return new Product(
                request.name().trim(),
                blankToNull(request.description()),
                normalizeSku(request.sku()),
                request.price(),
                request.stockQuantity()
        );
    }

    public ProductResponse toResponse(Product product) {
        return new ProductResponse(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getSku(),
                product.getPrice(),
                product.getStockQuantity(),
                product.isActive(),
                product.getCreatedAt(),
                product.getUpdatedAt()
        );
    }

    public void updateEntity(Product product, ProductRequest request) {
        product.update(
                request.name().trim(),
                blankToNull(request.description()),
                normalizeSku(request.sku()),
                request.price(),
                request.stockQuantity(),
                request.active() == null || request.active()
        );
    }

    private String normalizeSku(String sku) {
        return sku.trim().toUpperCase();
    }

    private String blankToNull(String value) {
        if (value == null || value.isBlank()) {
            return null;
        }
        return value.trim();
    }
}
