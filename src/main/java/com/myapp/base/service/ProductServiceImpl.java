package com.myapp.base.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myapp.base.entity.Product;
import com.myapp.base.model.ProductModel;
import com.myapp.base.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;
	
	 @Autowired
	 private ModelMapper modelMapper;
	 
	@Override
	public ProductModel addProduct(ProductModel productModel) {
		productRepository.save(convertToProduct(productModel));
		return productModel;
	}

	private Product convertToProduct(ProductModel  productModel) {
		Product  product = modelMapper.map(productModel, Product.class);	    
	    return product;
	}
	
	private ProductModel convertToProductModel(Product product) {
		ProductModel productModel = modelMapper.map(product, ProductModel.class);	    
	    return productModel;
	}

	@Override
	public List<ProductModel> getAllProducts() {
		return productRepository.findAll().stream().map(
				this::convertToProductModel
				).collect(Collectors.toList());
	}

}
