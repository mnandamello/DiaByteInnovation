package br.com.DiaByteInnovation.domain.service;

import br.com.DiaByteInnovation.domain.entity.HistoricoGlicemia;
import br.com.DiaByteInnovation.domain.repository.HistoricoGlicemiaRepository;

import java.util.List;

public class HistoricoGlicemiaService implements Service<HistoricoGlicemia, Long>{

        private HistoricoGlicemiaRepository repo = HistoricoGlicemiaRepository.build();

        @Override
        public List<HistoricoGlicemia> findAll() {
            return repo.findAll();
        }

        @Override
        public HistoricoGlicemia findById(Long id) {
            return repo.findById( id );
        }

        @Override
        public HistoricoGlicemia persiste(HistoricoGlicemia historicoGlicemia) {
            return repo.persiste( historicoGlicemia );
        }

}
