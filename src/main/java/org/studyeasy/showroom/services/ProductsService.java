package org.studyeasy.showroom.services;

import java.util.List;

import org.studyeasy.showroom.DAO.ProductsDAO;
import org.studyeasy.showroom.model.Product;

public class ProductsService {

	ProductsDAO dao = new ProductsDAO();
	public List<Product> getProductsByBrand(int brandId) {
		
		List<Product> Productlist = dao.getProductsByBrand(brandId);
		return Productlist;
	}
	public List<Product> getProductsByBrandAndCategory(int brandId, String category) {
		
		List<Product> productList = dao.getProductsByBrandAndCategory(brandId, category);
		return productList;
	}

}
