package br.com.DiaByteInnovation.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class HistoricoGlicemia {

    private Long id;

    private Integer valorDestro; //ja vamos pegar no front o valor colocado pra fazer o grafico

    private Paciente paciente;

    /* METODOS
    *PERSISTE (POST)
    * */

}
