package com.club.app.controlador;


import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.club.app.entidades.Entrenador;
import com.club.app.repositorio.EntrenadorRepositorio;


@Controller

public class EntrenadorWeb {

	@Autowired
	private  EntrenadorRepositorio entrenadorrepositorio;
	
	@GetMapping({"/verentrenador"})
	public String listarEntrenador(Model model) {
		List<Entrenador> listaEntrenador = entrenadorrepositorio.findAll();
		model.addAttribute("listaEntrenador", listaEntrenador);
		return "verentrenador";
	}
	
	@GetMapping("/formentrenador")
	public String mostrarFormulario (Model model) {
		model.addAttribute("entrenador", new Entrenador());
	
		List<Entrenador> listaEntrenador = entrenadorrepositorio.findAll();
		model.addAttribute("listaEntrenador", listaEntrenador);

		return "formentrenador";
	}
	
	@PostMapping("/guardarentrenador")
	public String guardarEnrenador (Entrenador entrenador) {
		entrenadorrepositorio.save(entrenador);
		return "redirect:/verentrenador";
	}
	
	@GetMapping("/editarentrenador/{id}")
	public String modificarEntrenador (@PathVariable("id") Long id, Model model) {
		Entrenador entrenador = entrenadorrepositorio.findById(id).get();
		model.addAttribute("entrenador", entrenador);
		List<Entrenador> listaEntrenador = entrenadorrepositorio.findAll();
		model.addAttribute("listaEntrenador", listaEntrenador);

		return "formentrenador";
		
	}
	
	@GetMapping("/eliminarentrenador/{id}")
	public String eliminarentrenador(@PathVariable("id") Long id, Model model) {
		entrenadorrepositorio.deleteById(id);
		return "redirect:/verentrenador";
	}
	
	
}
