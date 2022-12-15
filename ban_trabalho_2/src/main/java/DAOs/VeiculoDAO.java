package DAOs;

import Entidades.Incidente;
import Entidades.Veiculo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class VeiculoDAO extends GenericDAO {

    final String nomeTabela = "veiculo";

    public String getNomeTabela() {
        return nomeTabela;
    }

    private void validar(Veiculo entidade) {
    }

    public boolean create (Veiculo entidade){
        validar(entidade);
        try {
            Long id = Long.parseLong(maxValue(nomeTabela, "codVeiculo"));
            id++;

            entidade.setCodVeiculo(id);
            insertOne(nomeTabela, entidade);

            return true;
        }catch (Exception e){
            System.out.println("Erro ao inserir registro na tabela " + nomeTabela);
            System.out.println(e.getMessage());

            return false;
        }
    }

    public boolean update (Veiculo entidade){
        validar(entidade);
        try {
            update(nomeTabela, entidade.get_id(), entidade);
            return true;
        }catch (Exception e){
            System.out.println("Erro ao inserir registro na tabela " + nomeTabela);
            System.out.println(e.getMessage());

            return false;
        }
    }

    public boolean delete (Long id){
        Veiculo entidade = findById(id);

        if (entidade == null){
            return true;
        }
        delete(nomeTabela, entidade.get_id());
        return true;
    }

    public Veiculo findById(Long id){
        try {
            String json = findOne(nomeTabela, String.format("{codVeiculo: %s}", id));
            return gson.fromJson(json, Veiculo.class);
        }catch (Exception e){
            System.out.println("Erro ao inserir registro na tabela " + nomeTabela);
            System.out.println(e.getMessage());

            return null;
        }
    }

    public List<Veiculo> listar(){
        try {
            List<String> jsons = listar(nomeTabela);

            List<Veiculo> list = new ArrayList<>();
            for (String s : jsons){
                Veiculo entidade = gson.fromJson(s, Veiculo.class);
                list.add(entidade);
            }
            return list;
        }catch (Exception e){
            System.out.println("Erro na tabela " + nomeTabela);
            System.out.println(e.getMessage());

            return null;
        }
    }

}
