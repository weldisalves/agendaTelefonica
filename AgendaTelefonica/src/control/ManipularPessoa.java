package control;

import java.sql.SQLException; 
import java.util.ArrayList; 
import java.util.List; 
import java.util.Scanner; 
import model.Pessoa; 
import model.Telefone;

public class ManipularPessoa { 
	private Scanner in = new Scanner(System.in); 
	
	public void iniciarManipulacao() throws SQLException {
		String resposta; 
		do { 
			System.out .println("****************************Pessoa 0.0.1****************************"); 
			System.out.println("0-Voltar"); 
			System.out.println("1-Inserir"); 
			System.out.println("2-Deletar"); 
			System.out.println("3-Listar"); 
			System.out.println(":"); 
			resposta = in.next(); 
			
			if (resposta.trim().equals("1")) inserirPessoa(); 
			else if (resposta.trim().equals("2")) deletarPessoas(); 
			else if (resposta.trim().equals("3")) listarPessoas(); 
			} while (!resposta.trim().equals("0")); 
		} 
	
	private void listarPessoas() throws SQLException { 
		List<Pessoa> listaPessoa = new ArrayList<Pessoa>(); 
		ManipuladorBancoDados consultaBancoDados = new ManipuladorBancoDados(); 
		listaPessoa = consultaBancoDados.selectPessoa(null); 
		System.out .println("______________________________________________________________________"); 
		System.out.println("ID----NOME----TELEFONE"); 
		
		for (int i = 0; i < listaPessoa.size(); i++) 
			System.out.println(listaPessoa.get(i).getId() +
			" - " + listaPessoa.get(i).getNome() + 
			" - " + listaPessoa.get(i).getTelefone()); 
		
		System.out .println("______________________________________________________________________"); 
		} 
	
	private void deletarPessoas() throws SQLException { 
		ManipuladorBancoDados consultaBancoDados = new ManipuladorBancoDados(); 
		Pessoa pessoa = new Pessoa(); 
		long id; 
		boolean isDeletado; 
		listarPessoas(); 
		System.out.println("Pessoa a ser deletada (ID): "); 
		
		id = in.nextLong(); 
		pessoa.setId(id); 
		isDeletado = consultaBancoDados.removePessoa(pessoa); 
		
		if (isDeletado) System.out.println("Item deletado com sucesso!"); 
		} 
	
	private void inserirPessoa() throws SQLException { 
		ManipuladorBancoDados consultaBancoDados = new ManipuladorBancoDados(); 
		String nome; String numeroTelefone; 
		long id = consultaBancoDados.getId(); 
		
		System.out .println("______________________________________________________________________"); 
		System.out.println("Nome: ");
		nome = in.next();
		System.out.println("Telefone: ");
		numeroTelefone = in.next();
		Pessoa pessoa = new Pessoa();
		pessoa.setNome(nome); 
		pessoa.setId(id);
		Telefone telefone = new Telefone(); 
		telefone.setNumero(numeroTelefone); 
		telefone.setPessoa(pessoa); 
		boolean isPessoaInserido = consultaBancoDados.insertPessoa(pessoa); 
		boolean isTelefoneInserido = consultaBancoDados .insertTelefone(telefone);
		if (isPessoaInserido && isTelefoneInserido) System.out.println("Item inserido com sucesso!");
		listarPessoas(); 
	} 
}
