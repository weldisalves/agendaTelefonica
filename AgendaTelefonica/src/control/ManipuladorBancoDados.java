package control; 

import java.sql.Connection; 
import java.sql.DriverManager; 
import java.sql.PreparedStatement; 
import java.sql.ResultSet; 
import java.sql.SQLException; 
import java.util.ArrayList; 
import java.util.List; 
import model.Pessoa; 
import model.Telefone;

public class ManipuladorBancoDados { 
	private final String SELECT_TELEFONE = "SELECT * FROM TELEFONE WHERE TEL_NUMERO = ?"; 
	private final String INSERT_PESSOA = "INSERT INTO PESSOAS (PES_ID,PES_NOME) VALUES (?,?)"; 
	private final String SELECT_ALL_PESSOA = "SELECT * FROM PESSOAS PES, TELEFONES TEL WHERE PES.PES_ID=TEL.PES_ID"; 
	private final String INSERT_TELEFONE = "INSERT INTO TELEFONES (TEL_ID,TEL_NUMERO,PES_ID) values ((SELECT NEXTVAL('SEQUENCE_ID')),?,?)"; 
	private final String DELETE_PESSOA = "DELETE FROM telefones WHERE pes_id = ?;DELETE FROM pessoas WHERE pes_id = ?;"; private final String SELECT_NEXT_SEQUENCE = "SELECT NEXTVAL('SEQUENCE_ID')"; 
	private boolean retorno = false;
	
	/**
	 *jdbc://tipo_banco://host/nome_banco?user=usuario&password=password
	 * tipo_banco – Tipo de banco de dados que está sendo utilizado (postgresql/mysql/oracle).
		nome_banco – Banco de dados que iremos utilizar.
		user - Usuário utilizado para conectar ao banco e dados.
		password – Senha do usuário para conectar ao banco de dados.

	 * @return
	 * @throws SQLException
	 */
	
	//jdbc:mysql://localhost/agenda_telefone?user=postgres&password=postgres
	
	public Connection getConnection() throws SQLException { 
		Connection con = null; 
				
		con = DriverManager .getConnection("jdbc:mysql://localhost/agendaTelefonica?user=root&password=valhala2"); 
		return con; 
		} 
	
	public void closeConnnection(Connection con) { 
		try { 
			con.close(); 
			} catch (SQLException e) { 
				e.printStackTrace(); } 
		} 
	
	public boolean insertPessoa(Pessoa pessoa) { 
		Connection con = null; 
		try { 
			con = getConnection(); 
			PreparedStatement prepared = con.prepareStatement(INSERT_PESSOA); 
			prepared.setLong(1, pessoa.getId()); 
			prepared.setString(2, pessoa.getNome()); 
			retorno = prepared.execute(); 
			} catch (SQLException e) { 
				e.printStackTrace(); 
				} 
		finally { 
			closeConnnection(con); 
			} 
		return retorno; 
		} 
	
	public boolean removePessoa(Pessoa pessoa) throws SQLException { 
		Connection con = null;
		try {
			con = getConnection(); 
			PreparedStatement prepared = con.prepareStatement(DELETE_PESSOA); 
			prepared.setLong(1, pessoa.getId()); 
			prepared.setLong(2, pessoa.getId()); 
			retorno = prepared.execute(); 
			} catch (SQLException e) { 
				e.printStackTrace(); 
				} 
		finally { 
			closeConnnection(con); 
			} 
		return retorno; 
		} 
	
	public List<Pessoa> selectPessoa(Pessoa pessoa) throws SQLException { 
		Connection con = null; 
		List<Pessoa> listPessoa = new ArrayList<Pessoa>(); 
		try { 
			con = getConnection(); 
			PreparedStatement prepared = con.prepareStatement(SELECT_ALL_PESSOA); 
			ResultSet resultSet = prepared.executeQuery(); 
			
			while (resultSet.next()) { 
				Pessoa pessoaTmp = new Pessoa();
				pessoaTmp.setId(resultSet.getLong("PES_ID"));
				pessoaTmp.setNome(resultSet.getString("PES_NOME")); 
				pessoaTmp.setTelefone(resultSet.getString("TEL_NUMERO")); 
				listPessoa.add(pessoaTmp); 
				} 
			} catch (SQLException e) {
				e.printStackTrace();
				} 
		finally { 
			closeConnnection(con); 
			} 
		return listPessoa; 
		} 
	
	public boolean insertTelefone(Telefone telefone) throws SQLException { 
		Connection con = null; try { 
			con = getConnection();
			PreparedStatement prepared = con.prepareStatement(INSERT_TELEFONE);
			prepared.setString(1, telefone.getNumero()); prepared.setLong(2, telefone.getPessoa().getId());
			retorno = prepared.execute();
			} catch (SQLException e) { 
				e.printStackTrace(); 
				} 
		finally { 
			closeConnnection(con); 
			} 
		return retorno; 
		} 
	
	public long getId() throws SQLException { 
		Connection con = null; 
		long id = 0; 
		try { 
			con = getConnection(); 
			PreparedStatement prepared = con .prepareStatement(SELECT_NEXT_SEQUENCE);
			ResultSet resultSet = prepared.executeQuery();
			while (resultSet.next()) id = resultSet.getLong("nextval"); 
			} catch (SQLException e) { 
				e.printStackTrace(); 
				} 
		finally { 
			closeConnnection(con); 
			} 
		return id; 
		} 
	
	public List<Telefone> selectTelefones(Telefone telefone) throws SQLException { 
		Connection con = null; 
		List<Telefone> listTelefone = new ArrayList<Telefone>(); 
		try { 
			con = getConnection(); 
			PreparedStatement prepared = con.prepareStatement(SELECT_TELEFONE); 
			prepared.setString(1, telefone.getNumero()); 
			ResultSet resultSet = prepared.executeQuery(); 
			while (resultSet.next()) { 
				Telefone telefoneTmp = new Telefone(); 
				telefoneTmp.setId(resultSet.getLong("TEL_ID")); 
				telefoneTmp.setNumero(resultSet.getString("TEL_NUMERO")); 
				listTelefone.add(telefoneTmp);
				}
			} catch (SQLException e) { 
				e.printStackTrace(); 
				} 
		finally { 
			closeConnnection(con); 
			} 
		return listTelefone; 
		} 
	}
