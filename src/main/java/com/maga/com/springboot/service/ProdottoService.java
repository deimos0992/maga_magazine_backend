package com.maga.com.springboot.service;

import com.maga.com.springboot.Utils.Constants;
import com.maga.com.springboot.Utils.Utils;
import com.maga.com.springboot.dto.ProdottoDto;
import com.maga.com.springboot.exception.ProdottoException;
import com.maga.com.springboot.model.Magazzino;
import com.maga.com.springboot.model.Prodotto;
import com.maga.com.springboot.repository.ProdottoRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class ProdottoService {

    @Autowired
    private ProdottoRepository prodottoRepository;

    @Autowired
    private MagazzinoService magazzinoService;

    AtomicInteger count = new AtomicInteger(0);

    public List<Prodotto> getAllProdotti() {
        return prodottoRepository.findAll();
    }

    public Prodotto saveProduct(ProdottoDto prodottoDto) {
        Prodotto prodotto = new Prodotto();
        Magazzino magazzino = magazzinoService.getAllProductForSingleStore(Long.parseLong(prodottoDto.getTypeCode()));
        prodotto.setProductCode(getProductCode(prodottoDto.getTypeCode()));
        prodotto.setName(prodottoDto.getName());
        prodotto.setPrice(prodottoDto.getPrice());
        prodotto.setMagazzino(magazzino);
        prodottoRepository.save(prodotto);
        return prodotto;
    }

    public Prodotto getSingleProduct(Long id) {
        Optional<Prodotto> prodotto = prodottoRepository.findById(id);
        return prodotto.orElseThrow(() -> new ProdottoException("prodotto con id: " + id + " non trovato"));
    }

    public void deleteProduct(Long id){


//        prodottoRepository.delete();
    }

    public Prodotto modifySingleUser(Long id, ProdottoDto prodottoDto) {

        Prodotto prodotto = getSingleProduct(id);
        prodotto.setProductCode(prodotto.getProductCode());
        prodotto.setName(prodottoDto.getName());
        prodotto.setPrice(prodottoDto.getPrice());
        prodotto.setMagazzino(prodotto.getMagazzino());
        prodottoRepository.save(prodotto);

        return prodotto;
    }

    private String getProductCode(String typeCode){

        LocalDateTime now = LocalDateTime.now();
        String year = String.valueOf(now.getYear()).substring(2,4);
        String month = String.valueOf(now.getMonthValue());
        String day = String.valueOf(now.getDayOfMonth());
        String index = "";
        String cd = year + "/" + month + day;
        String productCode="";

        switch (typeCode){
            case "1":
                count.getAndSet(getLastIndex(Constants.CODE_LIBRO));
                count.incrementAndGet();
                index = Utils.getindex(count.get());
                productCode = cd + Constants.CODE_LIBRO + index;
                break;
            case "2":
                count.getAndSet(getLastIndex(Constants.CODE_ABBIGLIAMENTO));
                count.incrementAndGet();
                index = Utils.getindex(count.get());
                productCode = cd + Constants.CODE_ABBIGLIAMENTO + index;
                break;
            case "3":
                productCode = cd + Constants.CODE_SPORT + index;
                break;
            case "4":
                productCode = cd + Constants.CODE_UTINSILERIA + index;
                break;
            case "5":
                productCode = cd + Constants.CODE_GAMING + index;
                break;
        }
        return productCode;
    }

    private Integer getLastIndex(String code){
        Integer getLastProductCode = 0;

        List<Prodotto> prodottoList = prodottoRepository.findByCD(code).orElse(new ArrayList<Prodotto>());
        if (!prodottoList.isEmpty()){
            Prodotto prodotto = prodottoList.get(prodottoList.size()-1);
            getLastProductCode = Integer.valueOf(StringUtils.right(prodotto.getProductCode(), 3));
        }
        return getLastProductCode;
    }

    private String getTypeProduct(String typeCode){
        String typeDescription = "";
        switch (typeCode){
            case "1":
                typeDescription = "Libri";
                break;
            case "2":
                typeDescription = "Abbigliamento";
                break;
            case "3":
                typeDescription = "Sport";
                break;
            case "4":
                typeDescription = "Utinsileria";
                break;
            case "5":
                typeDescription = "Gaming";
                break;
        }
        return typeDescription;
    }
}
