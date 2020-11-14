package com.milkpointapi.controller;

import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.Principal;
import java.util.Date;
import java.util.Formatter;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.milkpointapi.model.*;
import com.milkpointapi.repository.*;
import com.milkpointapi.service.*;
import com.milkpointapi.utils.PasswordUtil;

@Controller
public class IndexController {

	@Autowired
	private SessionService<UserInfo> sessionService;
	
	@Autowired 
	private JavaMailSender mailSender;
	
	@Autowired
	private UserService userService;
	
	private TecnicoService tecnicoService;
	@Autowired
	private LaticinioService laticinioService;
	@Autowired
	private ProdutorService produtorService;
	@Autowired
	private ResponsavelService responsavelService;
	
	@Autowired
	private TecnicoRepository tecnicoRepository;
	@Autowired
	private LaticinioRepository laticinioRepository;
	@Autowired
	private ProdutorRepository produtorRepository;
	@Autowired
	private ResponsavelRepository responsavelRepository;
	
	@GetMapping("/")
	public String index(Model model) {
	
		model.addAttribute("user", new UserInfo());
		return "home";
	}
	
	@GetMapping("/login")
	public String login(Model model) {
		model.addAttribute("user", new UserInfo());
		return "login";
	}
	
	@RequestMapping("/home")
	public String home() {
		return "home";
	}
	
	@GetMapping("/guiaapi")
	public String guiaApi(Model model) {
		return "guiadaapi/guiaapi";
	}
	
	@GetMapping("/register")
	public String register(Model model) {
		model.addAttribute("user", new UserInfo());
		return "admin/register";
	}

	@GetMapping("/loginfailure")
	public String loginfailure(ModelMap model) {
		model.addAttribute("user", new UserInfo());
		model.addAttribute("alerta", "erro");
		model.addAttribute("texto", "Login ou senha incorretos, tente novamente.");
		return "login";
	}

	@GetMapping("/logout")
	public String logout() {
		sessionService.clearSession();
		return "login";
	}
	
	@GetMapping("/recovery")
	public String recoveryPass1() {
		sessionService.clearSession();
		return "recovery";
	}
	
	@PostMapping("/recovery2")
    public ModelAndView recoveryPass2(String email, String type) {
		ModelAndView mv = new ModelAndView("/recovery");
		String senha = geradorDeSenha(8);
		boolean finded = false;
		switch(type) { 
			case "admin": {
				UserInfo user = userService.findByEmail(email);
				if(user!=null) {
					user.setPassword(senha);
					userService.save(user);
					finded = true;
				}
			}
			case "tecnico": {
				Tecnico tecnico = tecnicoRepository.findByEmailIgnoreCaseContaining(email);
				if(tecnico !=null) {
					tecnico .setPassword(senha);
					tecnicoRepository.save(tecnico);
					finded = true;
				}
			}
			case "laticinio": {
				Laticinio laticinio = laticinioRepository.findByEmailIgnoreCaseContaining(email);
				if(laticinio !=null) {
					laticinio .setPassword(senha);
					laticinioRepository.save(laticinio);
					finded = true;
				}
			}
			case "produtor": {
				Produtor produtor = produtorRepository.findByEmailIgnoreCaseContaining(email);
				if(produtor !=null) {
					produtor .setPassword(senha);
					produtorRepository.save(produtor);
					finded = true;
				}
			}
			case "responsavel": {
				Responsavel responsavel = responsavelRepository.findByEmailIgnoreCaseContaining(email);
				if(responsavel !=null) {
					responsavel .setPassword(senha);
					responsavelRepository.save(responsavel);
					finded = true;
				}
			}
			
		}
		
		if(finded) {
			try {
	        	sendEmail(
    				"milkpoint@serviceapp.net.br",
    				email,
    				"Recuperação de senha MilkPoint",
    				"Esse é um email de recuperação de senha do Milk Point. \n Sua senha temporária é: "+senha+"\n\nEquipe Milk Point"
		        );
		        mv = new ModelAndView("/login");
    			mv.addObject("alerta", "success")
        				.addObject("texto", "Foi enviado um e-mail para "+email+" com instruções para recuperar sua senha.");
        
	        } catch (Exception e) {
	            e.printStackTrace();
	            mv.addObject("alerta", "erro")
						.addObject("texto", "Erro ao enviar e-mail.");
	        }
			
		} else {
			mv
				.addObject("user", new UserInfo())
				.addObject("alerta", "erro")
				.addObject("texto", "Usuário não localizado.");
        }
		return mv;
    }
	
	@PostMapping("/recovery")
	public ModelAndView recoveryPass3(String email, String type) throws Exception {
		ModelAndView mv = new ModelAndView("/login");
		String password = getPassword(type, email);
		long time = new Date().getTime();
		String hash = gerarHash(time+password);
		//String host = InetAddress.getLocalHost().getHostName();
		//String host = System.getenv("USERDOMAIN");
		String host = "milkpoint.serviceapp.net.br";
		String link = "https://"+host+"/recovery/"+type+"/"+email+"/"+time+"/"+hash;

		if(password != null) {
			try {
	        	sendEmail(
    				"milkpoint@serviceapp.net.br",
    				email,
    				"Redefinição de senha MilkPoint",
    				"Esse é um email de recuperação de senha do Milk Point Web. \n "
    				+"Para redefinição de senha acesse o link: "
    				+ link
					+ "\n\nEquipe Milk Point"
		        );
		        mv = new ModelAndView("/login");
    			mv.addObject("alerta", "success")
    				.addObject("texto", "Foi enviado um e-mail para "+email+" com instruções para recuperar sua senha.");

			} catch (Exception e) {
	            e.printStackTrace();
	            mv.addObject("alerta", "erro")
					.addObject("texto", "Erro ao enviar e-mail.");
	        }
			
		} else {
			mv
				.addObject("user", new UserInfo())
				.addObject("alerta", "erro")
				.addObject("texto", "Usuário não localizado.");
        }
		return mv;
	}
	
