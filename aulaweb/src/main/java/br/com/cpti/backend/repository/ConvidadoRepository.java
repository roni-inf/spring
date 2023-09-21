package br.com.cpti.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.cpti.backend.entity.Convidado;
import java.util.List;
import br.com.cpti.backend.entity.Palestra;

public interface ConvidadoRepository extends JpaRepository<Convidado, Long> {
	List<Convidado> findByPalestra(Palestra palestra);
}
