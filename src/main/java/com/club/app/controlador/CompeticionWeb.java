package com.club.app.controlador;


import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.club.app.entidades.Competicion;
import com.club.app.repositorio.CompeticionRepositorio;


@Controller

public class CompeticionWeb {

	
	@Autowired
	private  CompeticionRepositorio competicionrepositorio;
	
	@GetMapping({"/vercompeticion"})
	public String listarCompeticion(Model model) {
		List<Competicion> listaCompeticion = competicionrepositorio.findAll();
		model.addAttribute("listaCompeticion", listaCompeticion);
		return "vercompeticion";
	}
	
	@GetMapping("/formcompeticion")
	public String mostrarFormulario (Model model) {
		model.addAttribute("competicion", new Competicion());
	
	    
		List<Competicion> listaCompeticion = competicionrepositorio.findAll();
		model.addAttribute("listaCompeticion", listaCompeticion);

		return "formcompeticion";
	}
	
	@PostMapping("/guardarcompeticion")
	public String guardarCompeticion (Competicion competicion) {
		competicionrepositorio.save(competicion);
		return "redirect:/vercompeticion";
	}
	
	@GetMapping("/editarcompeticion/{id}")
	public String modificarCopeticion (@PathVariable("id") Long id, Model model) {
		Competicion competicion = competicionrepositorio.findById(id).get();
		model.addAttribute("competicion",competicion);
		List<Competicion> listaCompeticion = competicionrepositorio.findAll();
		model.addAttribute("listaCompeticion", listaCompeticion);

		return "formcompeticion";
		
	}
	
	@GetMapping("/eliminarcompeticion/{id}")
	public String eliminarcompeticionn(@PathVariable("id") Long id, Model model) {
		competicionrepositorio.deleteById(id);
		return "redirect:/vercompeticion";
	}
	
	
}
