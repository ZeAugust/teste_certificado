package com.teste_certificado.certificado;

import java.io.File;
import java.io.IOException;

public class Main {

	public static void main(String[] args) throws InterruptedException {
		//Variaveis para a JKS
		
		String keystoretype = RetornaParametros.getKey("keystoretype");
		String keystore = RetornaParametros.getKey("keystore");
		String keystorepassword = RetornaParametros.getKey("keystorepassword");
		String commonName = RetornaParametros.getKey("commonName");
		String organizationUnit = RetornaParametros.getKey("organizationUnit");
		String organizationName = RetornaParametros.getKey("organizationName");
		String localityName = RetornaParametros.getKey("localityName");
		String stateName = RetornaParametros.getKey("stateName");
		String country = RetornaParametros.getKey("country");
		
		// Gerando o keystore 
		
		ProcessBuilder pb = new ProcessBuilder();
		ProcessBuilder pb2 = new ProcessBuilder();
		
		pb.command("bash", "-c", "keytool -genkey "
				+ "-alias javakeystore "
				+ "-keyalg RSA "
				+ "-keystore " + keystore +"." + keystoretype
				+ " -keysize 2048 "
				+ "-storepass " + keystorepassword 
				+ " -keypass " + keystorepassword
				+ " -dname \"CN=" +commonName+","
				+ " OU="+organizationUnit+", "
				+ "O="+organizationName+", "
				+ "L="+localityName+", "
				+ "ST="+stateName+", "
				+ "C="+country+"\"")
					.directory(new File("../com.teste_certificado/src/certficado"));

		try {
				Process process = pb.start();

				process.waitFor();
			
		} catch(IOException e){
			e.printStackTrace();
		}
	
		//Gernado a solicitacao de certificado
		
		pb2.command("bash", "-c", "keytool -certreq "
				+ "-alias javakeystore "
				+ "-keystore " + keystore +"." + keystoretype
				+ " -storepass " + keystorepassword 
				+ " -keypass " + keystorepassword
				+ " -file certificado.csr")
		.directory(new File("../com.teste_certificado/src/certficado"));
		
		
		try {
			Process process2 = pb2.start();
			
			process2.waitFor();
			
		} catch(IOException e){
			e.printStackTrace();
		}
	}

}
