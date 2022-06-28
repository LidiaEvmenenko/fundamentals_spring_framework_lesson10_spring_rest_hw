package ru.geekbrains.hw9.services;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.geekbrains.hw9.dto.ProductDto;
import ru.geekbrains.hw9.model.Cart;
import ru.geekbrains.hw9.repositories.CartRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CartService {
    private final CartRepository cartRepository;

    public Page<Cart> findAll(int pageIndex, int pageSize){
        return cartRepository.findAll(PageRequest.of(pageIndex, pageSize));
    }

    public void deleteById(Long id){
        cartRepository.deleteById(id);
    }

    public void deleteAll(){
        cartRepository.deleteAll();
    }

    @Transactional
    public void addNewProductToCart(ProductDto productDto){
        Cart cart = cartRepository.findByTitle(productDto.getTitle());
        if(cart == null) {
            cart = new Cart();
            cart.setTitle(productDto.getTitle());
            cart.setPrice(productDto.getPrice());
            cart.setAmount(1);
        }else {

            cart.setAmount( cart.getAmount() + 1);
        }
        cartRepository.save(cart);
    }
    public int sumItogCart(){
        List<Cart> carts = cartRepository.findAll();
        int sum = 0;
        for (int i=0; i<carts.size(); i++){
            sum = sum + carts.get(i).getPrice() * carts.get(i).getAmount();
        }
        return sum;
    }
}
