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
        //lista los productos
        return (List<Producto>) productoRepository.findAll();
    }

    @Override
    public Producto obtenerProducto(Long id) {
        return productoRepository.findById(id).orElse(null);
    }

    @Override
    public Producto crearProducto(Producto producto) {
        return productoRepository.save(producto);
    }

    @Override
    public Producto actualizarProducto(Producto producto) {
        Producto productDB = obtenerProducto(producto.getId());
        if (productDB == null) {
            return null;
        }
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
