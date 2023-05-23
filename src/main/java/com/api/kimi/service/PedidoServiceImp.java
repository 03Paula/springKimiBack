package com.api.kimi.service;

import com.api.kimi.dto.converter.PedidoDTOConverter;
import com.api.kimi.dto.pedido.CrearPedidoDTO;
import com.api.kimi.dto.pedido.PedidoDTO;
import com.api.kimi.error.UsuarioNotFoundException;
import com.api.kimi.error.PedidoNotFoundException;
import com.api.kimi.error.ProductoNotFoundException;
import com.api.kimi.model.Usuario;
import com.api.kimi.model.Pedido;
import com.api.kimi.model.Producto;
import com.api.kimi.repository.UsuarioRepository;
import com.api.kimi.repository.PedidoRepository;
import com.api.kimi.repository.ProductoRepository;
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
public class PedidoServiceImp implements PedidoService{
    @Autowired
    private PedidoRepository pedidoRepository;
    private final PedidoDTOConverter pedidoDTOConverter;
    private final UsuarioRepository usuarioRepository;
    private final ProductoRepository productoRepository;

    @Override
    @Transactional(readOnly = true)
    public Iterable<PedidoDTO> findAll(){
        List<PedidoDTO> dtoList = pedidoRepository.findAll().stream().map(pedidoDTOConverter::convertToDTO).collect(Collectors.toList());
        return ResponseEntity.ok(dtoList).getBody();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<PedidoDTO> findById(Long id) {
        return  pedidoRepository.findById(id).map(pedidoDTOConverter::convertToDTO);
    }

    @Override
    @Transactional
    public ResponseEntity<?> save(CrearPedidoDTO crearPedido) {
        Usuario usuario;
        usuario = usuarioRepository.findById(crearPedido.getId_cliente()).orElseThrow(() -> new UsuarioNotFoundException(crearPedido.getId_cliente()));
        List<Long> productosId = new ArrayList<Long>();
        for(Long id : crearPedido.getId_productos()){
            if(productoRepository.findById(id).isPresent()){
                productosId.add(id);
            }else{
                new ProductoNotFoundException(id);
            }
        }
        Pedido nPedido = new Pedido();
        nPedido.setUsuario(usuario);

        nPedido.setEstado_pedido(crearPedido.getEstado_pedido());
        nPedido.setFecha_pedido(crearPedido.getFecha_pedido());
        return ResponseEntity.status(HttpStatus.CREATED).body(pedidoRepository.save(nPedido));
    }

    @Override
    public Pedido update(CrearPedidoDTO modPedido ,Long id) {
        Usuario usuario = usuarioRepository.findById(modPedido.getId_cliente()).orElseThrow(() -> new UsuarioNotFoundException(modPedido.getId_cliente()));
        List<Producto> productos = new ArrayList<>();
        for(Producto producto : productos ){
            if(productoRepository.findById(producto.getId()) != null){
                productos.add(producto);
            }else{
                new ProductoNotFoundException(producto.getId());
            }
        }
        return pedidoRepository.findById(id).map(pedido -> {
            pedido.setUsuario(usuario);
            pedido.setProductos(productos);
            pedido.setFecha_pedido(modPedido.getFecha_pedido());
            pedido.setUsuario(usuario);
            pedido.setEstado_pedido(modPedido.getEstado_pedido());
            return pedidoRepository.save(pedido);
        }).orElseThrow(() -> new PedidoNotFoundException(id));
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        pedidoRepository.deleteById(id);
    }
}
