package org.studyeasy.showroom.resources;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.studyeasy.showroom.hibernate.entities.ProductEntity;
import org.studyeasy.showroom.services.ProductsService;

//@Path("/showroom/brands")
public class Products {
	
	ProductsService productService = new ProductsService();
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<ProductEntity> getProducts(@PathParam("brandId") int brandId, @QueryParam("category") String category,
			@QueryParam("start") int start, @QueryParam("end") int end) {
		
		List<ProductEntity> Productlist;
		if (category != null) {
			Productlist = productService.getProductsByBrandAndCategory(brandId, category);
		}else {
		Productlist  = productService.getProductsByBrand(brandId);
		}
		
		if(end > 0) {
			Productlist = Productlist.subList(start, end);
		}
		
		return Productlist;
	}

}
