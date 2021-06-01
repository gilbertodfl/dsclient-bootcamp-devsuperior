package com.devsuperior.dsclient.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.devsuperior.dsclient.entities.Client;
// camada de acesso a dados
// Observe que aqui é uma interface e não uma class e extende da Jpa/reepository
@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
	

}
