package br.com.joaoaugusto.order_api.consumidor.repository;

import br.com.joaoaugusto.order_api.consumidor.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {

}
