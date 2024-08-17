package gksbr.aula_injecao_dependencia;

import java.util.List;


public class AulaInjecaoDependenciaApplication {

	public static void main(String[] args) {
			new MigracaoUsuario().migrar();
	}
}

//Problemas de MigracaoUsuario:

//1- As dependências estão sendo criadas diretamente dentro dela, em vez de serem injetadas (via setters ou contrutor). Viola o principio da inversao de controle
//2- Acoplamento forte: se futuramente quiser usar essa classe para ler uma api em vez de um arquivo, precisaria modificá-la(retirar e colocar outras linhas) sem extendê-la(usar overrides, interfaces, etc). Ou seja, essa classe viola o principio OPEN CLOSED do SOLID. Uma opcao para resolver seria usando uma interface leitora que é implementada pelo FileReader e por um possível leitor de APIs
//3- Essa classe instancia objetos, lê os dados e os grava. Viola o principio da responsabilidade única do SOLID
class MigracaoUsuario {
	FileReader reader = new FileReader();
	BdWriter writer = new BdWriter();

	void migrar() {
		// ler usuarios de A
		List<User> users = reader.read();

		// escrever para B
		writer.write(users);

	}
}

record User(String email, String username, String password)
{

}

class FileReader {
	List<User> read() {

		return List.of();
	}
}

class BdWriter{
	void write(List<User> users)
	{
		System.out.println("Escrevendo usuários no banco...");
		System.out.println(users);
	}
}