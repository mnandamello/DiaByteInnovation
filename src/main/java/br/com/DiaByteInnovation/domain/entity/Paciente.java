package br.com.DiaByteInnovation.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;


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

    private Usuario usuario; //fazer findById


    /* METODOS
    * persiste (POST)
    * findById (GET)
    * */

}
