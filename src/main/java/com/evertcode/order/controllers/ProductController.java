package com.evertcode.order.controllers;

import com.evertcode.order.dtos.OrderDTO;
import com.evertcode.order.dtos.ProductDTO;
import com.evertcode.order.services.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

	private final ProductService productService;

	public ProductController(final ProductService productService) {
		this.productService = productService;
	}

	@GetMapping
	@Operation(summary = "Get all products",
			description = "Get all products ordered by product name",
			tags = {"Product"})

	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Success",
					content = {@Content(mediaType = "application/json",
							schema = @Schema(implementation = OrderDTO.class))}),
			@ApiResponse(responseCode = "400", description = "Bad request")})

	private ResponseEntity<List<ProductDTO>> findAllByOrderByName() {
		List<ProductDTO> productList = productService.findAllByOrderByName();
		return ResponseEntity.ok().body(productList);
	}
}
