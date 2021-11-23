package br.ufscar.dc.dsw.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.ufscar.dc.dsw.domain.Pessoa;
import br.ufscar.dc.dsw.domain.Profissionais;
import br.ufscar.dc.dsw.service.spec.IPessoaService;

@Controller
@RequestMapping("/admins")
public class PessoaController {
	
	@Autowired
	private IPessoaService service;
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	@GetMapping("/cadastrar")
	public String cadastrar(ModelMap model) {
		model.addAttribute("admin", new Pessoa());
		return "admin/cadastro";
	}
	
	@GetMapping("/listar")
	public String listar(ModelMap model) {
		model.addAttribute("admins",service.buscarTodos());
		return "admin/lista";
	}
	
	@PostMapping("/salvar")
	public String salvar(@Valid Pessoa pessoa, BindingResult result, RedirectAttributes attr) {
		
		if (result.hasErrors()) {
			return "admin/cadastro";
		}

		System.out.println("password = " + pessoa.getSenha());
		
		pessoa.setSenha(encoder.encode(pessoa.getSenha()));
		service.salvar(pessoa);
		attr.addFlashAttribute("sucess", "Usuário inserido com sucesso.");
		return "redirect:/admins/listar";
	}
	
	@GetMapping("/editar/{id}")
	public String preEditar(@PathVariable("id") Long id, ModelMap model) {
		model.addAttribute("admin", service.buscarPorId(id));
		return "admin/cadastro";
	}
	
	@PostMapping("/editar")
	public String editar(@Valid Pessoa pessoa, BindingResult result, RedirectAttributes attr) {
		
		if (result.hasErrors()) {
			return "admin/cadastro";
		}

		System.out.println(pessoa.getSenha());
		
		service.salvar(pessoa);
		attr.addFlashAttribute("sucess", "Usuário editado com sucesso.");
		return "redirect:/admins/listar";
	}
	
	@GetMapping("/excluir/{id}")
	public String excluir(@PathVariable("id") Long id, ModelMap model) {
		service.excluir(id);
		model.addAttribute("sucess", "Usuário excluído com sucesso.");
		return listar(model);
	}
}