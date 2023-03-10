package orsys.projet.controller;

import java.util.Date;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import lombok.AllArgsConstructor;
import orsys.projet.business.Utilisateur;
import orsys.projet.service.FileService;
import orsys.projet.service.LienDeParenteService;
import orsys.projet.service.LocationService;
import orsys.projet.service.ParasolService;
import orsys.projet.service.PaysService;
import orsys.projet.service.StatutService;
import orsys.projet.service.UtilisateurService;

@Controller
@AllArgsConstructor
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200")
public class ProjetController {

	private final FileService fileService;
	private final LienDeParenteService lienDeParenteService;
	private final LocationService locationService;
	private final ParasolService parasolService;
	private final PaysService paysService;
	private final StatutService statutService;
	private final UtilisateurService utilisateurService;
	private final HttpSession httpSession;
	
	@GetMapping(value = {"/index", "/"})
	public ModelAndView accueil() {
		System.out.println(new Date() + " dans l'acceuil");
		ModelAndView mav = new ModelAndView();
		mav.setViewName("index");
		return mav;
	}
	
	@PostMapping("/connexion")
	public ModelAndView connexion(@RequestParam("EMAIL") String email, @RequestParam("MOT_DE_PASSE") String motDePasse) {
		Utilisateur utilisateur = utilisateurService.recupererUtilisateur(email, motDePasse);
		if(utilisateur==null) {
			ModelAndView mav = accueil();
			mav.addObject("Notification", "Mot de passe et/ou email incorrect");
			return mav;
		} else {
			httpSession.setAttribute("utilisateurs", utilisateur);
			ModelAndView mav = new ModelAndView("redirect:projet");
			return mav;
		}
	}
	
	@GetMapping("/projet")
	public ModelAndView projet() {
		ModelAndView mav = new ModelAndView("projet");
		return mav;
	}
}
