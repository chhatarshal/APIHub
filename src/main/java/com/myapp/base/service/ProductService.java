package com.myapp.base.service;

import java.util.List;

import com.myapp.base.model.ProductModel;

public interface ProductService {
	
	ProductModel addProduct(ProductModel productModel);
	List<ProductModel> getAllProducts();

}
