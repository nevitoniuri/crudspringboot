package br.com.nevitoniuri.crudspringboot.controller;

import java.util.List;

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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.nevitoniuri.crudspringboot.model.Usuario;
import br.com.nevitoniuri.crudspringboot.repository.UsuarioRepository;

@RestController
public class GreetingsController {
	
	@SuppressWarnings("unused")
	@Autowired  //injecao de dependencia
	private UsuarioRepository usuarioRepository;

	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/retornanome/{name}", method = RequestMethod.GET)
	public String greetingText(@PathVariable String name) {
		return "Curso Spring Boot API: " + name + "!";
	}
	
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/olamundo/{name}", method = RequestMethod.GET)
	public String metodoRetornaOlaMundo (@PathVariable String name) {
		return "Ola mundo " + name;
	}
	
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/addusuario/{name}", method = RequestMethod.GET)
	public String addUsuario (@PathVariable String name) {
		
		Usuario usuario = new Usuario();
		usuario.setNome(name);
		
		usuarioRepository.save(usuario);
		
		return "Usuario Adicionado no Banco de Dados: " + usuario.getNome();
	}
	
	@GetMapping(value = "listatodos")
	@ResponseBody() /* Retorna os dados ao corpo da resposta*/
	public ResponseEntity<List<Usuario>> listaUsuario() {
		List<Usuario> usuarios = usuarioRepository.findAll(); /*consulta no banco*/
		return new ResponseEntity<List<Usuario>>(usuarios, HttpStatus.OK); /* retorna a lista em JSON*/
	}
	
	@PostMapping(value = "salvar") /*mapeia a url*/
	@ResponseBody /* descricao da resposta */
	public ResponseEntity<Usuario> salvar (@RequestBody Usuario usuario){ //era RequestBody
		
		Usuario usuarioSalvo = usuarioRepository.save(usuario);
		
		return new ResponseEntity<Usuario>(usuarioSalvo, HttpStatus.CREATED);
	}
	
	@DeleteMapping(value = "delete") /*mapeia a url*/
	@ResponseBody /* descricao da resposta */
	public ResponseEntity<String> deletar (@RequestParam Long idUsuario){
		
		usuarioRepository.deleteById(idUsuario);
		
		return new ResponseEntity<String>("Usuario com ID " + idUsuario + " deletado com sucesso.", HttpStatus.OK);
	}
	
	@GetMapping(value = "buscaruserid") /*mapeia a url*/
	@ResponseBody /* descricao da resposta */
	public ResponseEntity<Usuario> buscaruserid (@RequestParam(name = "idUsuario") Long idUsuario){
		
		Usuario usuarioBuscado = usuarioRepository.findById(idUsuario).get();
		
		return new ResponseEntity<Usuario>(usuarioBuscado, HttpStatus.OK);
	}
	
	
	@PutMapping(value = "atualizar") /*mapeia a url*/
	@ResponseBody /* descricao da resposta */
	public ResponseEntity<?> atualizar (@RequestBody Usuario usuario){
		
		if(usuario.getId() == null) {
			return new ResponseEntity<String> ("Id n??o foi informado ou n??o encontrado no Banco de Dados.", HttpStatus.OK);
		}
		
		Usuario usuarioAtualizado = usuarioRepository.saveAndFlush(usuario);
		
		return new ResponseEntity<Usuario>(usuarioAtualizado, HttpStatus.CREATED);
	}
	
	
	@GetMapping(value = "buscarpornome") /*mapeia a url*/
	@ResponseBody /* descricao da resposta */
	public ResponseEntity<List<Usuario>> buscarPorNome (@RequestParam(name = "name") String name){
		
		List<Usuario> usuarioBuscado = usuarioRepository.buscarPorNome(name.trim().toUpperCase());
		
		return new ResponseEntity<List<Usuario>> (usuarioBuscado, HttpStatus.OK);
	}
	
}
