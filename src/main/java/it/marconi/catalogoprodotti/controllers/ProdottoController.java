package it.marconi.catalogoprodotti.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import it.marconi.catalogoprodotti.domains.Prodotto;
import it.marconi.catalogoprodotti.services.ProdottoService;

@Controller
public class ProdottoController {

    @Autowired
    private ProdottoService prodottoService;

    @GetMapping("/catalogo")
    public String visualizzaCatalogo(Model model) {
        model.addAttribute("prodotti", prodottoService.getCatalogo());
        return "catalogo";
    }

    @GetMapping("/prodotto/nuovo")
    public String mostraFormNuovoProdotto(Model model) {
        model.addAttribute("prodotto", new Prodotto());
        return "formNuovoProdotto";
    }

    @PostMapping("/prodotto/nuovo")
    public String aggiungiProdotto(@ModelAttribute Prodotto prodotto) {
        prodottoService.aggiungiProdotto(prodotto);
        return "redirect:/catalogo";
    }

    @GetMapping("/prodotto/{codice}")
    public String mostraDettaglioProdotto(@PathVariable String codice, Model model) {
        Prodotto prodotto = prodottoService.getProdottoByCodice(codice);
        if (prodotto != null) {
            model.addAttribute("prodotto", prodotto);
            return "dettaglioProdotto";
        } else {
            return "redirect:/catalogo";
        }
    }

    @PostMapping("/prodotto/{codice}/elimina")
    public String eliminaProdotto(@PathVariable String codice) {
        prodottoService.eliminaProdottoByCodice(codice);
        return "redirect:/catalogo";
    }


    @PostMapping("/catalogo/svuota")
    public String svuotaCatalogo() {
        prodottoService.svuotaCatalogo();
        return "redirect:/catalogo";
    }
}
