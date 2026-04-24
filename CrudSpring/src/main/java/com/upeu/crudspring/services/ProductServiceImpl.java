package com.upeu.crudspring.services;

import com.upeu.crudspring.models.Producto;
import com.upeu.crudspring.repository.ProductoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductoRepository productoRepository;

    @Override
    public List<Producto> listarTodo() {
        //lista los productos, los parsea a List, para evitar errores
        return (List<Producto>) productoRepository.findAll();
    }
    @Override
    public Producto crearProducto(Producto producto) {
        //A productRepository, le aplica el metodo 'save' con el parametro producto
        return productoRepository.save(producto);
    }

    @Override
    public Producto obtenerProducto(Long id) {
        //retorna el producto por ID o si es nulo, nulo
        return productoRepository.findById(id).orElse(null);
    }

    @Override
    public Producto actualizarProducto(Producto producto) {
        //obtiene producto por ID
        Producto productDB = obtenerProducto(producto.getId());
        //si productBD is null, retorna null
        if (productDB == null) {
            return null;
        }
        //si no entra en  la condicional, procede a obtner poner el dato con set, y lo obtiene con get
        productDB.setNombre(producto.getNombre());
        productDB.setDescripcion(producto.getDescripcion());
        productDB.setCantidad(producto.getCantidad());
        productDB.setPrecio(producto.getPrecio());
        return productoRepository.save(productDB);

    }

    @Override
    public Producto eliminarProductoSoft(Producto producto) {
        Producto productoDB=obtenerProducto(producto.getId());
        if (productoDB==null)
            return null;
        productoDB.setDisponible(false);
        return productoRepository.save(productoDB);
    }

    @Override
    public void eliminarProductoHard(Producto producto) {
        productoRepository.delete(producto);
    }
}
