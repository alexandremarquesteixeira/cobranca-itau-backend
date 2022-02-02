package br.com.itau.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.itau.models.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

}