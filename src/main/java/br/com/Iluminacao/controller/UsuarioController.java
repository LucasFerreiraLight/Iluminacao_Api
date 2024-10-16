package br.com.Iluminacao.controller;

import br.com.Iluminacao.dto.UsuarioCadastroDto;
import br.com.Iluminacao.dto.UsuarioExibicaoDto;
import br.com.Iluminacao.model.Usuario;
import br.com.Iluminacao.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    //save
    @PostMapping("usuario/save")
    @ResponseStatus(HttpStatus.CREATED)
    public UsuarioExibicaoDto gravar(@RequestBody UsuarioCadastroDto usuarioCadastroDto){
        return usuarioService.salvar(usuarioCadastroDto);
    }

    //buscar todos os Usuarios
    @GetMapping("usuario/listar")
    @ResponseStatus(HttpStatus.OK)
    public List<UsuarioExibicaoDto> listarTodosOsUsuarios(){
        return usuarioService.ListarTodosOsUsuarios();
    }

    //Delete
    @DeleteMapping("usuario/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT) //Por padrão já é esse, mas você pode deixar explicito igual está ai
    public void excluir(@PathVariable Long id){
        usuarioService.excluir(id);

    }

    // Update
    @PutMapping("usuario/update")
    @ResponseStatus(HttpStatus.OK)
    public Usuario atualizar(@RequestBody Usuario usuario){

        return usuarioService.atualizar(usuario);
    }

    @GetMapping("usuario/{nome}")
    @ResponseStatus(HttpStatus.OK)
    public UsuarioExibicaoDto buscarPeloNome(@PathVariable String nome){

        return usuarioService.buscarPeloNome(nome);
    }

}
