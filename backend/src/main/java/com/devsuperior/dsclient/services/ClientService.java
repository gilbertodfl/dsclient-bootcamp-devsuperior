package com.devsuperior.dsclient.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.dsclient.dto.ClientDTO;
import com.devsuperior.dsclient.entities.Client;
import com.devsuperior.dsclient.repositories.ClientRepository;

// camada de serviço
@Service
public class ClientService {
	// o @Autowired permite a injeção aqui
	@Autowired
	private ClientRepository repository;
	// importe do spring aqui
	@Transactional( readOnly = true)
	public List<ClientDTO> findAll(){
		List <Client>  list = repository.findAll();
		List <ClientDTO> listDTO = list.stream().map( x -> new ClientDTO(x)).collect(Collectors.toList());
		return listDTO;
	}
	@Transactional( readOnly = true)
	public ClientDTO findById(Long id) {
		Optional<Client> obj = repository.findById(id);
		Client entity = obj.get();
		return new ClientDTO(entity);
	}
}