package com.monetique.um.controllers;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.monetique.security.securityDispatcher.SecurityConstants;
import com.monetique.um.dao.entities.Groupe;
import com.monetique.um.dto.ResponseDto;
import com.monetique.um.service.IGroupeService;

@RestController
public class GroupeController {
	@Autowired
	private IGroupeService groupeService;
	@Autowired
	HttpServletResponse  httpServletResponse;
	
	@PreAuthorize("hasAuthority('gestgroupe')")
	@PostMapping("/addGroupe")
	public Groupe addGroupe(@RequestBody Groupe groupe) {
		return groupeService.addGroupe(groupe);
	}
	@PreAuthorize("hasAuthority('gestgroupe')")
	@PostMapping("/updateGroupe")
	public Groupe updateGroupe(@RequestBody Groupe groupe) {
		return groupeService.updateGroupe(groupe);
	}
	@PreAuthorize("hasAuthority('gestgroupe')")
	@GetMapping("/getGroupe/{id}")
	public Groupe getGroupe(@PathVariable long id) {
		return groupeService.getGroupe(id);
	}
	@PreAuthorize("hasAuthority('gestgroupe')")
	@GetMapping("/getAllGroupe")
	public  ResponseDto  getAllGroupe() throws Exception {
	   List<Groupe>	groupes = groupeService.getAllGroupe();
		return   new ResponseDto(httpServletResponse.getHeader(SecurityConstants.HEADER_STRING), groupes);

	}
	@PreAuthorize("hasAuthority('users')")
	@GetMapping("/getAllGroupeActive")
	public ResponseDto getAllGroupeActive() {
		   List<Groupe> groupes=groupeService.getAllGroupeActive();
			return   new ResponseDto(httpServletResponse.getHeader(SecurityConstants.HEADER_STRING), groupes);
	}
	@PreAuthorize("hasAuthority('gestgroupe')")
	@GetMapping("/changerEtatGroupe/{id}/{active}")
	public int changerEtatGroup(@PathVariable long id,@PathVariable boolean active) {
		return groupeService.changerEtatGroup(id, active);
	}

}
