package com.bantads.conta.bantadsconta.data.CUD;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.bantads.conta.bantadsconta.model.CUD.ContaCUD;

public interface ContaCUDRepository extends JpaRepository<ContaCUD, Long> {
    //QUERY PARA BUSCAR O GERENTE COM MENOR NUMERO DE CLIENTES PARA ESCOLHA DO GERENTE
     @Query(value="select id_gerente from conta  group by id_gerente order by count(id_gerente) asc limit 1",nativeQuery=true)
     public List<Long> idGerenteMenosClientes();

    
}
