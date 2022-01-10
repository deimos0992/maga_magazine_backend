package com.maga.com.springboot.controller;

import com.maga.com.springboot.dto.MagazzinoDto;
import com.maga.com.springboot.model.Magazzino;
import com.maga.com.springboot.service.MagazzinoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/magazzino")
public class MagazzinoController {

    @Autowired
    private MagazzinoService magazzinoService;

    @GetMapping("/store")
    public ResponseEntity<List<Magazzino>> getAllMagazzini() {
        List<Magazzino> magazzinoList = magazzinoService.getAllMagazzini();
        return ResponseEntity.ok(magazzinoList);
    }

    @PostMapping("/store")
    public ResponseEntity<Magazzino> saveSingleMagazzine(@RequestBody MagazzinoDto magazzinoDto) {
        Magazzino magazzino = magazzinoService.saveSingleMagazzino(magazzinoDto);
        return ResponseEntity.ok(magazzino);
    }

    @GetMapping("/store/{id}")
    public ResponseEntity<Magazzino> getAllProductForStore(@RequestParam Long id) {
        Magazzino magazzino = magazzinoService.getAllProductForSingleStore(id);
        return ResponseEntity.ok(magazzino);
    }
}
