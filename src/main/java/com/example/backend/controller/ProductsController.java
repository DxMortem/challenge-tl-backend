package com.example.backend.controller;

import com.example.backend.model.Product;
import com.example.backend.repository.ProductRepository;
import jakarta.persistence.criteria.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.web.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping(value = "/products")
public class ProductsController {

    private final ProductRepository productRepository;

    @PostMapping
    ResponseEntity<UUID> createProduct(@RequestBody Product request) {

        if (request != null) {
            var id  = UUID.randomUUID();
            request.setId(id);
            productRepository.save(request);
            return new ResponseEntity<>(id, HttpStatus.CREATED);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping
    ResponseEntity<PagedModel<Product>> getAllProducts(Pageable pageable, @RequestParam(required = false) String productName)
            throws InterruptedException {

        Specification<Product> specification = (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (productName != null) {
                predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), "%"+productName.toLowerCase()+"%"));
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
        Thread.sleep(2000);
        return ResponseEntity.of(Optional.of(new PagedModel<>(productRepository.findAll(specification, pageable))));
    }

    @PutMapping("/{id}")
    ResponseEntity<Product> modifyProduct(@PathVariable UUID id, @RequestBody Product request) {

        if (request != null && id != null && productRepository.existsById(id)) {
            request.setId(id);
            productRepository.save(request);
        } else {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(request);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Product> deleteProduct(@PathVariable UUID id) {

        productRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    ResponseEntity<Optional<Product>> getProductById(@PathVariable UUID id) {

        return ResponseEntity.of(Optional.of(productRepository.findById(id)));
    }

    @GetMapping("/category/{searchCategory}")
    ResponseEntity<PagedModel<Product>> getProductsByCategory(@PathVariable String searchCategory,
                                                                  @RequestParam(required = false, defaultValue = "0") Integer page,
                                                                  @RequestParam(required = false, defaultValue = "2") Integer pageSize) {

        Pageable paging = PageRequest.of(page, pageSize, Sort.by("category").ascending());

        return ResponseEntity.of(Optional.of(new PagedModel<> (productRepository.findAllByCategory(searchCategory, paging))));
    }


}
