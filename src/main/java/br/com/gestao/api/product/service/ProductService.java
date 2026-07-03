package br.com.gestao.api.product.service;

import br.com.gestao.api.common.exception.BusinessException;
import br.com.gestao.api.common.exception.ResourceNotFoundException;
import br.com.gestao.api.product.domain.Product;
import br.com.gestao.api.product.dto.ProductRequest;
import br.com.gestao.api.product.dto.ProductResponse;
import br.com.gestao.api.product.mapper.ProductMapper;
import br.com.gestao.api.product.repository.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public ProductService(ProductRepository productRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    @Transactional(readOnly = true)
    public Page<ProductResponse> findAll(Pageable pageable) {
        return productRepository.findAll(pageable).map(productMapper::toResponse);
    }

    @Transactional(readOnly = true)
    public ProductResponse findById(UUID id) {
        return productMapper.toResponse(getProduct(id));
    }

    @Transactional
    public ProductResponse create(ProductRequest request) {
        validateUniqueSku(request.sku(), null);
        Product product = productMapper.toEntity(request);
        return productMapper.toResponse(productRepository.save(product));
    }

    @Transactional
    public ProductResponse update(UUID id, ProductRequest request) {
        Product product = getProduct(id);
        validateUniqueSku(request.sku(), id);
        productMapper.updateEntity(product, request);
        return productMapper.toResponse(product);
    }

    @Transactional
    public void delete(UUID id) {
        Product product = getProduct(id);
        productRepository.delete(product);
    }

    private Product getProduct(UUID id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Produto", id));
    }

    private void validateUniqueSku(String sku, UUID currentId) {
        String normalizedSku = sku.trim().toUpperCase();
        boolean skuExists = currentId == null
                ? productRepository.existsBySkuIgnoreCase(normalizedSku)
                : productRepository.existsBySkuIgnoreCaseAndIdNot(normalizedSku, currentId);
        if (skuExists) {
            throw new BusinessException("Ja existe produto cadastrado com este SKU");
        }
    }
}
