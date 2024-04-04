package br.com.api.coffee.repository;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.cj.jdbc.CallableStatement;

import br.com.api.coffee.model.Coffee;
import jakarta.validation.Valid;

public class CoffeeRepository extends Repository{
	
	public static List<Coffee> findAll(){
		
		String sql = " select * from cafes ";
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Coffee> cafes = new ArrayList<>();
		try {
			ps = getConnection().prepareStatement(sql);
			
			rs = ps.executeQuery();
			
			if(rs.isBeforeFirst()) {
				while(rs.next()) {
					Coffee cafe = new Coffee();
					cafe.setId(rs.getLong("id"));
					cafe.setNome(rs.getString("nome"));
					cafe.setPreco(rs.getDouble("preco"));
					cafe.setDataFabricacao(rs.getDate("data_fabricacao").toLocalDate());
					cafe.setDataValidade(rs.getDate("data_validade").toLocalDate());
					cafes.add(cafe);
				}
			}else {
				System.out.println("não há registro na tabela");
			}
			
		} catch (SQLException e) {
			System.out.println("Erro ao conectar " + e.getMessage());
			e.printStackTrace();
		}
		
		
		return cafes;
	}
	public static Coffee save(Coffee cafe) {
		String sql = " insert into cafes "
				+ " (id, nome, preco, data_fabricacao, data_validade) "
				+ " values(?,?,?,?,?) ";
		CallableStatement cs = null;
		try {
			cs = (CallableStatement) getConnection().prepareCall(sql);
			cs.setLong(1, cafe.getId());
			cs.setString(2, cafe.getNome());
			cs.setDouble(3, cafe.getPreco());
			cs.setDate(4, Date.valueOf(cafe.getDataFabricacao()));
			cs.setDate(5, Date.valueOf(cafe.getDataValidade()));
			
			
			cs.executeUpdate();
			
			return cafe;
		}catch(SQLException e ) {
			System.out.println("Erro para salvar ");
		}finally {
			if(cs!=null ) {
				try {
					cs.close();
				}
				catch(SQLException e) {
					System.out.println("Erro ao fechar a conexão");
				}
			}
		}
		
		return null;
	}
	
	public static boolean delete(Long cafeId) {
		Coffee cafe = null;
		String sql = " Delete from cafes where id = ? ";
		PreparedStatement ps = null;
		cafe = findById(cafeId);
		
		if(cafe == null) {
			return false;
		}

		try {
			ps = getConnection().prepareStatement(sql);
		
			ps.setLong(1, cafeId);
			
			ps.executeUpdate();
			return true;
		}catch(SQLException e) {
			System.out.println("Erro ao deletar " + e.getMessage());
		}finally {
			if(ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					
					e.printStackTrace();
				}
			}
		}
		return false;
	}
	
	public static Coffee findById(Long id) {
		String sql = " Select * from cafes where id = ? ";
		
		PreparedStatement ps = null;
		  ResultSet rs = null;
		  
		  try {
		    ps = getConnection().prepareStatement(sql);
		    ps.setLong(1, id);
		    rs = ps.executeQuery();
		    
		    if (rs.next()) {
		      Coffee cafe = new Coffee();
		      cafe.setId(rs.getLong("id"));
		      cafe.setNome(rs.getString("nome"));
		      cafe.setPreco(rs.getDouble("preco"));
		      cafe.setDataFabricacao(rs.getDate("data_fabricacao").toLocalDate());
		      cafe.setDataValidade(rs.getDate("data_validade").toLocalDate());
		      return cafe;
		    } else {
		      return null;
		    }
		  } catch (SQLException e) {
		    e.printStackTrace();
		  } finally {
		    try {
		      if (rs != null) {
		        rs.close();
		      }
		      if (ps != null) {
		        ps.close();
		      }
		    } catch (SQLException e) {
		      e.printStackTrace();
		    }
		  }
		  return null;
	}
	
	public static Coffee update(@Valid Coffee cafe) {
		String sql = " Update cafes set nome=?, preco=?, data_fabricacao=?, data_validade=? "
				+ " where id = ? ";
		
		CallableStatement cs = null;
		try {
			cs = (CallableStatement) getConnection().prepareCall(sql);
			cs.setString(1, cafe.getNome());
			cs.setDouble(2, cafe.getPreco());
			cs.setDate(3, Date.valueOf(cafe.getDataFabricacao()));
			cs.setDate(4, Date.valueOf(cafe.getDataValidade()));
			cs.setLong(5, cafe.getId());
			
			cs.executeUpdate();
			
			return cafe;
		}catch(SQLException e ) {
			System.out.println("Erro para salvar UPDATE");
		}finally {
			if(cs!=null ) {
				try {
					cs.close();
				}
				catch(SQLException e) {
					System.out.println("Erro ao fechar a conexão");
				}
			}
		}
		return null;
	}
}
