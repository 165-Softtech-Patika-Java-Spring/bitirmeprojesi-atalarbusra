package com.graduation.graduation.app.pro.dao;

import com.graduation.graduation.app.pro.dto.ProProductDetails;
import com.graduation.graduation.app.pro.entity.ProProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProProductDao extends JpaRepository<ProProduct, Long> {
    @Query(nativeQuery = true, value = "SELECT * FROM PRO_PRODUCT pr WHERE pr.ID_PRODUCT_TYPE = :productTypeId")
    List<ProProduct> findByProductTypeId(@Param("productTypeId") Long productTypeId);

    @Query(nativeQuery = true, value = "SELECT * FROM PRO_PRODUCT pr WHERE pr.PRICE >= :minPrice and pr.PRICE <= :maxPrice")
    List<ProProduct> findProductWithPrice(@Param("minPrice") double minPrice, @Param("maxPrice") double maxPrice);

    @Query(
            " select " +
                    " new com.graduation.graduation.app.pro.dto.ProProductDetails( " +
                    " pr.productTypeId," +
                    " prt.name," +
                    " prt.kdvRate," +
                    " min(pr.priceWithKdv)," +
                    " max(pr.priceWithKdv)," +
                    " avg(pr.priceWithKdv)," +
                    " count(pr.productTypeId)" +
                    ") " +
                    " from ProProduct pr " +
                    " left join ProProductType prt on prt.id = pr.productTypeId " +
                    " group by pr.productTypeId "
    )
    List<ProProductDetails> getProductDetails();

}
