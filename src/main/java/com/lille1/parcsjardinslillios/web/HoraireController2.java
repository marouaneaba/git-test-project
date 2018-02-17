package com.lille1.parcsjardinslillios.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import com.lille1.parcsjardinslillios.entity.Horaire;
import com.lille1.parcsjardinslillios.entity.ParcJardin;
import com.lille1.parcsjardinslillios.repository.HoraireRepository;
import com.lille1.parcsjardinslillios.service.interfaces.HoraireInterface;
import com.lille1.parcsjardinslillios.service.interfaces.ParcJardinInterface;

import java.util.List;

@Controller
public class HoraireController2 {

	@Autowired
	HoraireInterface horaireInterfaceMetier;
	@Autowired
	HoraireRepository horaireRepository;
	@Autowired
	ParcJardinInterface parcJardinInterfaceMetier;

	/**
	 * 
	 * @param model
	 * @return
	 */
	@GetMapping(value = "/Operatiohoraire")
	public String GetOperationHoraire(Model model) {
		return "operationHoraire";
	}

	/**
	 * 
	 * @param model
	 * @param PJ
	 * @param PJNom
	 * @return
	 */
	@GetMapping(value = "/chercherHoraireParPJ")
	public String ChercherHoraireParPJ(Model model, String PJ, String PJNom) {
		ParcJardin foundPJ = parcJardinInterfaceMetier.chercherPJParNom(PJNom);
		List<Horaire> foundHoraire = horaireRepository.findByParcJardin(foundPJ);
		model.addAttribute("foundHoraires", foundHoraire);
		return "operationHoraire";
	}

	/**
	 * 
	 * @param idH
	 * @param model
	 * @return
	 */
	@GetMapping(value = "/ModifierHoraire")
	public String GetModifierHoraire(Long idH, Model model) {
		Horaire tmphoraire = horaireRepository.findById(idH);
		model.addAttribute("horaireAmodifer", tmphoraire);
		return "modifierhoraire";
	}

	/**
	 * 
	 * @param model
	 * @param horaire
	 * @param idH
	 * @return
	 */
	@PostMapping(value = "/ModifierHoraire")
	public String PostModifierHoraire(Model model, Horaire horaire, Long idH) {
		horaireInterfaceMetier.modifierHorairePJ(idH, horaire.getJournee(), horaire.getOuverture(),
				horaire.getFermeture());

		return "operationHoraire";

	}

	/**
	 * 
	 * @param idH
	 * @return
	 */
	@PostMapping(value = "/nhoraire/delete")
	public String deteleHoraire(Long idH) {
		Horaire tmp = horaireRepository.findById(idH);
		horaireRepository.delete(tmp);
		return "operationHoraire";
	}

}
