package br.com.DiaByteInnovation.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Paciente {

    private Long id;

    private String nomeCompleto;

    private LocalDate dtNascimento;

    private float relacaoInsulina;

    private Integer valorMaxGlicemia;

    private Integer valorMinGlicemia;


}
