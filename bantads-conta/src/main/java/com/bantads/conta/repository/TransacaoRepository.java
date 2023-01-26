package com.bantads.conta.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bantads.conta.model.Transacao;

public interface TransacaoRepository extends JpaRepository<Transacao, Long> {
	//List<Transacao> findByDataHoraBetween(Date startDate, Date endDate);
	
	//@Query(value = "select m.id, m.valor, m.datahora, m.tipomovimentacao, m.origem, m.destino, m.saga from movimentacao m where (origem = :idExternoCliente or destino = :idExternoCliente) and datahora >= :dataInicio and datahora <= :dataFim ", nativeQuery = true)
	//List<MovimentacaoR> obtemMovimentacoesCliente(@Param("idExternoCliente") UUID idExternoCliente, @Param("dataInicio") Date dataInicio, @Param("dataFim") Date dataFim);
}
