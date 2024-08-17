package gksbr.aula_injecao_dependencia;

import java.util.List;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@SpringBootApplication
public class AulaInjecaoDependenciaApplication {

	public static void main(String[] args) {
		SpringApplication.run(AulaInjecaoDependenciaApplication.class, args);
	}

	@Bean
	ApplicationRunner runner(MigracaoUsuario migracaoUsuario) {
		return args -> {
			migracaoUsuario.migrar();
			//!!! o Spring Boot instancia automaticamente FileReader e BdReader, por eles serem components
		};
	}
}

@Component

class MigracaoUsuario {
	private Reader<User> reader;
	private Writer<User> writer;

	MigracaoUsuario(Reader<User> r, Writer<User> w) {
		this.reader = r;
		this.writer = w;
	}

	void migrar() {

		List<User> users = reader.read();
		writer.write(users);

	}
}

interface Reader<T> {
	List<T> read();
}

interface Writer<T> {
	void write(List<T> itens);
}

record User(String email, String username, String password) {

}

@Component

class FileReader implements Reader<User> {

	public List<User> read() {

		return List.of();
	}
}

@Component

class BdWriter implements Writer<User> {
	@Override
	public void write(List<User> users) {
		System.out.println();
		System.out.println("Escrevendo usuários no banco...");
		System.out.println(users);
	}
}