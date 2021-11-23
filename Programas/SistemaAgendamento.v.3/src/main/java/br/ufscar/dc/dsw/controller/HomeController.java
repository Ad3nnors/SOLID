package br.ufscar.dc.dsw.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.ufscar.dc.dsw.domain.Pessoa;
import br.ufscar.dc.dsw.domain.Profissionais;
import br.ufscar.dc.dsw.security.PessoaDetails;
import br.ufscar.dc.dsw.service.spec.IProfissionaisService;

@Controller
public class HomeController {

	@Autowired
	private IProfissionaisService service;
	
	private List<String> areas;
	private List<Profissionais> profissionais;
	
	private Pessoa getPessoa() {
		PessoaDetails pessoaDetails = (PessoaDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return pessoaDetails.getPessoa();
	}
	/*
	@PostMapping("/homecontroller")
	public String listarhome(@Valid String area, RedirectAttributes attr) {
		attr.addAttribute("profissionais", service.buscarPorArea(area));
		return "home";
	}*/
	
	@GetMapping("/")
	public String listar(ModelMap model) {
		
		profissionais = service.buscarTodos();
		/*
		for (int i = 0; i < profissionais.size(); i++) {
            areas.add(profissionais.get(i).getArea());
        }
		
		for (Profissionais profissional : profissionais) {
			areas.add(profissional.getArea());
		}
		//List<String> areasn = new ArrayList<>(new HashSet<>(areas));
	
		//Set<String> set = new HashSet<>(areas);
		//areas.clear();
		//areas.addAll(set);
		*/
		model.addAttribute("profissionais", profissionais);
		model.addAttribute("areas", areas);
		
		return "home";
	}
	
	@GetMapping("/homecontroller")
	public String preEditar(@RequestParam String area, ModelMap model) {
		profissionais = service.buscarPorArea(area);
		model.addAttribute("profissionais", profissionais);
		return "home";
	}
	
	
}