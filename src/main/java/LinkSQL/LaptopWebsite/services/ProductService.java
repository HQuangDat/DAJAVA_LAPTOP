package LinkSQL.LaptopWebsite.services;

import LinkSQL.LaptopWebsite.model.Product;
import LinkSQL.LaptopWebsite.repository.IProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProductService {
    @Autowired
    private IProductRepository productRepository;
    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }
    public Product getProductById(long id){
        return productRepository.findById(id).orElse(null);
    }

    public void addProduct(Product product){
        productRepository.save(product);
    }
    public void updateProduct(Product product){
        productRepository.save(product);
    }
    public void deleteProduct(Long id){
        productRepository.deleteById(id);
    }

    //Service search + page
    public Page<Product> GetAll(int pageNo, int pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNo, pageSize);
        return productRepository.findAll(pageRequest);
    }
    public Page<Product> GetSearchProducts(String key, int pageNo, int
            pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        return productRepository.searchProducts(key, pageable);
    }


}
