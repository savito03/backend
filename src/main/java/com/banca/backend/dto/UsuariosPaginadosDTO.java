package com.banca.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UsuariosPaginadosDTO extends ResultadoDTO{

    private List<UsuarioDTO> listaUsuarios;

    private Integer numeroPagina;


}
