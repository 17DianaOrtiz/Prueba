package com.club.app.repositorio;


import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import com.club.app.entidades.Club;
import com.club.app.entidades.Entrenador;

public interface EntrenadorRepositorio extends JpaRepository <Entrenador, Long>{
	
	List<Entrenador> findByClubIsNull();
	List<Entrenador> findByClub(Club club);
	
}
