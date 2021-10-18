package com.maga.com.springboot.service;

import com.maga.com.springboot.dto.MagazzinoDto;
import com.maga.com.springboot.model.Magazzino;
import com.maga.com.springboot.model.Prodotto;
import com.maga.com.springboot.repository.MagazzinoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MagazzinoService {

    @Autowired
    private MagazzinoRepository magazzinoRepository;

    public List<Magazzino> getAllMagazzini() {
        return magazzinoRepository.findAll();
    }

    public Magazzino saveSingleMagazzino(MagazzinoDto magazzinoDto){
        Magazzino magazzino = new Magazzino();

        magazzino.setDescrizione(magazzinoDto.getDescrizione());
        magazzino.setType(magazzinoDto.getType());
        magazzinoRepository.save(magazzino);
        return magazzino;
    }

    public Magazzino getAllProductForSingleStore(Long id){
        Magazzino magazzino = magazzinoRepository.getById(id);
        return magazzino;

    }
}
