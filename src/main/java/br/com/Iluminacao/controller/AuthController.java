package br.com.Iluminacao.controller;

import br.com.Iluminacao.config.security.TokenService;
import br.com.Iluminacao.dto.TokenDTO;
import br.com.Iluminacao.dto.UsuarioCadastroDto;
import br.com.Iluminacao.dto.UsuarioExibicaoDto;
import br.com.Iluminacao.model.LoginDTO;
import br.com.Iluminacao.model.Usuario;
import br.com.Iluminacao.services.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UsuarioService service;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid LoginDTO loginDTO){
        UsernamePasswordAuthenticationToken usernamePassword =
                new UsernamePasswordAuthenticationToken(
                        loginDTO.email(),
                        loginDTO.senha()
                );
        Authentication auth = authenticationManager.authenticate(usernamePassword);

        // Fez o Casting, para transformar o Object do Principal em um Usuario.
        String token = tokenService.gerarToken((Usuario) auth.getPrincipal());

        return ResponseEntity.ok(new TokenDTO(token));
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public UsuarioExibicaoDto registrar(@RequestBody @Valid UsuarioCadastroDto usuarioCadastroDto){
        UsuarioExibicaoDto usuarioSalvo = null;
        usuarioSalvo = service.salvar(usuarioCadastroDto);
        return usuarioSalvo;
    }

}
