package view; 

import java.sql.SQLException; 
import java.util.Scanner; 
import control.ManipularPessoa;  

public class TelaPrincipal { 
	private Scanner in = new Scanner(System.in); 
	
	public static void main(String args[]) throws SQLException { 
		TelaPrincipal telaPrincipal = new TelaPrincipal(); 
		telaPrincipal.menuInicial(); 
		} 
	
	public void menuInicial() throws SQLException { 
		String resposta; 
		do { 
			System.out .println("****************************Agenda Telefonica 0.0.1****************************"); 
			System.out.println("0-Sair"); 
			System.out.println("1-Pessoas"); 
			System.out.println(":"); 
			resposta = in.next(); 
			
			if (resposta.trim().equals("1")) manipularPessoas(); 
			} while (!resposta.trim().equals("0")); 
		} 
	
	private void manipularPessoas() throws SQLException { 
		ManipularPessoa manipularPessoa = new ManipularPessoa(); 
		manipularPessoa.iniciarManipulacao(); 
		} 
	}
