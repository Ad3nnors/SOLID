package br.ufscar.dc.dsw;

import javax.mail.internet.InternetAddress;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.thymeleaf.extras.java8time.dialect.Java8TimeDialect;
import org.thymeleaf.spring5.ISpringTemplateEngine;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.templateresolver.ITemplateResolver;

import br.ufscar.dc.dsw.dao.*;
import br.ufscar.dc.dsw.domain.*;

@SpringBootApplication
public class SistemaAgendamentoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SistemaAgendamentoApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(IClientesDAO clienteDAO, BCryptPasswordEncoder encoder, IProfissionaisDAO profissionalDAO, IPessoaDAO pessoaDAO,
			IConsultasDAO consultaDAO) {
		return (args) -> {
			
			Pessoa ad1 = new Pessoa();
			ad1.setCpf("123.321.123-44");
			ad1.setNome("Administrador");
			ad1.setEmail("admin");
			ad1.setSenha(encoder.encode("admin"));
			ad1.setRole("ROLE_ADMIN");
			pessoaDAO.save(ad1);
			
			Clientes c1 = new Clientes();
			c1.setCpf("111.111.111-11");
			c1.setNome("Cliente Generico");
			c1.setEmail("cli@cli.com");
			c1.setSenha(encoder.encode("1234"));
			c1.setSexo("n");
			c1.setTelefone("11912345698");
			c1.setNascimento("1990-11-11");
			c1.setRole("ROLE_CLIENTE");
			clienteDAO.save(c1);

			Clientes c2 = new Clientes();
			c2.setCpf("111.222.333-44");
			c2.setNome("Luciana Souza Cliente");
			c2.setEmail("luciana.losg@gmail.com");
			c2.setSenha(encoder.encode("1234"));
			c2.setSexo("f");
			c2.setTelefone("11912345698");
			c2.setNascimento("1990-11-11");
			c2.setRole("ROLE_CLIENTE");
			clienteDAO.save(c2);

			Clientes c3 = new Clientes();
			c3.setCpf("222.333.444-55");
			c3.setNome("Jean Araujo Cliente");
			c3.setEmail("jean.araujo2008@hotmail.com");
			c3.setSenha(encoder.encode("1234"));
			c3.setSexo("m");
			c3.setTelefone("11912345698");
			c3.setNascimento("1990-11-11");
			c3.setRole("ROLE_CLIENTE");
			clienteDAO.save(c3);

			Clientes c4 = new Clientes();
			c4.setCpf("333.444.555-66");
			c4.setNome("Anderson Henrique Cliente");
			c4.setEmail("trab.web.ufscar@gmail.com");
			c4.setSenha(encoder.encode("1234"));
			c4.setSexo("m");
			c4.setTelefone("11912345698");
			c4.setNascimento("1990-11-11");
			c4.setRole("ROLE_CLIENTE");
			clienteDAO.save(c4);

			Profissionais p1 = new Profissionais();
			p1.setCpf("911.111.111-11");
			p1.setNome("Profissional Generico");
			p1.setEmail("pro@pro.com");
			p1.setSenha(encoder.encode("1234"));
			p1.setArea("medicina");
			p1.setEspecialidade("cardiologista");
			p1.setRole("ROLE_PROFISSIONAL");
			profissionalDAO.save(p1);

			Profissionais p2 = new Profissionais();
			p2.setCpf("911.222.333-44");
			p2.setNome("Luciana Souza");
			p2.setEmail("luciana.souza@estudante.ufscar.br");
			p2.setSenha(encoder.encode("1234"));
			p2.setArea("medicina");
			p2.setEspecialidade("psicologia");
			p2.setRole("ROLE_PROFISSIONAL");
			profissionalDAO.save(p2);

			Profissionais p3 = new Profissionais();
			p3.setCpf("922.333.444-55");
			p3.setNome("Jean Araujo");
			p3.setEmail("jean.araujo@estudante.ufscar.br");
			p3.setSenha(encoder.encode("1234"));
			p3.setArea("computacao");
			p3.setEspecialidade("engenharia de dados");
			p3.setRole("ROLE_PROFISSIONAL");
			profissionalDAO.save(p3);

			Profissionais p4 = new Profissionais();
			p4.setCpf("933.444.555-66");
			p4.setNome("Anderson Henrique");
			p4.setEmail("agiacomini@estudante.ufscar.br");
			p4.setSenha(encoder.encode("1234"));
			p4.setArea("engenharia");
			p4.setEspecialidade("mecânica");
			p4.setRole("ROLE_PROFISSIONAL");
			profissionalDAO.save(p4);

			Consultas a1 = new Consultas();
			a1.setDataHora("2022-02-10T14:00");
			a1.setCliente(c1);
			a1.setProfissional(p2);
			consultaDAO.save(a1);

			Consultas a2 = new Consultas();
			a2.setDataHora("2022-03-10T15:00");
			a2.setCliente(c2);
			a2.setProfissional(p3);
			consultaDAO.save(a2);

			Consultas a3 = new Consultas();
			a3.setDataHora("2022-04-10T16:00");
			a3.setCliente(c3);
			a3.setProfissional(p1);
			consultaDAO.save(a3);
			
		};
	}
	
	@Bean
	public CommandLineRunner run(EmailService service) {
		return (args) -> {
			
			InternetAddress from = new InternetAddress("trab.web.ufscar@gmail.com", "Equipe de Atendimento");
		    InternetAddress to = new InternetAddress("trab.web.ufscar@gmail.com", "Cliente");
					
			String subject1 = "Dados da Sua Consulta - Web1 Sistema de Agendamento";
			String body1 = "Olá,\n"
					+ "Sua consulta foi marcada com sucesso!\n"
					+ "O link de acesso é: https://meet.google.com/keq-xpus-jen \n "
					+ "Acesse o seu login em nossa plataforma para consultar a data e horário!\n\n"
					+ "Atenciosamente,\n"
					+ "Equipe de Atendimento do Sistema de Agendamento de Consultas";
			

			// Envio sem anexo
			service.send(from, to, subject1, body1);
			
			// Envio com anexo
			//service.send(from, to, subject2, body2, new File("SIGA.pdf"));
		};
	}
	
	private ISpringTemplateEngine templateEngine(ITemplateResolver templateResolver) {
	    SpringTemplateEngine engine = new SpringTemplateEngine();
	    engine.addDialect(new Java8TimeDialect());
	    engine.setTemplateResolver(templateResolver);
	    return engine;
	}
}
