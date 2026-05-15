package com.upeu.crudspring.services;

import com.upeu.crudspring.models.Cliente;
import com.upeu.crudspring.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClienteServiceImpl implements ClienteService {

    private final ClienteRepository clienteRepository;

    @Override
    public List<Cliente> listarTodo() {
        return (List<Cliente>) clienteRepository.findAll();
    }

    @Override
    public Cliente crearCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    @Override
    public Cliente obtenerCliente(Long id) {
        return clienteRepository.findById(id).orElse(null);
    }

    @Override
    public Cliente actualizarCliente(Cliente cliente) {
        Cliente clienteDB = obtenerCliente(cliente.getId());
        if (clienteDB == null) {
            return null;
        }
        clienteDB.setNombre(cliente.getNombre());
        clienteDB.setApellido(cliente.getApellido());
        clienteDB.setEmail(cliente.getEmail());
        clienteDB.setTelefono(cliente.getTelefono());
        clienteDB.setCiudad(cliente.getCiudad());
        return clienteRepository.save(clienteDB);
    }

    @Override
    public Cliente eliminarClienteSoft(Cliente cliente) {
        Cliente clienteDB = obtenerCliente(cliente.getId());
        if (clienteDB == null)
            return null;
        clienteDB.setActivo(false);
        return clienteRepository.save(clienteDB);
    }

    @Override
    public void eliminarClienteHard(Cliente cliente) {
        clienteRepository.delete(cliente);
    }
}

