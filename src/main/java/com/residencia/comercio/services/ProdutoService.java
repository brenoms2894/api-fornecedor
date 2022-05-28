package com.residencia.comercio.services;

import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.residencia.comercio.entities.Produto;
import com.residencia.comercio.repositories.ProdutoRepository;

@Service
public class ProdutoService {
	@Autowired
	ProdutoRepository produtoRepository;

	public List<Produto> findAll() {
		return produtoRepository.findAll();
	}

	public Produto findById(Integer id) {
		return produtoRepository.findById(id).isPresent() ? produtoRepository.findById(id).get() : null;
	}

	public Produto save(Produto produto) {
		return produtoRepository.save(produto);
	}
	
	/*public Produto saveProdutoWithImage(String produto, MultipartFile file) {
		Produto newProduto = new Produto();
		
		try {
			ObjectMapper objMapper = new ObjectMapper();
			newProduto = objMapper.readValue(produto, Produto.class);
		} catch (Exception e) {
			System.out.println("Erro de conversão");
			e.printStackTrace();
		}
		
		Produto produtoSaved = produtoRepository.save(newProduto);
		
		String filename = produtoSaved.getIdProduto()+"."+StringUtils.cleanPath(file.getOriginalFilename());
		
		System.out.println();
		
		try {
			Files.copy(file.getInputStream(), path.resolve(filename), StandardCopyOption.REPLACE_EXISTING);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			produtoSaved.setImagemProduto(path.resolve(filename).toRealPath().toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return produtoRepository.save(produtoSaved);
	}*/

	public Produto update(Produto produto) {
		return produtoRepository.save(produto);
	}

	public Produto updateComId(Produto produto, Integer id) {
		Produto produtoBD = produtoRepository.findById(id).isPresent() ? produtoRepository.findById(id).get() : null;
		Produto produtoAtualizado = null;
		if (null != produtoBD) {
			produtoBD.setCategoria(produto.getCategoria());

			produtoAtualizado = produtoRepository.save(produtoBD);
		}
		return produtoRepository.save(produto);
	}

	public void delete(Produto produto) {
		produtoRepository.delete(produto);
	}

	public void deletePorId(Integer id) {
		produtoRepository.deleteById(id);
	}

}
