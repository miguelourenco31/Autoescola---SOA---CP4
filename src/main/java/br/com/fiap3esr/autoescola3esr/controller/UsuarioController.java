package br.com.fiap3esr.autoescola3esr.controller;

import br.com.fiap3esr.autoescola3esr.domain.usuario.DadosCadastroUsuario;
import br.com.fiap3esr.autoescola3esr.domain.usuario.DadosDetalhamentoUsuario;
import br.com.fiap3esr.autoescola3esr.domain.usuario.Usuario;
import br.com.fiap3esr.autoescola3esr.domain.usuario.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import br.com.fiap3esr.autoescola3esr.domain.usuario.DadosAtualizacaoUsuario;
import br.com.fiap3esr.autoescola3esr.domain.usuario.DadosAlteracaoSenha;
import org.springframework.security.core.Authentication;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<DadosDetalhamentoUsuario> cadastrar(
            @RequestBody @Valid DadosCadastroUsuario dados) {

        String senhaCriptografada = passwordEncoder.encode(dados.senha());

        Usuario usuario = new Usuario(
                dados.login(),
                senhaCriptografada,
                dados.perfil()
        );

        repository.save(usuario);

        return ResponseEntity.ok(new DadosDetalhamentoUsuario(usuario));
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<DadosDetalhamentoUsuario>> listar() {

        List<DadosDetalhamentoUsuario> usuarios = repository.findAll()
                .stream()
                .map(DadosDetalhamentoUsuario::new)
                .toList();

        return ResponseEntity.ok(usuarios);
    }

    @PutMapping
@PreAuthorize("hasRole('ADMIN')")
public ResponseEntity<DadosDetalhamentoUsuario> atualizar(
        @RequestBody @Valid DadosAtualizacaoUsuario dados) {

    Usuario usuario = repository.getReferenceById(dados.id());

    usuario.atualizarInformacoes(dados);

    repository.save(usuario);

    return ResponseEntity.ok(new DadosDetalhamentoUsuario(usuario));
}

@DeleteMapping("/{id}")
@PreAuthorize("hasRole('ADMIN')")
public ResponseEntity<Void> excluir(@PathVariable Long id) {

    repository.deleteById(id);

    return ResponseEntity.noContent().build();
}

@PutMapping("/senha")
public ResponseEntity<Void> alterarSenha(
        @RequestBody @Valid DadosAlteracaoSenha dados,
        Authentication authentication) {

    Usuario usuario = (Usuario) authentication.getPrincipal();

    if (!passwordEncoder.matches(dados.senhaAtual(), usuario.getPassword())) {
        return ResponseEntity.badRequest().build();
    }

    String novaSenhaCriptografada = passwordEncoder.encode(dados.novaSenha());

    usuario.alterarSenha(novaSenhaCriptografada);

    repository.save(usuario);

    return ResponseEntity.noContent().build();
}

}