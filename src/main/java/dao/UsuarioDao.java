package dao;

import java.util.List;
import models.Usuario;

public interface UsuarioDao {

   List<Usuario> getUsuarios();

   void eliminar(Long id);

   void registrar(Usuario usuario);

   Usuario obtenerUsuarioPorCredenciales(Usuario usuario);
}