package br.com.DiaByteInnovation.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Refeicao {

    private Long idRefeicao;

    private float totalCarboidrato;

    private Integer quantidadeInsulina;

    private Paciente paciente;

}
