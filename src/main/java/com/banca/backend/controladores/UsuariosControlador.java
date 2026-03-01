package com.banca.backend.controladores;

import com.banca.backend.dto.UsuarioDTO;
import com.banca.backend.dto.UsuariosPaginadosDTO;
import com.banca.backend.servicios.UsuarioServicio;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Usuarios")
public class UsuariosControlador {

    private final UsuarioServicio usuarioServicio;

    public UsuariosControlador(UsuarioServicio usuarioServicio) {
        this.usuarioServicio = usuarioServicio;
    }


    @PostMapping("/CrearUsuario")
    public void crearUsuario(@RequestBody UsuarioDTO usuarioDTO) {

    }

    @GetMapping("/CreaUsuario")
    public UsuarioDTO crearUsuario() {
        return usuarioServicio.crearUsuario();
    }

    @GetMapping("/ObtenerUsuarios")
    public List<UsuarioDTO> test() {
        return usuarioServicio.obtenerUsuarios();
    }

    @GetMapping("/ObtenerUsuariosPaginados")
    public UsuariosPaginadosDTO test(@RequestParam("cantidadPorPagina") Integer cantidadPorPagina, @RequestParam("numeroPagina") Integer numeroPagina) {
        return usuarioServicio.obtenerUsuariosPaginados(cantidadPorPagina, numeroPagina);
    }



}
