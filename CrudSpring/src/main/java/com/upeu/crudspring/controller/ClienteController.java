package com.upeu.crudspring.controller;

import com.upeu.crudspring.models.Cliente;
import com.upeu.crudspring.services.ClienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/clientes")
@RequiredArgsConstructor
public class ClienteController {

    private final ClienteService service;

    @GetMapping("/listar")
    public String listar(Model model) {
        List<Cliente> clientes = service.listarTodo();
        model.addAttribute("clientes", clientes);
        return "listar_cliente";
    }

    @GetMapping("/nuevo")
    public String mostrarFormularioRegistro(Model model) {
        model.addAttribute("cliente", new Cliente());
        return "crear_cliente";
    }

    @PostMapping("/guardar")
    public String guardarCliente(@ModelAttribute("cliente") Cliente cliente) {
        if (cliente.getCreated() == null) {
            cliente.setCreated(new Date());
        }
        service.crearCliente(cliente);
        return "redirect:/clientes/listar";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable("id") Long id, Model model) {
        Cliente cliente = service.obtenerCliente(id);
        model.addAttribute("cliente", cliente);
        return "crear_cliente";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable("id") Long id) {
        Cliente cliente = service.obtenerCliente(id);
        if (cliente != null) {
            service.eliminarClienteSoft(cliente);
        }
        return "redirect:/clientes/listar";
    }
}

