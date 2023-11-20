package br.com.DiaByteInnovation.domain.service;

import br.com.DiaByteInnovation.domain.entity.Usuario;
import br.com.DiaByteInnovation.domain.repository.UsuarioRepository;

import java.util.List;

public class UsuarioService implements Service<Usuario, Long>{

    private UsuarioRepository repo = UsuarioRepository.build();

    @Override
    public List<Usuario> findAll() {
        return repo.findAll();
    }

    @Override
    public Usuario findById(Long id) {
        return repo.findById( id );
    }

    @Override
    public Usuario persiste(Usuario usuario) {
        return repo.persiste( usuario );
    }

}
