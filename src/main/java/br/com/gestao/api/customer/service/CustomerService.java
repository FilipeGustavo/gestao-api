package br.com.gestao.api.customer.service;

import br.com.gestao.api.common.exception.BusinessException;
import br.com.gestao.api.common.exception.ResourceNotFoundException;
import br.com.gestao.api.customer.domain.Customer;
import br.com.gestao.api.customer.dto.CustomerRequest;
import br.com.gestao.api.customer.dto.CustomerResponse;
import br.com.gestao.api.customer.mapper.CustomerMapper;
import br.com.gestao.api.customer.repository.CustomerRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    public CustomerService(CustomerRepository customerRepository, CustomerMapper customerMapper) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
    }

    @Transactional(readOnly = true)
    public Page<CustomerResponse> findAll(Pageable pageable) {
        return customerRepository.findAll(pageable).map(customerMapper::toResponse);
    }

    @Transactional(readOnly = true)
    public CustomerResponse findById(UUID id) {
        return customerMapper.toResponse(getCustomer(id));
    }

    @Transactional
    public CustomerResponse create(CustomerRequest request) {
        validateUniqueFields(request.email(), request.document(), null);
        Customer customer = customerMapper.toEntity(request);
        return customerMapper.toResponse(customerRepository.save(customer));
    }

    @Transactional
    public CustomerResponse update(UUID id, CustomerRequest request) {
        Customer customer = getCustomer(id);
        validateUniqueFields(request.email(), request.document(), id);
        customerMapper.updateEntity(customer, request);
        return customerMapper.toResponse(customer);
    }

    @Transactional
    public void delete(UUID id) {
        Customer customer = getCustomer(id);
        customerRepository.delete(customer);
    }

    private Customer getCustomer(UUID id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente", id));
    }

    private void validateUniqueFields(String email, String document, UUID currentId) {
        String normalizedEmail = email.trim().toLowerCase();
        String normalizedDocument = document.replaceAll("\\D", "");

        boolean emailExists = currentId == null
                ? customerRepository.existsByEmailIgnoreCase(normalizedEmail)
                : customerRepository.existsByEmailIgnoreCaseAndIdNot(normalizedEmail, currentId);
        if (emailExists) {
            throw new BusinessException("Ja existe cliente cadastrado com este e-mail");
        }

        boolean documentExists = currentId == null
                ? customerRepository.existsByDocument(normalizedDocument)
                : customerRepository.existsByDocumentAndIdNot(normalizedDocument, currentId);
        if (documentExists) {
            throw new BusinessException("Ja existe cliente cadastrado com este documento");
        }
    }
}
