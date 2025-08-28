package com.product.service;

import com.product.entity.Product;
import com.product.exception.ProductNotFound;
import com.product.payload.ProductDto;
import com.product.repository.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    //    @Autowired
//    private EmailService emailService;
    private ProductRepository repository;

    public ProductService(ProductRepository repo) {
        this.repository = repo;
    }

    public Product convertToEntity(ProductDto dto) {
        Product product = new Product();
        product.setPid(dto.getProductId());
        product.setPName(dto.getProductName());
        product.setPCode(dto.getProductCode());
        product.setPType(dto.getProductType());
        return product;
    }

    public ProductDto convertToDto(Product p) {
        ProductDto dto = new ProductDto();
        dto.setProductId(p.getPid());
        dto.setProductName(p.getPName());
        dto.setProductCode(p.getPCode());
        dto.setProductType(p.getPType());
        return dto;
    }

    public ProductDto saveTheProduct(ProductDto dto) {
        Product product = convertToEntity(dto);
        Product savedProduct = repository.save(product);
//        emailService.sendEmail(savedProduct.getPCode(), "ProductSaved", "Thanks, Your Product "+savedProduct.getPName()+" is successfully saved");
        return convertToDto(savedProduct);
    }

    public ProductDto updateTheProduct(ProductDto dto, long id) {
        Product product = repository.findById(id).orElseThrow(
                () -> new ProductNotFound("Product is Unavailable")
        );
        product.setPName(dto.getProductName());
        product.setPCode(dto.getProductCode());
        product.setPType(dto.getProductType());
        Product saved = repository.save(product);
        return convertToDto(saved);
    }

    public void deleteTheProduct(long id) {
        repository.deleteById(id);
    }

    public ProductDto getProductById(long id) {
        Product found = repository.findById(id).orElseThrow(
                () -> new ProductNotFound("Product is Not Available")
        );
        return convertToDto(found);
    }

    public List<ProductDto> getAllProduct(int pageNo, int pageSize, String sortBy, String orderBy) {
        Sort sort = orderBy.equals("asc") ? Sort.by(Sort.Order.asc(sortBy)) : Sort.by(Sort.Order.desc(sortBy));
        PageRequest pageRequest = PageRequest.of(pageNo, pageSize, sort);
        Page<Product> productsPage = repository.findAll(pageRequest);
        List<Product> products = productsPage.getContent();
        List<ProductDto> productList = products.stream().map(this::convertToDto).toList();
        return productList;
    }
}
