package org.studyeasy.showroom.resources;


import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.studyeasy.showroom.model.Brand;
import org.studyeasy.showroom.model.Link;
import org.studyeasy.showroom.services.BrandsService;


@Path("/showroom/brands")
public class BrandsResource {
	BrandsService service = new BrandsService();
	
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Brand> getBrands() {
		List<Brand> list  = service.getBrands();
		return list;
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{brandId}")
	public Brand getBrand(@PathParam("brandId") int brandId, @Context UriInfo uri) {
		
		Link self = new Link(uri.getAbsolutePath().toString(), "self");
		Link products = new Link(uri.getAbsolutePathBuilder().path("products").build().toString(), "product");
		Brand brand = service.getBrand(brandId);
		List<Link> links = new ArrayList<Link>();
		links.add(self);
		links.add(products);
		brand.setLinks(links);
		return brand;
		
	}
	

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response postBrands(Brand brand, @Context UriInfo uri ) {
//	URI location = uri.getAbsolutePath();
		service.addBrand(brand);
		
//		String ResourceURL = uri.getAbsolutePath()+"/"+brand.getBrandId();
//		URI location = URI.create(ResourceURL);
		
		URI location = uri.getAbsolutePathBuilder().path(Integer.toString(brand.getBrandId())).build();
		
		return Response.created(location).entity(brand).build();
				//status(javax.ws.rs.core.Response.Status.CREATED).entity(brand).build();
	}
	
	@PUT
	@Path("/{brandId}")
	@Consumes(MediaType.APPLICATION_JSON)
	public void putBrands(@PathParam("brandId")  int brandId, Brand updatedBrand) {
		updatedBrand.setBrandId(brandId);
		service.updateBrand(updatedBrand);
	}
	
	@DELETE
	@Path("/{brandId}")
	public void deleteBrands(@PathParam("brandId") int brandId) {
		service.deleteBrand(brandId);
	}
	
	@Path("/{brandId}/products")
	public ProductsResource productsDelegation() {
		return new ProductsResource();
		
	}


}
