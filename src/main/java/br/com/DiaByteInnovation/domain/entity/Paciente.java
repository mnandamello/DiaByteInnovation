package br.com.DiaByteInnovation.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Paciente {

    private Long id;

    private String nome;

    private String sobrenome;

    private Date dtNascimento;

    private Integer relacaoInsulina;

    private Integer maxGlicemia;

    private Integer minGlicemia;

    private Integer valorCorrecao;

    private Integer quantiDose;

    /* METODOS
    * CREATE (POST)
    * FindByRelação (GET)
    * FindByMax (GET)
    * FindByMin (GET)
    * findByValorCorrecao (GET)
    * finByQauntiDose
    * */

}