	@GetMapping("/recovery/{type}/{email}/{time}/{hash}")
	public ModelAndView recoveryPass4(
			@PathVariable String type, 
			@PathVariable String email,
			@PathVariable long time,
			@PathVariable String hash
		) throws Exception {
		ModelAndView mv = new ModelAndView("login");
		String password = getPassword(type, email);
		String check = gerarHash(time+password);
		int tempo = (int) ((new Date().getTime()-time)/1000/60);
		if(hash.equals(check) && tempo<=10) {
			mv = new ModelAndView("changepassword")
			.addObject("alerta", "default")
			.addObject("texto", "Redefinição de senha para "+email)
			.addObject("type", type)
			.addObject("email", email)
			.addObject("time", String.valueOf(time))
			.addObject("hash", hash);
		} else {
			mv
			.addObject("alerta", "erro")
			.addObject("texto", "Link de recuperação de senha expirou ou é invalido.");
		}
		return mv;
		
	}
	
	@PostMapping("/changepassword")
	public ModelAndView recoveryPass5(
			String type, 
			String email,
			long time,
			String hash,
			String newPassword
		) throws Exception {
		ModelAndView mv = new ModelAndView("login");
		String password = getPassword(type, email);
		String check = gerarHash(time+password);
		int tempo = (int) ((new Date().getTime()-time)/1000/60);
		if(hash.equals(check) && tempo<=10) {
			setPassword(type, email, newPassword);
			mv
			.addObject("alerta", "default")
			.addObject("texto", "Senha alterada com sucesso.");
		} else {
			mv
			.addObject("alerta", "erro")
			.addObject("texto", "Link de recuperação de senha expirou ou é invalido.");
		}
		return mv;
	}
	
	private static String geradorDeSenha(int length) {
        String base = "0123456789AaBbCcDdEeFfGgHhIiJjKkLlMmNnOoPpQqRrSsTtUuVvWwXxYyZz";
        Random gerador = new Random();
        String text = "";
        for (int i = 0; i < length; i++) {
        	int rand = gerador.nextInt()%base.length();
        	if( rand < 0 ) { rand = rand * (-1); }
            text += base.substring(rand,rand+1);
        }        
        return text;
    }
	
	private void sendEmail(
			String from, 
			String to, 
			String subject, 
			String text
		) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom(from);
        message.setTo(to);
        message.setSubject(subject);
    	message.setText(text);
    	mailSender.send(message);
	}
	
	public static String gerarHash(String senha) throws Exception {
		MessageDigest algorithm = MessageDigest.getInstance("SHA-256");
		byte hash[] = algorithm.digest(senha.getBytes("UTF-8"));
		StringBuilder texto = new StringBuilder();
		for (byte b : hash) {
			texto.append(String.format("%02X", 0xFF & b));
		}
		return texto.toString();
	}
	
	public String getPassword(String type, String email) {
		String password = null;
		switch(type) { 
		case "tecnico": 
			password = tecnicoRepository.findByEmailIgnoreCaseContaining(email).getPassword();
			break;
		case "laticinio": 
			password = laticinioRepository.findByEmailIgnoreCaseContaining(email).getPassword();
			break;
		case "produtor": 
			password = produtorRepository.findByEmailIgnoreCaseContaining(email).getPassword();
			break;
		case "responsavel": 
			password = responsavelRepository.findByEmailIgnoreCaseContaining(email).getPassword();
			break;
		default:
			password = userService.findByEmail(email).getPassword();
		}
		//if(password == null) {password = "123";}
		return password;
	}
	
	public void setPassword(String type, String email, String password) {
		
		switch(type) { 
		case "tecnico": 
			Tecnico tecnico = tecnicoRepository.findByEmailIgnoreCaseContaining(email);
			tecnico.setPassword(password);
			tecnicoService.save(tecnico);
;			break;
		case "laticinio": 
			Laticinio laticinio = laticinioRepository.findByEmailIgnoreCaseContaining(email);
			laticinio.setPassword(password);
			laticinioService.save(laticinio);
			break;
		case "produtor": 
			Produtor produtor = produtorRepository.findByEmailIgnoreCaseContaining(email);
			produtor.setPassword(password);
			produtorService.save(produtor);
			break;
		case "responsavel": 
			Responsavel responsavel = responsavelRepository.findByEmailIgnoreCaseContaining(email);
			responsavel.setPassword(password);
			responsavelService.save(responsavel);
			break;
		default:
			UserInfo user = userService.findByEmail(email);
			user.setPassword(password);
			userService.save(user);
		}
	}

}
