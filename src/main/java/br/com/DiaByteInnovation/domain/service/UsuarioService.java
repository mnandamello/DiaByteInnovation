package br.com.DiaByteInnovation.domain.service;

import br.com.DiaByteInnovation.domain.entity.Paciente;
import br.com.DiaByteInnovation.domain.entity.Usuario;
import br.com.DiaByteInnovation.domain.repository.UsuarioRepository;

import java.util.List;
import java.util.Objects;

public class UsuarioService implements Service<Usuario, Long>{

    PacienteService pacienteService = new PacienteService();
    private UsuarioRepository repo = UsuarioRepository.build();

    @Override
    public List<Usuario> findAll() {
        return repo.findAll();
    }

    @Override
    public Usuario findById(Long id) {
        return repo.findById( id );
    }

    public Usuario login(Usuario usuario) {
        Usuario user = repo.findByEmail( usuario.getEmail() );
        if (Objects.isNull(usuario)) return null;
        boolean autenticado = user.getSenha().equals(usuario.getSenha());
        if (autenticado) return user;
        return null;
    }

    @Override
    public Usuario persiste(Usuario usuario) {
        Paciente paciente = pacienteService.persiste(usuario.getPaciente());
        if (Objects.isNull(paciente)) return null;
        return repo.persiste( usuario );
    }

}
