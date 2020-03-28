package com.milkpointapi.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.milkpointapi.model.Deposito;
import com.milkpointapi.model.Retirada;

@Service
public class SmsService {
	private String uri = "https://semysms.net:443/api/3/sms.php?token=";
	private String token = "395e1bcb383e0cf3d306f5828a57cf5a";
	
	public static void request(String uri) throws Exception {
			URL obj = new URL(uri);
			HttpURLConnection con = (HttpURLConnection) obj.openConnection();
			con.setRequestMethod("GET");
			//con.setRequestProperty("User-Agent", "Mozilla/5.0");
			//int responseCode = con.getResponseCode();
			Map<String, List<String>> map = con.getHeaderFields();
			//System.out.println("\nSending 'GET' request to URL : " + uri);
			//System.out.println("Response Code : " + responseCode);
			BufferedReader in = new BufferedReader(
					new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}	
			in.close();
        
			for (Map.Entry<String, List<String>> entry : map.entrySet()) {
				System.out.println("Key : " + entry.getKey() 
		                           + " ,Value : " + entry.getValue());
			}
	}
		
	public void send(Deposito deposito) {
		String telefone = deposito.getTanque().getResponsavel().getTelefone();
		String tanque = deposito.getTanque().getNome();
		String produtor = deposito.getProdutor().getNome();
		float quantidade = deposito.getQuantidade();
		boolean confirmado = deposito.getConfirmacao();
		boolean cancelado = deposito.getExcluido();
		String mensagem = "DEPÓSITO de "+quantidade+" litros do Produtor "+produtor+", para o tanque "+tanque;
		if(!confirmado && !cancelado) {
			mensagem = "Nova solicitação de "+mensagem; 
		} else if(confirmado && !cancelado) {
			mensagem = mensagem+" foi CONFIRMADO";
		} else if(cancelado) {
			mensagem =  mensagem+" foi CANCELADO";
		};
		mensagem = "MILKPOINT INFORMA: "+mensagem;
		uri = uri+token+"&device=active&phone="+telefone+"&msg="+mensagem;
		try {
			request(uri);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void send(Retirada retirada) {
		String telefone = retirada.getTanque().getResponsavel().getTelefone();
		String tanque = retirada.getTanque().getNome();
		String laticinio = retirada.getLaticinio().getNome();
		float quantidade = retirada.getQuantidade();
		boolean confirmado = retirada.getConfirmacao();
		boolean cancelado = retirada.getExcluido();
		String mensagem = "RETIRADA de "+quantidade+" litros do Laticínio  "+laticinio+", para o tanque "+tanque;
		if(!confirmado && !cancelado) {
			mensagem = "Nova solicitação de "+mensagem; 
		} else if(confirmado && !cancelado) {
			mensagem = mensagem+" foi CONFIRMADA";
		} else if(cancelado) {
			mensagem =  mensagem+" foi CANCELADA";
		};
		mensagem = "MILKPOINT INFORMA: "+mensagem;
		uri = uri+token+"&device=active&phone="+telefone+"&msg="+mensagem;
		try {
			request(uri);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
