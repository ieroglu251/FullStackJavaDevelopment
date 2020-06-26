package org.studyeasy.showroom.services;

import java.util.List;

import org.studyeasy.showroom.hibernate.DAO.ProductsDAO;
import org.studyeasy.showroom.hibernate.entities.ProductEntity;

public class ProductsService {

	ProductsDAO dao = new ProductsDAO();
	public List<ProductEntity> getProductsByBrand(int brandId) {
		
		List<ProductEntity> Productlist = dao.getProductsByBrand(brandId);
		return Productlist;
	}
	public List<ProductEntity> getProductsByBrandAndCategory(int brandId, String category) {
		
		List<ProductEntity> productList = dao.getProductsByBrandAndCategory(brandId, category);
		return productList;
	}

}
