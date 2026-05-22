package br.com.joaoaugusto.order_api.pedido.repository;

import br.com.joaoaugusto.order_api.pedido.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {

}
