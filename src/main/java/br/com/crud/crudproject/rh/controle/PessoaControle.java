package br.com.crud.crudproject.rh.controle;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import br.com.crud.crudproject.rh.dominio.Pessoa;
import br.com.crud.crudproject.rh.dominio.PessoaRepositorio;

//Listar pessoas

@Controller
public class PessoaControle {
	
	private PessoaRepositorio pessoaRepo;
	
	public PessoaControle(PessoaRepositorio pessoaRepo) {
		this.pessoaRepo = pessoaRepo;
	}
	
	@GetMapping("/rh/pessoas")
	public String pessoas(Model model) {
		model.addAttribute("listaPessoas", pessoaRepo.findAll());
		return "rh/pessoas/index";
	}
	
	@GetMapping("/rh/pessoas/nova")
	public String novaPessoa(@ModelAttribute("pessoa") Pessoa pessoa) {
		return "rh/pessoas/form";
	}
	
	@GetMapping("/rh/pessoas/{id}")
	public String alterarPessoa(@PathVariable("id") long id, Model model) {
		Optional<Pessoa> pessoaOpt = pessoaRepo.findById(id);
		if(pessoaOpt.isEmpty()) {
			throw new IllegalArgumentException("Pessoa invalida");
		}		
		
		model.addAttribute("pessoa", pessoaOpt.get());
		return "rh/pessoas/form";
	}
	
	@PostMapping("/rh/pessoas/salvar")
	public String salvarPessoa(@ModelAttribute("pessoa") Pessoa pessoa) {
		pessoaRepo.save(pessoa);
		return "redirect:/rh/pessoas";
	}
	
	@GetMapping("/rh/pessoas/excluir/{id}")
	public String excluirPessoa(@PathVariable("id") Long id) {
		Optional<Pessoa> pessoaOpt = pessoaRepo.findById(id);
		if(pessoaOpt.isEmpty()) {
			throw new IllegalArgumentException("Pessoa invalida");			
		}
		pessoaRepo.delete(pessoaOpt.get());
		return "redirect:/rh/pessoas";
		
	}

}
