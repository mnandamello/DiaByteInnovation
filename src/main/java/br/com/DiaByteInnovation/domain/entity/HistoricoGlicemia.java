package br.com.DiaByteInnovation.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class HistoricoGlicemia {

    private Long id_glicemia;

    private Integer valorDestro;

    private Paciente paciente;

}
