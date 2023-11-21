package br.com.DiaByteInnovation.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Alimento {

    private Long id_alimento;

    private String nome;

    private float quantCarboGrama;

    /* METODOS
     * FindAll (GET)
     * */
}
