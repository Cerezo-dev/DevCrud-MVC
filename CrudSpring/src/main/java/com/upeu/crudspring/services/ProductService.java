package com.upeu.crudspring.services;

import com.upeu.crudspring.models.Producto;

import java.util.List;

public interface ProductService {
    public List<Producto> listarTodo();
    public Producto obtenerProducto(Long id);
    public Producto crearProducto(Producto producto);
    public Producto actualizarProducto(Producto producto);
    public Producto eliminarProductoSoft(Producto producto);
    public void eliminarProductoHard(Producto producto);

}
