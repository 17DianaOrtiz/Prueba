package com.club.app.controlador;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.club.app.entidades.Asociacion;
import com.club.app.repositorio.AsociacionRepositorio;


@Controller

public class AsociacionWeb {

	
	@Autowired
	private  AsociacionRepositorio asociacionrepositorio;
	
	@GetMapping({"/verasociacion"})
	public String listarAsociacion(Model model) {
		List<Asociacion> listaAsociacion = asociacionrepositorio.findAll();
		model.addAttribute("listaAsociacion", listaAsociacion);
		return "verasociacion";
	}
	
	@GetMapping("/formasociacion")
	public String mostrarFormulario (Model model) {
		model.addAttribute("asociacion", new Asociacion());
	
	    
		List<Asociacion> listaAsociacion = asociacionrepositorio.findAll();
		model.addAttribute("listaAsociacion", listaAsociacion);

		return "formasociacion";
	}
	
	@PostMapping("/guardarasociacion")
	public String guardarAsociacion (Asociacion asociacion) {
		asociacionrepositorio.save(asociacion);
		return "redirect:/verasociacion";
	}
	
	@GetMapping("/editarasociacion/{id}")
	public String modificarAsociacion (@PathVariable("id") Long id, Model model) {
		Asociacion asociacion = asociacionrepositorio.findById(id).get();
		model.addAttribute("asociacion",asociacion);
		List<Asociacion> listaAsociacion = asociacionrepositorio.findAll();
		model.addAttribute("listaAsociacion", listaAsociacion);

		return "formasociacion";
		
	}
	
	@GetMapping("/eliminarasociacion/{id}")
	public String eliminarasociacion(@PathVariable("id") Long id, Model model) {
		asociacionrepositorio.deleteById(id);
		return "redirect:/verasociacion";
	}
	
}
