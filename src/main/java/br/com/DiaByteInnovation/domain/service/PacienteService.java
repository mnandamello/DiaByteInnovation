package br.com.DiaByteInnovation.domain.service;

import br.com.DiaByteInnovation.domain.entity.Paciente;
import br.com.DiaByteInnovation.domain.repository.PacienteRepository;


import java.util.List;

public class PacienteService implements Service<Paciente, Long>{

    private PacienteRepository repo = PacienteRepository.build();

    @Override
    public List<Paciente> findAll() {
        return repo.findAll();
    }

    @Override
    public Paciente findById(Long id) {
        return repo.findById( id );
    }

    @Override
    public Paciente persiste(Paciente paciente) {
        return repo.persiste( paciente );
    }
}
