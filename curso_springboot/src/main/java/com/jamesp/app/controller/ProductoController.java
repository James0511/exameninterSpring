package com.jamesp.app.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jamesp.app.entity.Producto;
import com.jamesp.app.service.ProductoService;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

	@Autowired
	private ProductoService produService;
	
	
	//Create a new product
	
	@PostMapping
	public ResponseEntity<?> create (@RequestBody Producto produ){
		return ResponseEntity.status(HttpStatus.CREATED).body(produService.save(produ));
	}
	
	//Leer a new product
	@GetMapping("/{cod}")
	public ResponseEntity<?> read(@PathVariable(value = "cod") Long produCod){
		Optional<Producto> oProdu = produService.findById(produCod);
		if(!oProdu.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(oProdu);
	}
	
	//Update a product
	@PutMapping("/{cod}")
	public ResponseEntity<?> update(@RequestBody Producto produDetails, @PathVariable(value = "cod") Long produCod){
		Optional<Producto> oProdu = produService.findById(produCod);
		
		if(!oProdu.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		
		oProdu.get().setDescripcion(produDetails.getDescripcion());
		oProdu.get().setPrecio(produDetails.getPrecio());
		oProdu.get().setCantidad(produDetails.getCantidad());
		
		return ResponseEntity.status(HttpStatus.CREATED).body(produService.save(oProdu.get()));
	}
	
	//Delete an product
	@DeleteMapping("/{cod}")
	public ResponseEntity<?> delete (@PathVariable(value = "cod") Long produCod){
		
		if(!produService.findById(produCod).isPresent()) {
			return ResponseEntity.notFound().build();
		}
		
		produService.deleteById(produCod);
		return ResponseEntity.ok().build();
	}
	
	//Read All products
	@GetMapping
	public List<Producto> readAll(){
		
		List<Producto> products = StreamSupport
				.stream(produService.findAll().spliterator(), false)
				.collect(Collectors.toList());
		
		return products;
	}
	
	
}
