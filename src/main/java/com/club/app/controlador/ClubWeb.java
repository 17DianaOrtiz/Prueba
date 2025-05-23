package com.club.app.controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.club.app.entidades.Asociacion;
import com.club.app.entidades.Club;
import com.club.app.entidades.Competicion;
import com.club.app.entidades.Entrenador;
import com.club.app.entidades.Jugador;
import com.club.app.repositorio.AsociacionRepositorio;
import com.club.app.repositorio.ClubRepositorio;
import com.club.app.repositorio.CompeticionRepositorio;
import com.club.app.repositorio.EntrenadorRepositorio;
import com.club.app.repositorio.JugadorRepositorio;

import jakarta.transaction.Transactional;

import java.util.List;

@Controller

public class ClubWeb {
	
	@Autowired
	private EntrenadorRepositorio entrenadorRepositorio;

	@Autowired
	private AsociacionRepositorio asociacionRepositorio;

	@Autowired
	private CompeticionRepositorio competicionRepositorio;
	
	@Autowired
	private JugadorRepositorio jugadorRepositorio;

	
	@Autowired
	private  ClubRepositorio clubrepositorio;
	
	@GetMapping({"/listaclub","/mostrarclub","/listarclub"})
	public String listarAgenda(Model model) {
		List<Club> listaClub = clubrepositorio.findAll();
		model.addAttribute("listaclub", listaClub);
		return "listaclub";
	}
	
	@GetMapping("/formclub")
	public String mostrarFormulario (Model model) {
		model.addAttribute("club", new Club());
		
		
		List<Entrenador> listaEntrenadores = entrenadorRepositorio.findAll();
	    List<Asociacion> listaAsociaciones = asociacionRepositorio.findAll();
	    List<Competicion> listaCompeticiones = competicionRepositorio.findAll();
		List<Jugador> listaJugadores = jugadorRepositorio.findAll();

	    model.addAttribute("entrenadores", listaEntrenadores);
	    model.addAttribute("asociaciones", listaAsociaciones);
	    model.addAttribute("competiciones", listaCompeticiones);
	    model.addAttribute("listaJugadores", listaJugadores);
	    
		List<Club> listaClub = clubrepositorio.findAll();
		model.addAttribute("listaClub", listaClub);

		return "formclub";
	}
	
	@GetMapping("/ingresarclub")
	public String ingresarFormulario (Model model) {
		model.addAttribute("club", new Club());
		
		 model.addAttribute("entrenadores", entrenadorRepositorio.findByClubIsNull());
		    model.addAttribute("listaJugadores", jugadorRepositorio.findByClubIsNull());
		
	    List<Asociacion> listaAsociaciones = asociacionRepositorio.findAll();
	    List<Competicion> listaCompeticiones = competicionRepositorio.findAll();

	    model.addAttribute("asociaciones", listaAsociaciones);
	    model.addAttribute("competiciones", listaCompeticiones);
	    
		List<Club> listaClub = clubrepositorio.findAll();
		model.addAttribute("listaClub", listaClub);

		return "ingresarclub";
	}
	
	@PostMapping("/guardarclub")
	public String guardarClub(Club club) {
		
	    Club clubGuardado = clubrepositorio.save(club);

	    if (club.getEntrenador() != null) {
	        Entrenador entrenador = entrenadorRepositorio.findById(club.getEntrenador().getId()).orElse(null);
	        if (entrenador != null) {
	            entrenador.setClub(clubGuardado);
	            entrenadorRepositorio.save(entrenador);
	        }
	    }

	    
	    if (club.getJugadores() != null) {
	        for (Jugador jugador : club.getJugadores()) {
	            Jugador j = jugadorRepositorio.findById(jugador.getId()).orElse(null);
	            if (j != null) {
	                j.setClub(clubGuardado);
	                jugadorRepositorio.save(j);
	            }
	        }
	    }

	    return "redirect:/listaclub";
	}
	
	
	@GetMapping("/editarclub/{id}")
	public String modificarClub (@PathVariable("id") Long id, Model model) {
		Club club = clubrepositorio.findById(id).get();
		model.addAttribute("club",club);
		
		
	    List<Entrenador> entrenadoresAsignados = entrenadorRepositorio.findByClub(club);
	    model.addAttribute("entrenadores", entrenadoresAsignados);
	    
	    List<Entrenador> entrenadoresLibres = entrenadorRepositorio.findByClubIsNull();
	    model.addAttribute("entrenadoresLibres", entrenadoresLibres);


	    List<Competicion> competiciones = competicionRepositorio.findAll();
	    model.addAttribute("competiciones", competiciones);
	    
	    List<Asociacion> asociaciones = asociacionRepositorio.findAll();
	    model.addAttribute("asociaciones", asociaciones);
	    
	    List<Jugador> jugadoresLibres = jugadorRepositorio.findByClubIsNull();
	    model.addAttribute("jugadoresLibres", jugadoresLibres);
	    
	    List<Jugador> jugadoresAsignados = jugadorRepositorio.findByClub(club);
	    model.addAttribute("jugadoresAsignados", jugadoresAsignados);
	    
	   
	    
		List<Club> listaClub = clubrepositorio.findAll();
		model.addAttribute("listaClub", listaClub);

		return "formclub";
		
	}
	
	@GetMapping("/eliminarclub/{id}")
	@Transactional
	public String eliminarclub(@PathVariable("id") Long id, Model model) {
	   
	    Club club = clubrepositorio.findById(id).orElse(null);
	    if (club == null) {
	      
	        return "redirect:/listaclub";
	    }

	
	    List<Entrenador> entrenadores = entrenadorRepositorio.findByClub(club);

	  
	    for (Entrenador e : entrenadores) {
	        e.setClub(null);
	        entrenadorRepositorio.save(e);
	    }

	   
	    clubrepositorio.delete(club);

	    return "redirect:/listaclub";
	}
}
