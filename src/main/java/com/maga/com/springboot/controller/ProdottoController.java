package com.maga.com.springboot.controller;

import com.maga.com.springboot.dto.ProdottoDto;
import com.maga.com.springboot.model.Prodotto;
import com.maga.com.springboot.service.ProdottoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/prodotto")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ProdottoController {

    @Autowired
    private ProdottoService productService;

    @GetMapping("/product")
    public ResponseEntity<List<Prodotto>> getAllProduct() {
        List<Prodotto> utentiList = productService.getAllProdotti();
        return ResponseEntity.ok(utentiList);
    }

    @PostMapping("/product")
    public ResponseEntity<Prodotto> saveSingleProduct(@RequestBody ProdottoDto prodottoDto) {
        Prodotto product = productService.saveProduct(prodottoDto);
        return ResponseEntity.ok(product);
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<Prodotto> getSingleProduct(@RequestParam Long id) {
        Prodotto product = productService.getSingleProduct(id);
        return ResponseEntity.ok(product);
    }

    @PutMapping("/product/{id}")
    public ResponseEntity<Prodotto> putSingleProduct(@RequestParam Long id, @RequestBody ProdottoDto prodottoDto) {
        Prodotto utente = productService.modifySingleUser(id, prodottoDto);
        return ResponseEntity.ok(utente);

    }
}
