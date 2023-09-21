package br.com.cpti.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.cpti.backend.entity.Palestra;

public interface PalestraRepository extends JpaRepository<Palestra, Long> {

}
