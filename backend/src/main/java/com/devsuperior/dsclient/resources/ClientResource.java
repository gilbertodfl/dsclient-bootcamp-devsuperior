package com.devsuperior.dsclient.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.devsuperior.dsclient.dto.ClientDTO;
import com.devsuperior.dsclient.services.ClientService;
// camanda de CONTROLADOR  REST
@RestController
@RequestMapping ( value = "/clients")
public class ClientResource {
	
	@Autowired
	private ClientService service;
/*	@GetMapping
	public ResponseEntity<List<ClientDTO>> findAll()
	{
		List<ClientDTO> list = service.findAll();
		return ResponseEntity.ok().body(list);
		
	}
*/
  @GetMapping
	public ResponseEntity <Page<ClientDTO> >findAll(
			@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linesPerPage", defaultValue = "12") Integer linesPerPage,
			@RequestParam(value = "direction", defaultValue = "ASC") String direction,
			@RequestParam(value = "orderBy", defaultValue = "name") String orderBy
			){
		PageRequest pageRequest=  PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy );
		
		Page<ClientDTO> list = service.findAllPaged( pageRequest );
		return  ResponseEntity.ok().body(list);
		
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<ClientDTO> findById( @PathVariable Long id)
	{
		ClientDTO dto = service.findById(id);
		return ResponseEntity.ok().body(dto);
		
	}
	@PostMapping
	public ResponseEntity<ClientDTO> insert ( @RequestBody ClientDTO dto){
		dto = service.insert(dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(dto.getId()).toUri();
		return ResponseEntity.created(uri).body(dto);
		/* 201 - resource created
		 * https://developer.mozilla.org/en-US/docs/Web/HTTP/Status/201
		 */
	}
	//back end, front end, padr??o camadas, mvc, rest=> 
	//https://www.youtube.com/watch?v=b8uLFfzcVQ8
	@PutMapping( value = "/{id}")
	public ResponseEntity<ClientDTO> update (@PathVariable Long id, @RequestBody ClientDTO dto){
		dto = service.update(id, dto);
		return ResponseEntity.ok().body(dto);
	}

	@DeleteMapping( value = "/{id}")
	public ResponseEntity <Void> delete(@PathVariable Long id){
		// como n??o tem corpo na requisi????o, ent??o o retorno ?? Void
		service.delete(id);
		return  ResponseEntity.noContent().build();
	}
	
	
}
