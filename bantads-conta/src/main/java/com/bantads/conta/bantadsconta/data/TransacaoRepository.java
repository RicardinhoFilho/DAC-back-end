package com.bantads.conta.bantadsconta.data;

import com.bantads.conta.bantadsconta.model.Transacao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransacaoRepository extends JpaRepository<Transacao, Long> {
	//List<Transacao> findByDataHoraBetween(Date startDate, Date endDate);
	
	//@Query(value = "select m.id, m.valor, m.datahora, m.tipomovimentacao, m.origem, m.destino, m.saga from movimentacao m where (origem = :idExternoCliente or destino = :idExternoCliente) and datahora >= :dataInicio and datahora <= :dataFim ", nativeQuery = true)
	//List<MovimentacaoR> obtemMovimentacoesCliente(@Param("idExternoCliente") UUID idExternoCliente, @Param("dataInicio") Date dataInicio, @Param("dataFim") Date dataFim);
}
