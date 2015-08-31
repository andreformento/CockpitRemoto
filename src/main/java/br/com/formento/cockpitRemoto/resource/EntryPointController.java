package br.com.formento.cockpitRemoto.resource;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.formento.cockpitRemoto.resource.service.EntryPointService;
import br.com.formento.cockpitRemoto.resource.service.EntryPointServiceImpl;

@RestController
public class EntryPointController {

	private EntryPointService entryPointService;

	public EntryPointController() {
		entryPointService = new EntryPointServiceImpl();
	}

	@RequestMapping(path = "/rest/enviarSinalParaMarte", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE
			+ ";charset=UTF-8")
	public String enviarSinalParaMarteGet(@RequestParam(value = "comandos", defaultValue = "{}") List<String> comandos) {
		return entryPointService.processarComandos(comandos);
	}

}
