package com.product.controller;

import com.product.payload.ProductDto;
import com.product.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/product")
public class ProductController {
    private ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @PostMapping("saveProduct")
    public ResponseEntity<ProductDto> saveProduct(@RequestBody ProductDto dto) {
        ProductDto productDto = service.saveTheProduct(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(productDto);
    }

    @PutMapping("productUpdate/{pid}")
    public ResponseEntity<ProductDto> updateProduct(@PathVariable("pid") long id, @RequestBody ProductDto dto) {
        ProductDto productDto = service.updateTheProduct(dto, id);
        return ResponseEntity.status(HttpStatus.OK).body(productDto);
    }

    @DeleteMapping("deleteProduct")
    public ResponseEntity<String> deleteProduct(@RequestParam("pid") long id) {
        service.deleteTheProduct(id);
        return new ResponseEntity<>("Product Deleted Successfully with ID: " + id, HttpStatus.OK);
    }

    @GetMapping("getAll")
    public ResponseEntity<List<ProductDto>> findAllProduct(
            @RequestParam(name = "pageNo", required = false, defaultValue = "0") int pageNo,
            @RequestParam(required = false, defaultValue = "5") int pageSize,
            @RequestParam(name = "sort", required = false, defaultValue = "pid") String sortBy,
            @RequestParam(name = "order", required = false, defaultValue = "asc") String orderBy

    ) {
        List<ProductDto> allProduct = service.getAllProduct(pageNo, pageSize, sortBy, orderBy);
        return new ResponseEntity<>(allProduct, HttpStatus.OK);
    }

    @GetMapping("findById")
    public ResponseEntity<ProductDto> findProductById(@RequestParam long id) {
        ProductDto productById = service.getProductById(id);
        return new ResponseEntity<>(productById, HttpStatus.OK);
    }

}
