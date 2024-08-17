package gksbr.aula_injecao_dependencia;


import java.util.List;


public class AulaInjecaoDependenciaApplication {

	public static void main(String[] args) {
			MigracaoUsuario migracao = new MigracaoUsuario(new FileReader(), new BdWriter());
			migracao.migrar();
	}
}

//

class MigracaoUsuario {
	private Reader<User> reader;
	private Writer<User> writer;

	MigracaoUsuario(Reader<User> r, Writer<User> w)
	{
		this.reader = r;
		this.writer = w;
	}

	void migrar() {

		List<User> users = reader.read();
		writer.write(users);

	}
}

interface Reader <T>{
	List<T> read();
}

interface Writer <T>{
	void write(List<T> itens);
}

record User(String email, String username, String password)
{

}

class FileReader implements Reader<User> {

	
	public List<User> read() {

		return List.of();
	}
}

class BdWriter implements Writer <User>{
	@Override
	public void write(List<User> users)
	{
		System.out.println();
		System.out.println("Escrevendo usuários no banco...");
		System.out.println(users);
	}
}