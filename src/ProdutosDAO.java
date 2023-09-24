/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Adm
 */
import java.sql.PreparedStatement;
import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProdutosDAO {

    Connection conn;
    PreparedStatement prep;
    ResultSet resultset;
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();

    public void cadastrarProduto(ProdutosDTO produto) {
        try {
            conn = new conectaDAO().connectDB();

            prep = conn.prepareStatement("INSERT INTO produtos (nome, valor, status) VALUES (?,?,?);");
            prep.setString(1, produto.getNome());
            prep.setInt(2, produto.getValor());
            prep.setString(3, produto.getStatus());

            prep.executeUpdate();

            JOptionPane.showMessageDialog(null, "Dados inseridos com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro no acesso ao Bando de Dados!");
        }
    }

    public ArrayList<ProdutosDTO> listarProdutos() {
        try {
            conn = new conectaDAO().connectDB();
            
            prep = conn.prepareStatement("SELECT * FROM produtos;");

            resultset = prep.executeQuery();

            ProdutosDTO produto;

            do {
                resultset.next();

                produto = new ProdutosDTO();

                produto.setId(resultset.getInt("id"));
                produto.setNome(resultset.getString("nome"));
                produto.setValor(resultset.getInt("valor"));
                produto.setStatus(resultset.getString("status"));

                listagem.add(produto);
            } while (!resultset.isLast());
        } catch (SQLException ex) {
            System.out.println("Erro no acesso ao Bando de Dados!");
        }

        return listagem;
    }

    public void venderProduto(int id) {
        try {
            conn = new conectaDAO().connectDB();

            prep = conn.prepareStatement("UPDATE produtos SET status = 'Vendido' WHERE id = ?");
            prep.setInt(1, id);

            prep.executeUpdate();

            JOptionPane.showMessageDialog(null, "Status atualizado com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro no acesso ao Bando de Dados!");
        }
    }

    public ArrayList<ProdutosDTO> listarProdutosVendidos() {
        try {
            conn = new conectaDAO().connectDB();

            prep = conn.prepareStatement("SELECT * FROM produtos WHERE status = 'Vendido'");

            resultset = prep.executeQuery();

            ProdutosDTO produto;

            do {
                resultset.next();

                produto = new ProdutosDTO();

                produto.setId(resultset.getInt("id"));
                produto.setNome(resultset.getString("nome"));
                produto.setValor(resultset.getInt("valor"));
                produto.setStatus(resultset.getString("status"));

                listagem.add(produto);
            } while (!resultset.isLast());
        } catch (SQLException ex) {
            System.out.println("Erro no acesso ao Bando de Dados!");
        }

        return listagem;
    }
}
