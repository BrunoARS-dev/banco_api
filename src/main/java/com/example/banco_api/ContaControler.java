package com.example.banco_api;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.banco_api.service.BancoService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;



@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/contas")
public class ContaControler {
	
	private final BancoService bancoService;
	
	
	public ContaControler(BancoService bancoService) {
		super();
		this.bancoService = bancoService;
	}

	
	@PostMapping
	public ResponseEntity<Conta> criarConta(@RequestBody Conta conta) {
	    Conta novaConta = bancoService.criarConta(conta);
	    return ResponseEntity.status(HttpStatus.CREATED).body(novaConta);
	}

	
	
	@GetMapping("/{numero}")
	public ResponseEntity<Conta> buscarConta(@PathVariable int numero) {
	    Conta conta = bancoService.buscarConta(numero);
	    return ResponseEntity.ok(conta);
	}

	
	@PostMapping("/{numero}/depositar")
	public ResponseEntity<Conta> depositar(@PathVariable int numero,
	                                       @RequestBody ValorDTO dto) {

	    Conta conta = bancoService.depositar(numero, dto.getValor());
	    return ResponseEntity.ok(conta);
	}

	@PostMapping("/{numero}/sacar")
	public ResponseEntity<Conta> sacar(@PathVariable int numero,
	                                   @RequestBody ValorDTO dto) {

	    Conta conta = bancoService.sacar(numero, dto.getValor());
	    return ResponseEntity.ok(conta);
	}

	
	@GetMapping
	public ResponseEntity<List<Conta>> listarContas() {
	    return ResponseEntity.ok(bancoService.listarContas());
	}


	@PostMapping("/transferencia")
	public ResponseEntity<String> transferir(@RequestBody TransferenciaDTO transf) {

	    bancoService.transferencia(
	        transf.getOrigem(),
	        transf.getDestino(),
	        transf.getValor()
	    );

	    return ResponseEntity.ok("Transferência realizada com sucesso!");
	}

	
}

