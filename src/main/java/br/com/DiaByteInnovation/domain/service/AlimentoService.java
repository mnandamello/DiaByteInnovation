package br.com.DiaByteInnovation.domain.service;

import br.com.DiaByteInnovation.domain.entity.Alimento;
import br.com.DiaByteInnovation.domain.entity.Usuario;
import br.com.DiaByteInnovation.domain.repository.AlimentoRepository;


import java.util.List;

public class AlimentoService implements Service<Alimento, Long>{

    private AlimentoRepository repo = AlimentoRepository.build();

    @Override
    public List<Alimento> findAll() {
        return repo.findAll();
    }

    @Override
    public Alimento findById(Long id) {
        return repo.findById( id );
    }

    @Override
    public Alimento persiste(Alimento alimento) {
        return repo.persiste( alimento );
    }
}
