package br.com.equipe4.app_produtos.repository;

import br.com.equipe4.app_produtos.model.Products;
import br.com.equipe4.app_produtos.service.dto.ProductDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductsRepository extends JpaRepository<Products, Long> {

    //Projection
    @Query(nativeQuery = true, value = """
            SELECT p.id, 
            p.barcode AS barcode, 
            p.name AS name, 
            p.price AS price,
            p.category_id AS categoryId
            FROM products p 
            WHERE p.id = :id
            """)
    ProductDto findByIdDto(long id);
}
