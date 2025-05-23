package com.club.app.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.club.app.entidades.Club;

public interface ClubRepositorio extends JpaRepository <Club, Long> {

}
