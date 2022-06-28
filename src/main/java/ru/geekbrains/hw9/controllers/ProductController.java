package ru.geekbrains.hw9.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.hw9.dto.ProductDto;
import ru.geekbrains.hw9.exceptions.ResourceNotFoundException;
import ru.geekbrains.hw9.model.Category;
import ru.geekbrains.hw9.model.Product;
import ru.geekbrains.hw9.services.CategoryService;
import ru.geekbrains.hw9.services.ProductService;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    private final CategoryService categoryService;

    @GetMapping("/products")
    public Page<ProductDto> findAll(@RequestParam(name = "p", defaultValue = "1") int pageIndex) {
        if (pageIndex < 1) {
            pageIndex = 1;
        }
        return productService.findAll(pageIndex - 1, 5).map(ProductDto::new);
    }

    @GetMapping("/products/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ProductDto findById(@PathVariable Long id) {
        return new ProductDto(productService
                .findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Product id = "+ id +" not found")));
    }


    @PostMapping("/products")
    @ResponseStatus(HttpStatus.CREATED)
    public ProductDto save(@RequestBody ProductDto productDto) {
        Product product = new Product();
        product.setId(productDto.getId());
        product.setTitle(productDto.getTitle());
        product.setPrice(productDto.getPrice());
        Category category = categoryService.findByTitle(productDto
                .getCategoryTitle())
                .orElseThrow(()-> new ResourceNotFoundException("Category title = "+ productDto.getCategoryTitle() +" not found"));
        product.setCategory(category);
        productService.save(product);
        return new ProductDto(product);
    }

    @DeleteMapping("/products")
    @ResponseStatus(HttpStatus.OK)
    public void deleteAll(){
        productService.deleteAll();
    }

    @DeleteMapping("/products/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteById(@PathVariable Long id){
        productService.deleteById(id);
    }

    @PutMapping("/products")
    @ResponseStatus(HttpStatus.OK)
    public void updateProduct( @RequestBody ProductDto productDto) {//@PathVariable Long id,
        productService.updateProduct(productDto);
    }

}
