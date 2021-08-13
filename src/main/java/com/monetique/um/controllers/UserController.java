package com.monetique.um.controllers;

import java.util.Set;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.monetique.dto.RequestDto;
import com.monetique.security.securityDispatcher.SecurityConstants;
import com.monetique.um.dao.entities.Groupe;
import com.monetique.um.dao.entities.User;
import com.monetique.um.dto.ResponseDto;
import com.monetique.um.dto.UserDto;
import com.monetique.um.service.IUserService;

@RestController
@Transactional
@CrossOrigin("*")
public class UserController {
    
	@Autowired
	IUserService userService;
	@Autowired
	HttpServletResponse  httpServletResponse;
	
	
	@PreAuthorize("hasAuthority('users')")
	@RequestMapping(value="/addUser",method= RequestMethod.POST)
	public @ResponseBody ResponseDto addUser(@RequestBody User user) throws Exception{
		userService.addNewUser(user);
		return   new ResponseDto(httpServletResponse.getHeader(SecurityConstants.HEADER_STRING), null);
	}
    
	
	@PreAuthorize("hasAuthority('users')")
	@RequestMapping(value="/updateUser",method= RequestMethod.POST)
	public @ResponseBody ResponseDto updateUser(@RequestBody User user) throws Exception{
		userService.updateUser(user);
		return   new ResponseDto(httpServletResponse.getHeader(SecurityConstants.HEADER_STRING), null);
		
	}
	
	@PreAuthorize("hasAuthority('users')")
	@RequestMapping(value="/getAllUser",method= RequestMethod.GET)
	public @ResponseBody ResponseDto getAllUser() throws Exception{
		return   new ResponseDto(httpServletResponse.getHeader(SecurityConstants.HEADER_STRING),userService.getAllUser());

	}
	
	@PreAuthorize("hasAuthority('users')")
	@RequestMapping(value="/getUser",method= RequestMethod.GET)
	public @ResponseBody ResponseDto getUser(@RequestParam long id) throws Exception{
		return   new ResponseDto(httpServletResponse.getHeader(SecurityConstants.HEADER_STRING),userService.getUser(id));

	}
	
	@PreAuthorize("hasAuthority('users')")
	@RequestMapping(value="/deleteUser",method= RequestMethod.DELETE)
	public @ResponseBody void deleteUser(@RequestParam long id) throws Exception {
		userService.deleteUser(id);
	}
	
	@PreAuthorize("hasAuthority('users')")
	@RequestMapping(value="/addRulesToUser",method= RequestMethod.POST)
	public @ResponseBody ResponseDto addRulesToUser(@RequestBody UserDto UserDto) throws Exception {
		userService.addRulesToUser(UserDto.getRules(),UserDto.getIdUser());
		return   new ResponseDto(httpServletResponse.getHeader(SecurityConstants.HEADER_STRING),null);

	}
	
	@PreAuthorize("hasAuthority('users')")
	@RequestMapping(value="/removeRulesToUser",method= RequestMethod.POST)
	public @ResponseBody ResponseDto removeRulesToUser(@RequestBody UserDto UserDto) throws Exception {
		userService.removeRulesToUser(UserDto.getRules(),UserDto.getIdUser());
		return   new ResponseDto(httpServletResponse.getHeader(SecurityConstants.HEADER_STRING),null);

	}
	
	@PreAuthorize("hasAuthority('users')")
	@RequestMapping(value="/addRuleToUser",method= RequestMethod.POST)
	public @ResponseBody ResponseDto addRuleToUser(@RequestBody UserDto UserDto) throws Exception {
		userService.addRuleToUser(UserDto.getRule(),UserDto.getIdUser());
		return   new ResponseDto(httpServletResponse.getHeader(SecurityConstants.HEADER_STRING),null);
	}
	
	@PreAuthorize("hasAuthority('users')")
	@RequestMapping(value="/removeRuleToUser",method= RequestMethod.POST)
	public @ResponseBody ResponseDto removeRuleToUser(@RequestBody UserDto UserDto) throws Exception {
		userService.removeRuleToUser(UserDto.getRule(),UserDto.getIdUser());
		return   new ResponseDto(httpServletResponse.getHeader(SecurityConstants.HEADER_STRING),null);
	}
	
	@PreAuthorize("hasAuthority('users')")
	@RequestMapping(value="/changeStatutUser",method= RequestMethod.POST)
	public @ResponseBody ResponseDto changeStatusUser(@RequestBody User user) throws Exception {	
		userService.changeStatusUser(user);
		return   new ResponseDto(httpServletResponse.getHeader(SecurityConstants.HEADER_STRING), null);
		
	}
	
	/*
	 * méthode Rest qui permet de réinitialiser le mot de passe oublié d'un utilisateur
	 */
	@PreAuthorize("hasAuthority('users')")
	@RequestMapping(value="/resetPassword",method=RequestMethod.POST)
	public @ResponseBody ResponseDto resetPasswordRestService(@RequestBody User user) throws Exception{
		userService.resetPassword(user);
		return   new ResponseDto(httpServletResponse.getHeader(SecurityConstants.HEADER_STRING), null);
		
	}
	
	/*
	 * méthode Rest qui permet de change le mot de passe  d'un utilisateur
	 */
	@PreAuthorize("hasAuthority('user')")
	@RequestMapping(value="/changePassword",method=RequestMethod.POST)
	public @ResponseBody ResponseDto changePasswordRestService(@RequestBody RequestDto req) throws Exception{
		userService.updatePassword(req.getUserName(),req.getActuelPwd(),req.getNewPwd());
		return   new ResponseDto(httpServletResponse.getHeader(SecurityConstants.HEADER_STRING), null);
		
	}
	
	@PreAuthorize("hasAuthority('users')")
	@RequestMapping(value="/addGroupeToUser",method= RequestMethod.POST)
	public @ResponseBody ResponseDto addGroupeToUser(@RequestBody UserDto UserDto) throws Exception {
		userService.addGroupeToUser(UserDto.getGroupe(),UserDto.getIdUser());
		return   new ResponseDto(httpServletResponse.getHeader(SecurityConstants.HEADER_STRING),null);
	}
	
	@PreAuthorize("hasAuthority('users')")
	@RequestMapping(value="/removeGroupeToUser",method= RequestMethod.POST)
	public @ResponseBody ResponseDto removeGroupeToUser(@RequestBody UserDto UserDto) throws Exception {
		userService.removeGroupeToUser(UserDto.getGroupe(),UserDto.getIdUser());
		return   new ResponseDto(httpServletResponse.getHeader(SecurityConstants.HEADER_STRING),null);
	}
	@PreAuthorize("hasAuthority('users')")
	@GetMapping("/getGroupeToUser/{username}")
	public ResponseDto getGroupeToUser(@PathVariable String username) throws Exception {
		   Set<Groupe>	bankilies = userService.getGroupeToUser(username);
			return   new ResponseDto(httpServletResponse.getHeader(SecurityConstants.HEADER_STRING), bankilies);
		}
	
}
