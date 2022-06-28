package ru.geekbrains.hw9.services;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.geekbrains.hw9.dto.ProductDto;
import ru.geekbrains.hw9.exceptions.ResourceNotFoundException;
import ru.geekbrains.hw9.model.Category;
import ru.geekbrains.hw9.model.Product;
import ru.geekbrains.hw9.repositories.ProductRepository;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final CategoryService categoryService;

    public Page<Product> findAll(int pageIndex, int pageSize){
        return productRepository.findAll(PageRequest.of(pageIndex, pageSize));
    }

    public Optional<Product> findById(Long id){
        return productRepository.findById(id);
    }

    public Product save(Product product){
        return productRepository.save(product);
    }

    public void deleteById(Long id){
        productRepository.deleteById(id);
    }

    public void deleteAll(){
        productRepository.deleteAll();
    }

    public Product findByIdFromUpdate(Long id){
        return productRepository.getReferenceById(id);
    }

    @Transactional
    public void updateProduct(ProductDto productDto){
        //   Product product = findByIdFromUpdate(productDto.getId());
        Product product = findById(productDto.getId()).get();
        //   product.setId(productDto.getId());

        product.setTitle(productDto.getTitle());
        product.setPrice(productDto.getPrice());
        Category category = categoryService.findByTitle(productDto
                .getCategoryTitle())
                .orElseThrow(()-> new ResourceNotFoundException("Category title = "+ productDto.getCategoryTitle() +" not found"));
        product.setCategory(category);

    }
}















