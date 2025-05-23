package com.club.app.repositorio;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.club.app.entidades.Club;
import com.club.app.entidades.Jugador;

public interface JugadorRepositorio extends JpaRepository <Jugador, Long>{

	List<Jugador> findByClubIsNull();
	List<Jugador> findByClub(Club club);
	
}
