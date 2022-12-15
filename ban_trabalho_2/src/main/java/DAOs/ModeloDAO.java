package DAOs;

import Entidades.Incidente;
import Entidades.Marca;
import Entidades.Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ModeloDAO extends GenericDAO {

    final String nomeTabela = "modelo";

    public String getNomeTabela() {
        return nomeTabela;
    }

    private void validar(Modelo entidade) {
    }
    
    public boolean create (Modelo entidade){
        validar(entidade);
        try {
            Long id = Long.parseLong(maxValue(nomeTabela, "codModelo"));
            id++;

            entidade.setCodModelo(id);
            insertOne(nomeTabela, entidade);

            return true;
        }catch (Exception e){
            System.out.println("Erro ao inserir registro na tabela " + nomeTabela);
            System.out.println(e.getMessage());

            return false;
        }
    }

    public boolean update (Modelo entidade){
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
        Modelo entidade = findById(id);

        if (entidade == null){
            return true;
        }
        delete(nomeTabela, entidade.get_id());
        return true;
    }

    public Modelo findById(Long id){
        try {
            String json = findOne(nomeTabela, String.format("{codModelo: %s}", id));
            return gson.fromJson(json, Modelo.class);
        }catch (Exception e){
            System.out.println("Erro ao inserir registro na tabela " + nomeTabela);
            System.out.println(e.getMessage());

            return null;
        }
    }

    public List<Modelo> listar(){
        try {
            List<String> jsons = listar(nomeTabela);

            List<Modelo> list = new ArrayList<>();
            for (String s : jsons){
                Modelo entidade = gson.fromJson(s, Modelo.class);
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
