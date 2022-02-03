package br.com.itau.resources;

import br.com.itau.models.Contrato;
import br.com.itau.repositories.ContratoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/contratos")
public class ContratoResource {

	@Autowired
	private ContratoRepository contratoRepository;

	@GetMapping("/{id}")
	public ResponseEntity<Contrato> buscarPeloCodigo(@PathVariable Long id) {
		Optional<Contrato> contratoOptional = contratoRepository.findById(id);
		return contratoOptional.isPresent() ? ResponseEntity.ok(contratoOptional.get()) : ResponseEntity.notFound().build();
	}
	
	@GetMapping
	public List<Contrato> listar() {
		return contratoRepository.findAll();
	}

	@PostMapping
	public ResponseEntity<Contrato> cadastrar(@Valid @RequestBody Contrato contrato, HttpServletResponse response) {
		Contrato contratoSalvo = contratoRepository.save(contrato);
		return ResponseEntity.status(HttpStatus.CREATED).body(contratoSalvo);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long id) {
		contratoRepository.deleteById(id);
	}

	@PutMapping
	public ResponseEntity<Contrato> atualizar(@Valid @RequestBody Contrato contrato) {
		try {
			Contrato contrato1Salvo = contratoRepository.save(contrato);
			return ResponseEntity.ok(contrato1Salvo);
		} catch (IllegalArgumentException e) {
			return ResponseEntity.notFound().build();
		}
	}

}