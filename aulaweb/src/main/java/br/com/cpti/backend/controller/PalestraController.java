package br.com.cpti.backend.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.cpti.backend.entity.Convidado;
import br.com.cpti.backend.entity.Palestra;
import br.com.cpti.backend.repository.ConvidadoRepository;
import br.com.cpti.backend.repository.PalestraRepository;
import jakarta.transaction.Transactional;

@Controller
public class PalestraController {

	@Autowired
	private PalestraRepository repository;
	
	@Autowired
	private ConvidadoRepository convidadoRepository;

	@GetMapping("/cadastrar")
	public String formularioPalestra() {
		return "pages/formularioCadastrarPalestra";
	}
	
	@PostMapping("/cadastrar")
	public String formularioPalestra(Palestra palestra) {
		repository.save(palestra);
		return "pages/formularioCadastrarPalestra";
	}
	
	
	@GetMapping("/palestras")
	public ModelAndView listarPalestras() {
		ModelAndView mv = new ModelAndView("pages/listarPalestras");
		List<Palestra> palestras = repository.findAll();
		mv.addObject("palestras",palestras);
		return mv;
	}
	
	@GetMapping("{id}")
	public ModelAndView buscarPalestra(@PathVariable Long id) {
		Optional<Palestra> palestra = repository.findById(id);
		ModelAndView mv = new ModelAndView("pages/buscarPalestra");
		mv.addObject("palestra", palestra.get());
		List<Convidado> convidados = convidadoRepository.findByPalestra(palestra.get());
		mv.addObject("convidados",convidados);
		return mv;
	}

	@Transactional
	@PostMapping("{id}")
	public String inserirConvidadoPalestra(@PathVariable Long id, Convidado convidado) {
		Optional<Palestra> palestra = repository.findById(id);
		if (palestra.isPresent()) {
			convidado.setPalestra(palestra.get());
			convidadoRepository.save(convidado);
		}
		return "redirect:/{id}";
	}
}
