package com.trabalhoweb2.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.trabalhoweb2.demo.model.Mensagem;

public interface MensagemRepository extends CrudRepository<Mensagem, Integer> {
    @Query(value = "SELECT * FROM mensagem where (emissor_id = :id1 and receptor_id = :id2 ) or (emissor_id = :id2 and receptor_id = :id1) order by data desc", nativeQuery = true)
    List<Mensagem> getMensagens(int id1, int id2);
}
