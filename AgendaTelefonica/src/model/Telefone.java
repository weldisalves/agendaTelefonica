package model; 

public class Telefone { 
	private long id; 
	private Pessoa pessoa; 
	private String numero; 
	
	public long getId() { 
		return id; 
		} 
	
	public void setId(long id) { 
		this.id = id; 
		} 
	
	public Pessoa getPessoa() { 
		return pessoa; 
		} 
	
	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa; 
		} 
	
	public String getNumero() { 
		return numero; 
		} 
	
	public void setNumero(String numero) { 
		this.numero = numero; 
		} 
	}
