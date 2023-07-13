package com.trabalhoweb2.demo.rest;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.trabalhoweb2.demo.model.Mensagem;
import com.trabalhoweb2.demo.repository.MensagemRepository;

@RestController
@RequestMapping("/api/v1/mensagens")
public class MensagemRestController {
    @Autowired
    MensagemRepository mensagemRepository;

    @GetMapping
    Iterable<Mensagem> getMensagens() {
        return mensagemRepository.findAll();
    }

    @PostMapping
    Mensagem addMensagem(@RequestBody Mensagem mensagem) {
        return mensagemRepository.save(mensagem);
    }

    @GetMapping("/{id}/{id2}")
    List<Mensagem> getMensagensEmissorEReceptor(@PathVariable("id") int id, @PathVariable("id2") int id2) {
        return mensagemRepository.getMensagens(id, id2);
    }
}
