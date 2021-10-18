package com.maga.com.springboot.repository;

import com.maga.com.springboot.model.Magazzino;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MagazzinoRepository extends JpaRepository<Magazzino, Long> {
}
