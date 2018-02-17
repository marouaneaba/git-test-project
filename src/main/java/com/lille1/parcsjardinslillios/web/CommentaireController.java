package com.lille1.parcsjardinslillios.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.lille1.parcsjardinslillios.entity.Commentaire;
import com.lille1.parcsjardinslillios.entity.ParcJardin;
import com.lille1.parcsjardinslillios.service.interfaces.CommentaireInterface;
import com.lille1.parcsjardinslillios.service.interfaces.ParcJardinInterface;

import java.util.List;

@Controller
public class CommentaireController {
	@Autowired
	CommentaireInterface commentaireInterfaceMetier;
	@Autowired
	ParcJardinInterface parcJardinInterfaceMetier;

	/**
	 * 
	 * @param model
	 * @return
	 */
	@GetMapping(value = "/NouveauCommentaire")
	public String afficherNouveauCommentaire(Model model) {
		// model.addAttribute("CommentairesByPJ", commentaireInterfaceMetier.);
		model.addAttribute("AllCommentaire", commentaireInterfaceMetier.consulternouveauCommentaires());
		return "Commentaire";
	}

	/**
	 * 
	 * @param id
	 * @return
	 */
	@PostMapping(value = "/NouveauCommentaire/delete")
	public String supprimerCommentaire(Long id) {
		Commentaire tmp = commentaireInterfaceMetier.consulterCommentaireParId(id);
		commentaireInterfaceMetier.supprimerCommentaire(tmp);
		return "redirect:/NouveauCommentaire";
	}

	/**
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@PostMapping(value = "/NouveauCommentaire/confirmer")
	public String confirmerCommentaire(Long id) throws Exception {
		Commentaire tmp = commentaireInterfaceMetier.consulterCommentaireParId(id);
		commentaireInterfaceMetier.validerCommentaire(tmp);
		return "redirect:/NouveauCommentaire";
	}

	/**
	 * 
	 * @param model
	 * @param PJNom
	 * @return
	 */
	@RequestMapping("/chercherCommentaireParNomPJ")
	public String ChercherCommentaireParNomPJ(Model model, @RequestParam(value = "PJNom") String PJNom) {

		if (PJNom.equals("")) {
			model.addAttribute("AllCommentaire", commentaireInterfaceMetier.consulternouveauCommentaires());
		} else {
			ParcJardin tmp = parcJardinInterfaceMetier.chercherPJParNom(PJNom);
			List<Commentaire> listtmpCom = commentaireInterfaceMetier.consulterCommentaireNonValiderParPJ(tmp);
			model.addAttribute("AllCommentaire", listtmpCom);
		}

		return "Commentaire";
	}

}
