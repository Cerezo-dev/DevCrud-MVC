package com.upeu.crudspring.services;

import com.upeu.crudspring.models.Cliente;

import java.util.List;

public interface ClienteService {
    public List<Cliente> listarTodo();
    public Cliente obtenerCliente(Long id);
    public Cliente crearCliente(Cliente cliente);
    public Cliente actualizarCliente(Cliente cliente);
    public Cliente eliminarClienteSoft(Cliente cliente);
    public void eliminarClienteHard(Cliente cliente);

}

