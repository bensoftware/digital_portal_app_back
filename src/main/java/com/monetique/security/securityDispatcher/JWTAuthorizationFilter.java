package com.monetique.security.securityDispatcher;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JWTAuthorizationFilter extends OncePerRequestFilter{

	
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.addHeader("Access-Control-Allow-Origin","*");
		response.addHeader("Access-Control-Allow-Headers","Origin, Accept, X-Requested-With, Content-Type, Access-Control-Request-Method, Access-Control-Request-Headers,authorization");
		response.addHeader("Access-Control-Request-Method","*");
		response.addHeader("Access-Control-Expose-Headers","Access-Control-Allow-Origin,Access-Control-Allow-Credentials, authorization");
		
		
		// OPTIONS requete envoyé par le client pour connaitre les entetes autorisées s'il est dans un autre domaine
		if(request.getMethod().equals("OPTIONS")) {
			// la 1er requete cliente de type OPTION pour pour connaitre les entete autorisé 
			response.setStatus(HttpServletResponse.SC_OK);
		}else {
		
			String jwt=request.getHeader(SecurityConstants.HEADER_STRING);
			
			if(jwt==null || !jwt.startsWith(SecurityConstants.TOKEN_PREFIX ) || request.getRequestURI().equals("/login")  ) {
				// il passe au filtre suivant gerer par spring sec
				filterChain.doFilter(request, response);
			    // sortir du filtre et ne pas executer le reste du fitre apres la fin du filtre
				// suivant, doFilter.
				return;
				
			}
			
			Claims claims=Jwts.parser()
					.setSigningKey(SecurityConstants.SECRET)
					.parseClaimsJws(jwt.replace(SecurityConstants.TOKEN_PREFIX,""))
					.getBody();
			
			String username=claims.getSubject();
			
			//System.out.println("****** date expiration "+claims.getExpiration());
			//Date expiration= claims.getExpiration();
			claims.setExpiration(new Date(System.currentTimeMillis()+SecurityConstants.EXPIRATION_TIME));

			ArrayList<Map<String, String>> actions =(ArrayList<Map<String, String>>) claims.get("actions");
			
			
			Collection<GrantedAuthority> authorities=new ArrayList<>();
			actions.forEach(r->{
				authorities.add(new SimpleGrantedAuthority(r.get("authority")));
			});
			
			authorities.add(new SimpleGrantedAuthority("user"));
			
			UsernamePasswordAuthenticationToken authenticationUser=new UsernamePasswordAuthenticationToken(username,null,authorities);
			
			// update de context spring: l'utilisateur est authentifié
			SecurityContextHolder.getContext().setAuthentication(authenticationUser);
			
			// send jwt updated		
			// if JWT expired dont send update
			String new_jwt=null;
			try {
				new_jwt = Jwts.builder()
						.setSubject(username)
						.setExpiration(new Date(System.currentTimeMillis()+SecurityConstants.EXPIRATION_TIME))
						.claim("roles",claims.get("roles"))
						.claim("actions", claims.get("actions"))
						.signWith(SignatureAlgorithm.HS256,SecurityConstants.SECRET)
						.compact();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			response.addHeader(SecurityConstants.HEADER_STRING,new_jwt);	
			
			filterChain.doFilter(request, response);

		}
		
				
	}

}
