package br.ufjf.lp3;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;


class PessoaTableModel extends AbstractTableModel {
    Connection conexao;
    
    public PessoaTableModel(Connection conexao) {
        this.conexao=conexao;
    }

    @Override
    public int getRowCount() {
        try {
            Statement operacao = conexao.createStatement();
            operacao.executeQuery("SELECT COUNT(nome) FROM pessoa");
            return 2;
                    } catch (SQLException ex) {
            Logger.getLogger(PessoaTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public int getColumnCount() {
        return 2;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return "";
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
