package com.teste_certificado.certificado;

import java.util.ResourceBundle;

public class RetornaParametros {
	
	private static final ResourceBundle PARAM_CERT = ResourceBundle.getBundle("com.teste_certificado.certificado.parametros_certificado");
	
	public static String getKey(String key) {
		if(PARAM_CERT.containsKey(key)) {
			return PARAM_CERT.getString(key);
		}
		
		return null;
	}

	
}
