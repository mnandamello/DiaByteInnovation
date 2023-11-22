package br.com.DiaByteInnovation.domain.service;


import br.com.DiaByteInnovation.domain.entity.Paciente;
import br.com.DiaByteInnovation.domain.entity.Refeicao;
import br.com.DiaByteInnovation.domain.repository.RefeicaoRepository;

import java.util.List;
import java.util.Objects;

public class RefeicaoService implements Service<Refeicao, Long>{

    PacienteService pacienteService = new PacienteService();
    private RefeicaoRepository repo = RefeicaoRepository.build();

    @Override
    public List<Refeicao> findAll() {
        return repo.findAll();
    }

    @Override
    public Refeicao findById(Long id) {
        return repo.findById( id );
    }

    @Override
    public Refeicao persiste(Refeicao refeicao) {
        Paciente paciente = pacienteService.findById(refeicao.getPaciente().getId());
        if (Objects.isNull(paciente)) return null;
        return repo.persiste( refeicao );
    }

     //ver se esta certo
    public List<Refeicao> findByPaciente(Long id) {
        return repo.findByPaciente(id);
    }

}
