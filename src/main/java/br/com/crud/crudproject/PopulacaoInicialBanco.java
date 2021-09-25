package br.com.crud.crudproject;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import br.com.crud.crudproject.rh.dominio.Pessoa;
import br.com.crud.crudproject.rh.dominio.PessoaRepositorio;

@Component
public class PopulacaoInicialBanco implements CommandLineRunner{

	@Autowired
	private PessoaRepositorio pessoaRepo;
	
	@Override
	public void run(String... args) throws Exception {
		
		Pessoa p1 = new Pessoa("Joao");
		p1.setDataNascimento(LocalDate.of(1990, 4, 1));
		p1.setEmail("joao@gmail.com");
		
		Pessoa p2 = new Pessoa("Maria");
		p2.setDataNascimento(LocalDate.of(1990, 1, 1));
		p2.setEmail("maria@gmail.com");
		
		pessoaRepo.save(p1);
		pessoaRepo.save(p2);
	}
	
	
	

}
