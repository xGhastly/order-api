package br.com.joaoaugusto.order_api.entrega.listener;

import br.com.joaoaugusto.order_api.entrega.dto.PedidoEvent;
import br.com.joaoaugusto.order_api.entrega.model.Entrega;
import br.com.joaoaugusto.order_api.entrega.repository.EntregaRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class PedidoListenerTest {

    private final EntregaRepository entregaRepository = mock(EntregaRepository.class);
    private final PedidoListener pedidoListener = new PedidoListener(entregaRepository);

    @Test
    @DisplayName("Deve processar evento de pedido criado e salvar entrega")
    void deveProcessarEventoDePedidoCriadoESalvarEntrega() {
        final PedidoEvent event = new PedidoEvent(1L, "Rua B, 456");
        when(entregaRepository.save(any(Entrega.class))).thenReturn(new Entrega());

        pedidoListener.processarPedidoCriado(event);

        verify(entregaRepository, times(1)).save(argThat(entrega -> 
            entrega.getPedidoId().equals(1L) && entrega.getEnderecoEntrega().equals("Rua B, 456")
        ));
    }
}
