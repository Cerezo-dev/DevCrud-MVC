package com.upeu.crudspring.controller;

import com.upeu.crudspring.models.Producto;
import com.upeu.crudspring.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping
@RequiredArgsConstructor

public class ProductoController {

    private final ProductService service;

    @GetMapping("/listar")
    public String listar(Model model){
        List<Producto> productos=service.listarTodo();
        model.addAttribute("productos", productos);
        return "index";
    }

    @GetMapping("/nuevo")
    public String mostrarFormularioRegistro(Model model) {
        model.addAttribute("producto", new Producto());
        return "crear_producto";
    }

    @PostMapping ("/guardar")
    public String guardarProducto(@ModelAttribute("productos") Producto producto){
        if (producto.getCreated() == null) {
            producto.setCreated(new Date());
        }
        service.crearProducto(producto);
        return "redirect:/listar";
    }
}
