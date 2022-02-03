package br.com.itau.resources;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import br.com.itau.repositories.ClienteRepository;
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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.itau.models.Cliente;

@RestController
@RequestMapping("/clientes")
public class ClienteResource {

	@Autowired
	private ClienteRepository clienteRepository;

	@GetMapping("/{id}")
	public ResponseEntity<Cliente> buscarPeloCodigo(@PathVariable Long id) {
		Optional<Cliente> cliente = clienteRepository.findById(id);
		return cliente.isPresent() ? ResponseEntity.ok(cliente.get()) : ResponseEntity.notFound().build();
	}
	
	@GetMapping
	public List<Cliente> listar() {
		return clienteRepository.findAll();
	}

	@PostMapping
	public ResponseEntity<Cliente> cadastrar(@Valid @RequestBody Cliente cliente, HttpServletResponse response) {
		cliente.setDataCadastro(new Date());
		Cliente clienteSalvo = clienteRepository.save(cliente);
		return ResponseEntity.status(HttpStatus.CREATED).body(clienteSalvo);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long id) {
		clienteRepository.deleteById(id);
	}

	@PutMapping
	public ResponseEntity<Cliente> atualizar(@Valid @RequestBody Cliente cliente) {
		try {
			Cliente clienteSalvo = clienteRepository.save(cliente);
			return ResponseEntity.ok(clienteSalvo);
		} catch (IllegalArgumentException e) {
			return ResponseEntity.notFound().build();
		}
	}

}