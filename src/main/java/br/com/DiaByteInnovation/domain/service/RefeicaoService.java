package br.com.DiaByteInnovation.domain.service;


import br.com.DiaByteInnovation.domain.entity.Refeicao;
import br.com.DiaByteInnovation.domain.repository.RefeicaoRepository;

import java.util.List;
public class RefeicaoService implements Service<Refeicao, Long>{

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
        return repo.persiste( refeicao );
    }

     /* //ver se esta certo
    public List<Refeicao> findRefeicoesByPaciente(Long idPaciente) {
        return repo.findRefeicoesByPaciente( idPaciente );
    }*/

}
