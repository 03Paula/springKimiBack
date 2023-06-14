package com.api.kimi.service.impl;

import com.api.kimi.dto.carrito.CarritoDTO;
import com.api.kimi.dto.carrito.CrearCarritoDTO;
import com.api.kimi.dto.converter.CarritoDTOConverter;
import com.api.kimi.error.Pedido.PedidoNotFoundException;
import com.api.kimi.error.Producto.ProductoNotFoundException;
import com.api.kimi.error.Usuario.UsuarioNotFoundException;
import com.api.kimi.model.Carrito;
import com.api.kimi.model.Producto;
import com.api.kimi.model.Usuario;
import com.api.kimi.repository.CarritoRepository;
import com.api.kimi.repository.ProductoRepository;
import com.api.kimi.repository.UsuarioRepository;
import com.api.kimi.service.CarritoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CarritoServiceImp implements CarritoService {
    @Autowired
    private CarritoRepository carritoRepository;
    private final CarritoDTOConverter carritoDTOConverter;
    private final UsuarioRepository usuarioRepository;
    private final ProductoRepository productoRepository;

    @Override
    @Transactional(readOnly = true)
    public Iterable<CarritoDTO> findAll(){
        List<CarritoDTO> dtoList = carritoRepository.findAll().stream().map(carritoDTOConverter::convertToDTO).collect(Collectors.toList());
        return ResponseEntity.ok(dtoList).getBody();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<CarritoDTO> findById(Long id) {
        return  carritoRepository.findById(id).map(carritoDTOConverter::convertToDTO);
    }

    @Override
    @Transactional
    public ResponseEntity<?> save(CrearCarritoDTO crearCarrito) {
        Usuario usuario;
        usuario = usuarioRepository.findById(crearCarrito.getUsuarioId()).orElseThrow(() -> new UsuarioNotFoundException(crearCarrito.getUsuarioId()));
        List<Producto> productosId = new ArrayList<>();
        Double precioTotal = 0.0;
        for(Long id : crearCarrito.getProductos()){
            Optional<Producto> producto;
            if(productoRepository.findById(id).isPresent()){
                producto = productoRepository.findById(id);
                productosId.add(producto.get());
                precioTotal += producto.get().getPrecio() * crearCarrito.getCantidad();
            }
        }
        if (!productosId.isEmpty()){
            Carrito nCarrito = new Carrito();
            nCarrito.setUsuario(usuario);
            nCarrito.setProductos(productosId);
            nCarrito.setPrecioTotal(precioTotal);
            nCarrito.setCantidad(crearCarrito.getCantidad());
            return ResponseEntity.status(HttpStatus.CREATED).body(carritoRepository.save(nCarrito));
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

    }

    @Override
    public Carrito update(CrearCarritoDTO modCarrito , Long id) {
        Usuario usuario = usuarioRepository.findById(modCarrito.getUsuarioId()).orElseThrow(() -> new UsuarioNotFoundException(modCarrito.getUsuarioId()));
        List<Producto> productos = new ArrayList<>();
        Double precioTotal = 0D;

        for(Producto producto : productos ){
            if(productoRepository.findById(producto.getId()) != null){
                productos.add(producto);
                precioTotal += producto.getPrecio() * producto.getCantidad();

            }else{
                new ProductoNotFoundException(producto.getId());
            }
        }
        Double finalPrecioTotal = precioTotal;
        return carritoRepository.findById(id).map(car -> {
            car.setUsuario(usuario);
            car.setProductos(productos);
            car.setPrecioTotal(finalPrecioTotal);
            car.setCantidad(modCarrito.getCantidad());
            return carritoRepository.save(car);
        }).orElseThrow(() -> new PedidoNotFoundException(id));

    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        carritoRepository.deleteById(id);
    }

    @Override
    public List<CarritoDTO> findByUsuarioId(Long id_usuario) {
        return carritoRepository.findByUsuarioId(id_usuario).stream().map(carritoDTOConverter::convertToDTO).collect(Collectors.toList());
    }
}
