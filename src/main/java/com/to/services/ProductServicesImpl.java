package com.to.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.to.entities.Product;
import com.to.entities.UserPurchase;
import com.to.exceptions.EtBadRequestException;
import com.to.exceptions.EtResourceNotFoundException;
import com.to.repositories.ProductRepository;

@Service
@Transactional
public class ProductServicesImpl implements ProductServices {

	@Autowired
	ProductRepository productRepository;

	@Override
	public void createNewProduct(Product product) throws EtBadRequestException {
		productRepository.create(product);

	}

	@Override
	public void updateProduct(Integer userId, Integer purId, UserPurchase userPurchase) throws EtBadRequestException {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteProduct(Integer pId) throws EtResourceNotFoundException {
		productRepository.delete(pId);

	}

	@Override
	public Product getProductById(Integer pid) throws EtResourceNotFoundException {
		// TODO Auto-generated method stub
		return productRepository.getById(pid);
	}

	@Override
	public List<Product> getAllProductDetails() throws EtResourceNotFoundException {

		return productRepository.getAllProduct();
	}

}
