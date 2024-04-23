package hu.webler.weblerstoragemanager.util;

import hu.webler.weblerstoragemanager.entity.Product;
import hu.webler.weblerstoragemanager.persistence.ProductRepository;

import java.util.List;

public class StockUpdater {

    private ProductRepository productRepository;
    public void stockIncrease(List<Product> productList){
        for (int i = 0; i < productList.size(); i++){
            Long productId = productList.get(i).getId();
            int stockNow = productRepository.findById(productId).get().getQuantity();
            int stockWillBe = stockNow + productList.get(i).getQuantity();
            productRepository.findById(productId).get().setQuantity(stockWillBe);
        }
    }

    public void stockDecrease(List<Product> productList){
        for (int i = 0; i < productList.size(); i++){
            Long productId = productList.get(i).getId();
            int stockNow = productRepository.findById(productId).get().getQuantity();
            int stockWillBe = stockNow - productList.get(i).getQuantity();
            productRepository.findById(productId).get().setQuantity(stockWillBe);
        }
    }
}
