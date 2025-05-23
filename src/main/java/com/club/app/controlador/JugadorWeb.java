package com.club.app.controlador;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


import com.club.app.entidades.Jugador;
import com.club.app.repositorio.JugadorRepositorio;


@Controller


public class JugadorWeb {

	
	@Autowired
	private  JugadorRepositorio jugadorrepositorio;
	
	@GetMapping({"/verjugador"})
	public String listarJugador(Model model) {
		List<Jugador> listaJugador = jugadorrepositorio.findAll();
		model.addAttribute("listaJugador", listaJugador);
		return "verjugador";
	}
	
	@GetMapping("/formjugador")
	public String mostrarFormulario (Model model) {
		model.addAttribute("jugador", new Jugador());
	
	    
		List<Jugador> listaJugador = jugadorrepositorio.findAll();
		model.addAttribute("listaJugador", listaJugador);

		return "formjugador";
	}
	
	@PostMapping("/guardarjugador")
	public String guardarJugador (Jugador jugador) {
		jugadorrepositorio.save(jugador);
		return "redirect:/verjugador";
	}
	
	@GetMapping("/editarjugador/{id}")
	public String modificarJugador (@PathVariable("id") Long id, Model model) {
		Jugador jugador = jugadorrepositorio.findById(id).get();
		model.addAttribute("jugador",jugador);
		List<Jugador> listaJugador = jugadorrepositorio.findAll();
		model.addAttribute("listaJugador", listaJugador);

		return "formjugador";
		
	}
	
	@GetMapping("/eliminarjugador/{id}")
	public String eliminarjugador(@PathVariable("id") Long id, Model model) {
		jugadorrepositorio.deleteById(id);
		return "redirect:/verjugador";
	}
	
	
}
