package br.com.joaoaugusto.order_api.entrega.repository;

import br.com.joaoaugusto.order_api.entrega.model.Entrega;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EntregaRepository extends JpaRepository<Entrega, Long> {
}
