package it.marconi.catalogoprodotti.services;

import org.springframework.stereotype.Service;

import it.marconi.catalogoprodotti.domains.Prodotto;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProdottoService {

    private final List<Prodotto> catalogo = new ArrayList<>();

    public List<Prodotto> getCatalogo() {
        return catalogo;
    }

    public void aggiungiProdotto(Prodotto prodotto) {
        catalogo.add(prodotto);
    }

    public void svuotaCatalogo() {
        catalogo.clear();
    }

    public Prodotto getProdottoByCodice(String codice) {
        for (Prodotto prodotto : catalogo) {
            if (prodotto.getCodice().equals(codice)) {
                return prodotto;
            }
        }
        return null;
    }

    public void eliminaProdottoByCodice(String codice) {
        catalogo.removeIf(prodotto -> prodotto.getCodice().equals(codice));
    }
}
