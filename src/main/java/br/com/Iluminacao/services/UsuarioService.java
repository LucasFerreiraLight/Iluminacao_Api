package br.com.Iluminacao.services;

import br.com.Iluminacao.dto.UsuarioCadastroDto;
import br.com.Iluminacao.dto.UsuarioExibicaoDto;
import br.com.Iluminacao.model.Usuario;
import br.com.Iluminacao.repository.UsuarioRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;


    //------------------------------------------------------------------
    // Salvar Usuario
    public UsuarioExibicaoDto salvar(UsuarioCadastroDto usuarioDTO) {

        String senhaCriptografada = new BCryptPasswordEncoder().encode(usuarioDTO.senha());

        // BeanUtils
        Usuario usuario = new Usuario();
        BeanUtils.copyProperties(usuarioDTO, usuario);
        usuario.setSenha(senhaCriptografada);

        Usuario usuarioSalvo = usuarioRepository.save(usuario);
        return new UsuarioExibicaoDto(usuarioRepository.save(usuario));
    }

    public List<UsuarioExibicaoDto> ListarTodosOsUsuarios() {
        return usuarioRepository
                .findAll()
                .stream()
                .map(UsuarioExibicaoDto::new)
                .toList();
    }


    //------------------------------------------------------------------
    // Deletar Usuario
    public void excluir(Long id) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(id);

        if (usuarioOptional.isPresent()) {
            usuarioRepository.delete(usuarioOptional.get());
        } else {
            throw new RuntimeException("Usuario não encontrado!");
        }
    }


    //------------------------------------------------------------------
    //Atualizar
    public Usuario atualizar(Usuario usuario) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(usuario.getUsuarioId());

        if (usuarioOptional.isPresent()) {
            return usuarioRepository.save(usuario);
        } else {
            throw new RuntimeException("Usuario não encontrado!");
        }
    }

    public UsuarioExibicaoDto buscarPeloNome(String nome) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findByNome(nome);

        if (usuarioOptional.isPresent()) {
            return new UsuarioExibicaoDto(usuarioOptional.get());
        } else {
            throw new RuntimeException("Nome de Usuario não encontrado!");
        }
    }

}
