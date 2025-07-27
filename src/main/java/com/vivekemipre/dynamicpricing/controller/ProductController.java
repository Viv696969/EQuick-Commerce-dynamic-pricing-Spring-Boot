package com.vivekemipre.dynamicpricing.controller;


import com.vivekemipre.dynamicpricing.dto.PagedResponse;
import com.vivekemipre.dynamicpricing.dto.ProductDto;
import com.vivekemipre.dynamicpricing.dto.ProductResponse;
import com.vivekemipre.dynamicpricing.dto.SingleResponseDto;
import com.vivekemipre.dynamicpricing.service.ProductServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    @Autowired
    private ProductServiceInterface productService;


    @PostMapping("/v1/add-product")
    public ResponseEntity<SingleResponseDto> addProductV1(@RequestBody ProductDto productDto){
        productService.addProduct(
                productDto.getProductName(),
                productDto.getProductBrand(),
                productDto.getProductPrice(),
                productDto.getProductDescription(),
                productDto.getEatingTimes()
        );
        return ResponseEntity.status(201).body(
                new SingleResponseDto("Product "+productDto.getProductName()+" added Successfully")
        );
    }

    @GetMapping("/v1/view-product/{productId}/{city}/{pinCode}")
    public ResponseEntity<ProductResponse> viewProductV1(@PathVariable("productId") String productId,
                                           @PathVariable("city") String city,
                                           @PathVariable("pinCode") int pinCode
                                           ){
        ProductResponse productResponse=productService.viewProduct(productId,city,pinCode);
        return ResponseEntity.status(200).body(productResponse);
    }

    @GetMapping("/v1/view-products/{page}/{size}/{city}/{pinCode}")
    public ResponseEntity<PagedResponse<ProductResponse>> viewProducts(
            @PathVariable("page") int page,
            @PathVariable("size") int size,
            @PathVariable("city") String city,
            @PathVariable("pinCode") int pinCode){

       PagedResponse<ProductResponse> pagedResponse= productService.getProducts(page,size,city,pinCode);
       return ResponseEntity.status(200).body(pagedResponse);
    }

}
