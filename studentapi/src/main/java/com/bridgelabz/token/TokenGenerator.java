package com.bridgelabz.token;

import java.sql.Date;
import javax.xml.bind.DatatypeConverter;
import org.springframework.stereotype.Service;
import com.bridgelabz.model.Student;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * @author administrator
 *
 */
@Service
public class TokenGenerator {

	final static String KEY = "Vishal";

	@SuppressWarnings("unused")
	public String generator(Student student) {
		String email = student.getEmail();
		String passkey = student.getPassword();
		long time = System.currentTimeMillis();
		long nowMillis = System.currentTimeMillis() + (24 * 60 * 60 * 1000);
		Date now = new Date(nowMillis);
		JwtBuilder builder = Jwts.builder().setId(passkey).setIssuedAt(now).setSubject(email)
				.signWith(SignatureAlgorithm.HS256, KEY);
		return builder.compact();
	}

	public void parseJWT(String jwt) {

		Claims claims = Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary(KEY)).parseClaimsJws(jwt)
				.getBody();
		System.out.println("ID: " + claims.getId());
		System.out.println("Subject: " + claims.getSubject());
	}
}
