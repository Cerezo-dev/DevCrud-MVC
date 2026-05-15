package com.upeu.crudspring.repository;

import com.upeu.crudspring.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario,Long> {
    Usuario findByUsername(String username);
}
