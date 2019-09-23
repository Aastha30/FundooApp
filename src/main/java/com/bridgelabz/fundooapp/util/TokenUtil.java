package com.bridgelabz.fundooapp.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.auth0.jwt.interfaces.Verification;


public class TokenUtil {

	private static String TOKEN_SECRET = "dfhdgffdgjdfsdfsd";

	public static String generateToken(Long id) {
		Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
		return JWT.create().withClaim("ID", id).sign(algorithm);
	}

	public static Long verifyToken(String token) {

		Long id;
		Verification verification = JWT.require(Algorithm.HMAC256(TOKEN_SECRET));
		JWTVerifier jwtVerifier = verification.build();
		DecodedJWT decodedJWT = jwtVerifier.verify(token);
		Claim claim = decodedJWT.getClaim("ID");
		id = claim.asLong();
		return id;

	}

}