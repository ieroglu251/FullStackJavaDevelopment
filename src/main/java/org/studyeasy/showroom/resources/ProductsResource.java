package org.studyeasy.showroom.resources;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.studyeasy.showroom.model.Product;
import org.studyeasy.showroom.services.ProductsService;

//@Path("/showroom/brands")
public class ProductsResource {
	
	ProductsService productService = new ProductsService();
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Product> getProducts(@PathParam("brandId") int brandId, @QueryParam("category") String category,
			@QueryParam("start") int start, @QueryParam("end") int end) {
		
		List<Product> Productlist;
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
