package com.monetique.security.securityDispatcher;


public class SecurityConstants {
	
	public static final String SECRET="s.moulaye@bpm.mr";
	//public static final long EXPIRATION_TIME=864_000_000; //10jours
    public static final long EXPIRATION_TIME=1800_000;  //30 minutes
	//public static final long EXPIRATION_TIME=1_000;  //10 minutes
	public static final String TOKEN_PREFIX="Bearer ";
	public static final String HEADER_STRING="Authorization";

	
}
