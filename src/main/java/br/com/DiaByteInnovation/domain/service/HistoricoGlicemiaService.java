package br.com.DiaByteInnovation.domain.service;

import br.com.DiaByteInnovation.domain.entity.HistoricoGlicemia;
import br.com.DiaByteInnovation.domain.entity.Paciente;
import br.com.DiaByteInnovation.domain.repository.HistoricoGlicemiaRepository;

import java.util.List;
import java.util.Objects;

public class HistoricoGlicemiaService implements Service<HistoricoGlicemia, Long>{

        PacienteService pacienteService = new PacienteService();
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
            Paciente paciente = pacienteService.findById(historicoGlicemia.getPaciente().getId());
            if (Objects.isNull(paciente)) return null;
            return repo.persiste( historicoGlicemia );
        }

}
