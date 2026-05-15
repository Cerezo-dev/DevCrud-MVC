package com.upeu.crudspring.services;

import com.upeu.crudspring.models.Usuario;
import com.upeu.crudspring.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Primary
public class DetalleUsuarioService implements UserDetailsService {
    @Autowired
    private UsuarioRepository repo;

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario u = repo.findByUsername(username);
        if (u == null) throw new UsernameNotFoundException("Usuario no encontrado");
        System.out.println("Usuario: " + u.getUsername());
        System.out.println("Password en DB: " + u.getPassword());
        System.out.println("Longitud del password en DB: " + u.getPassword().length());
        System.out.println("BCrypt coincide: " + new BCryptPasswordEncoder().matches("123", u.getPassword()));
        System.out.println("--------------------------------------------------");
        // GENERAR UN HASH NUEVO EN VIVO PARA PROBAR
        String hashPrueba = new BCryptPasswordEncoder().encode("123");
        System.out.println("Hash generado ahora mismo para '123': " + hashPrueba);
        System.out.println("Hash que viene de tu DB:              " + u.getPassword());

        boolean coincide = new BCryptPasswordEncoder().matches("123", u.getPassword());
        System.out.println("¿Coinciden?: " + coincide);

        return User.withUsername(u.getUsername())
                .password(u.getPassword())
                .roles(u.getRol())
                .build();
    }
}

