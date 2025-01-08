package com.alten.ecommerce.dto;

import com.alten.ecommerce.model.Product;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ProductDtoTest {

    @Test
    public void testProductDtoGettersAndSetters() {
        ProductDto productDto = new ProductDto();

        productDto.setId(1L);
        productDto.setCode("P123");
        productDto.setName("Test Product");
        productDto.setDescription("A sample product description.");
        productDto.setImage("image_url");
        productDto.setCategory("Electronics");
        productDto.setPrice(99.99);
        productDto.setQuantity(10);
        productDto.setInventoryStatus(Product.InventoryStatus.INSTOCK);
        productDto.setRating(4.5);

        assertThat(productDto.getId()).isEqualTo(1L);
        assertThat(productDto.getCode()).isEqualTo("P123");
        assertThat(productDto.getName()).isEqualTo("Test Product");
        assertThat(productDto.getDescription()).isEqualTo("A sample product description.");
        assertThat(productDto.getImage()).isEqualTo("image_url");
        assertThat(productDto.getCategory()).isEqualTo("Electronics");
        assertThat(productDto.getPrice()).isEqualTo(99.99);
        assertThat(productDto.getQuantity()).isEqualTo(10);
        assertThat(productDto.getInventoryStatus()).isEqualTo(Product.InventoryStatus.INSTOCK);
        assertThat(productDto.getRating()).isEqualTo(4.5);
    }

    @Test
    public void testProductDtoEquality() {
        ProductDto product1 = new ProductDto();
        product1.setId(1L);
        product1.setName("Test Product");

        ProductDto product2 = new ProductDto();
        product2.setId(1L);
        product2.setName("Test Product");

        assertThat(product1).isEqualTo(product2);

        product2.setName("Another Product");
        assertThat(product1).isNotEqualTo(product2);
    }

    @Test
    public void testProductDtoToString() {
        ProductDto productDto = new ProductDto();
        productDto.setId(1L);
        productDto.setName("Test Product");

        String expectedString = "ProductDto(id=1, code=null, name=Test Product, description=null, image=null, category=null, price=0.0, quantity=0, inventoryStatus=null, rating=0.0)";
        assertThat(productDto.toString()).isEqualTo(expectedString);
    }
}
