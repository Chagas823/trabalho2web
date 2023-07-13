package com.trabalhoweb2.demo.rest;

import java.security.Principal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.trabalhoweb2.demo.model.Usuario;
import com.trabalhoweb2.demo.repository.UsuarioRepository;

@RestController
@RequestMapping("/api/v1/user")
public class UsuarioRestController {
    @Autowired
    UsuarioRepository usuarioRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("me")
    Principal getMe(Principal me) {
        return me;
    }

    @GetMapping
    Iterable<Usuario> getAllUser() {
        return usuarioRepository.findAll();
    }

    @PostMapping("/login")
    Usuario login(@RequestBody Usuario user) {
        System.out.println("ola");
        Optional<Usuario> usuario = usuarioRepository.findByEmail(user.getUsername());
        System.out.println(passwordEncoder.encode(user.getPassword()));
        System.out.println(passwordEncoder.encode(usuario.get().getPassword()));
        if (passwordEncoder.encode(user.getPassword()).equals(usuario.get().getPassword())) {
            System.out.println("passou");
            return usuario.get();
        }
        return null;

    }

    @PostMapping("cadastrar")
    Usuario addUsuario(@RequestBody Usuario usuario) {
        System.out.println("teste");
        usuario.setSenha(passwordEncoder.encode(usuario.getPassword()));

        return usuarioRepository.save(usuario);
    }

    @GetMapping("/{id}")
    Optional<Usuario> getUsuarioById(@PathVariable("id") int id) {
        return usuarioRepository.findById(id);
    }
}
