package com.algawork.osworks.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.algawork.osworks.domain.model.Comentario;

@Repository
public interface ComentarioRepostiroty extends JpaRepository<Comentario, Long>{

}
