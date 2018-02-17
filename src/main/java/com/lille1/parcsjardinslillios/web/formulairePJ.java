package com.lille1.parcsjardinslillios.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.lille1.parcsjardinslillios.entity.Horaire;
import com.lille1.parcsjardinslillios.entity.ParcJardin;
import com.lille1.parcsjardinslillios.service.interfaces.CategorieInterface;

import java.util.ArrayList;
import java.util.List;

@Controller
public class formulairePJ {

	@Autowired
	private CategorieInterface categorieInterfaceMetier;

	/**
	 * 
	 * @param model
	 * @return
	 */
	@GetMapping(value = "/AjouterPJ")
	public String afficherFormulairePJ(Model model) {

		Horaire lundi = new Horaire(null, null, "Lundi", null);
		Horaire mardi = new Horaire(null, null, "Mardi", null);

		List<Horaire> listHoraire = new ArrayList<>();
		listHoraire.add(lundi);
		listHoraire.add(mardi);

		model.addAttribute("Allcategories", categorieInterfaceMetier.consulterCategories());
		/*
		 * model.addAttribute("hlundi", new Horaire());
		 * model.addAttribute("hmardi", new Horaire());
		 * model.addAttribute("hmercredi", new Horaire());
		 * model.addAttribute("hjeudi", new Horaire());
		 * model.addAttribute("hvendredi", new Horaire());
		 * model.addAttribute("hsamedi", new Horaire());
		 * model.addAttribute("hdimanche", new Horaire());
		 */

		model.addAttribute("AllHoraire", listHoraire);
		model.addAttribute("nouveauPJ", new ParcJardin());
		return "AjouterPJ";
	}

}
