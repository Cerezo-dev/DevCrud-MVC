package com.upeu.crudspring.controller;

import com.upeu.crudspring.models.Producto;
import com.upeu.crudspring.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/productos")
@RequiredArgsConstructor

public class ProductoController {

    private final ProductService service;

    @GetMapping("/listar")
    public String listar(Model model){
        List<Producto> productos=service.listarTodo();
        model.addAttribute("productos", productos);
        return "listar_producto";
    }

    @GetMapping("/nuevo")
    public String mostrarFormularioRegistro(Model model) {
        model.addAttribute("producto", new Producto());
        return "crear_producto";
    }

    @PostMapping ("/guardar")
    public String guardarProducto(@ModelAttribute("producto") Producto producto){
        if (producto.getCreated() == null) {
            producto.setCreated(new Date());
        }
        service.crearProducto(producto);
        return "redirect:/productos/listar";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable("id") Long id, Model model){
        Producto producto = service.obtenerProducto(id);
        model.addAttribute("producto", producto);
        return "crear_producto";
    }
    @GetMapping("/eliminar/{productos}")
    public String eliminar(@PathVariable("productos") Producto producto){
        service.eliminarProductoSoft(producto);
        return "redirect:/productos/listar";
    }
}
