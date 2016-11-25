package br.ufjf.lp3;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;


class PessoaTableModel extends AbstractTableModel {
    private final Connection conexao;
    List<Pessoa> pessoas;
    
    public PessoaTableModel(Connection conexao) {
        this.conexao=conexao;
    }

    @Override
    public int getRowCount() {
        try {
            Statement operacao = conexao.createStatement();
            ResultSet resultado = operacao.executeQuery("SELECT COUNT(nome) FROM pessoa");
            if (resultado.next())
            {
                return resultado.getInt(1);
            }
            return 2;
                    } catch (SQLException ex) {
            Logger.getLogger(PessoaTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 2;
    }

    @Override
    public int getColumnCount() {
        return 2;
    }
    
   public void atualizaDados() {
        pessoas = new ArrayList<Pessoa>();
       try{
         Statement operacao = conexao.createStatement();
         ResultSet resultado = operacao.executeQuery("SELECT nome,telefone FROM pessoa");
         int l = 0;
         while (resultado.next())
         {
             
             String nome = resultado.getString(1);
             String telefone = resultado.getString(2);
             pessoas.add(new Pessoa(nome,telefone));
         }
        }
        catch (SQLException ex)
        {
            System.err.println(ex);
        }
       
   }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        try{
         Statement operacao = conexao.createStatement();
         ResultSet resultado = operacao.executeQuery("SELECT nome,telefone FROM pessoa");
         int l = 0;
         while (resultado.next())
         {
             String nome = resultado.getString(1);
             String telefone = resultado.getString(2);
             if (l++ == rowIndex)
                switch(columnIndex){
                    case 0: return nome;
                    case 1: return telefone;
                    default: return "naosei";
                }
         }
        }
        catch (SQLException ex)
        {
            System.err.println(ex);
        }
        return null;
    }

    @Override
    public String getColumnName(int column) {
        switch(column)
        {
            case 0: return "Nome";
            case 1: return "Telefone";
            default:return "?";
        }
    }
    
    
}
