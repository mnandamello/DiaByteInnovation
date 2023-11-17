package br.com.DiaByteInnovation.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class HistoricoGlicemia {

    private Long id;

    private Integer glicemia; //como podemos pegar o resultado a conta da refeição se for feita no front, ou ela tem que ser feita no java.




    private Paciente paciente;

    private Date diaMedicao;

    private Date horarioMedicao;

    private Boolean jejum;


    /* METODOS
    *
    *
    *
    *
    * */

    //ONDE PEGAR ESSAS INFORMAÇÕES? FORMULARIO?

}
