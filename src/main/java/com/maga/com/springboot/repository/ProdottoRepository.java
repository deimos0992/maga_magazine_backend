package com.maga.com.springboot.repository;

import com.maga.com.springboot.model.Prodotto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProdottoRepository extends JpaRepository<Prodotto, Long> {

    @Query(value="SELECT * FROM prodotto p WHERE p.product_code LIKE %:prodCode%", nativeQuery=true)
    Optional<List<Prodotto>> findByCD(@Param("prodCode") String prodCode);
}
