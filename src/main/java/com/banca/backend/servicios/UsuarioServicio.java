package com.banca.backend.servicios;

import com.banca.backend.dto.UsuarioDTO;
import com.banca.backend.dto.UsuariosPaginadosDTO;
import com.banca.backend.entidades.Usuario;
import com.banca.backend.mapper.UsuarioMapper;
import com.banca.backend.repositorios.UsuarioRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
public class UsuarioServicio {

    @PersistenceContext
    private EntityManager entityManager;

    private final UsuarioRepository usuarioRepository;

    public UsuarioServicio(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public UsuarioDTO crearUsuario() {
        UsuarioDTO usuarioDTO = new
                UsuarioDTO(1L, "Juan", "Carlos", "Perez", "Gomez", "123456789", 1L);


        Usuario usuario = UsuarioMapper.toEntity(usuarioDTO);
        usuario.setId(null); // Aseguramos que el ID sea null para que se genere automáticamente
        usuarioRepository.save(usuario);
        return UsuarioMapper.toDTO(usuario);
    }

    public List<UsuarioDTO> obtenerUsuarios() {
        List<Usuario> usuarios = usuarioRepository.findAll();
        return usuarios.stream()
                .map(UsuarioMapper::toDTO)
                .toList();
    }

    public UsuariosPaginadosDTO obtenerUsuariosPaginados(Integer cantidadPorPagina, Integer numeroPagina) {
        UsuariosPaginadosDTO usuariosPaginadosDTO = new UsuariosPaginadosDTO();
        try{
            List <Usuario> listadoUsuarios = entityManager.createQuery("SELECT u FROM Usuario u", Usuario.class)
                    .setFirstResult(cantidadPorPagina*numeroPagina) // Aquí podrías calcular el índice de inicio según tu lógica de paginación
                    .setMaxResults(cantidadPorPagina) // Aquí podrías establecer la cantidad de resultados por página según tu lógica de paginación
                    .getResultList();

            List<UsuarioDTO> listaUsuariosDTO = listadoUsuarios.stream().map(UsuarioMapper::toDTO).toList();

            if (listaUsuariosDTO.isEmpty()) {
                usuariosPaginadosDTO.setError("No hay usuarios disponibles");
            }else {
                usuariosPaginadosDTO.setMensaje("Usuarios obtenidos correctamente");
                usuariosPaginadosDTO.setListaUsuarios(listaUsuariosDTO);
                usuariosPaginadosDTO.setNumeroPagina(numeroPagina);
                usuariosPaginadosDTO.setExitoso(Boolean.TRUE);

            }
        }catch ( Exception e){
            usuariosPaginadosDTO.setError("Error al obtener los usuarios: " + e.getMessage());
            usuariosPaginadosDTO.setExitoso(Boolean.FALSE);
        }

        return usuariosPaginadosDTO;
    }
}
