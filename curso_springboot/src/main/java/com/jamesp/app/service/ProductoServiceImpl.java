package com.jamesp.app.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jamesp.app.entity.Producto;
import com.jamesp.app.repository.ProductoRepository;

@Service
public class ProductoServiceImpl implements ProductoService {

	@Autowired
	private ProductoRepository produRepository;
	
	@Override
	@Transactional(readOnly = true)
	public Iterable<Producto> findAll() {
		
		return produRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Producto> findAll(Pageable pageable) {
		
		return produRepository.findAll(pageable);
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Producto> findById(Long id) {
		
		return produRepository.findById(id);
	}

	@Override
	@Transactional
	public Producto save(Producto user) {

		return produRepository.save(user);
	}

	@Override
	@Transactional
	public void deleteById(Long id) {
		produRepository.deleteById(id);
	}
	
}
