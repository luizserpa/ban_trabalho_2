package DAOs;

import Entidades.Incidente;
import Entidades.Marca;
import Entidades.Motorista;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class MarcaDAO extends GenericDAO {

    final String nomeTabela = "marca";

    public String getNomeTabela() {
        return nomeTabela;
    }
    
    public boolean create (Marca entidade){
        validar(entidade);
        try {
            Long id = Long.parseLong(maxValue(nomeTabela, "codMarca"));
            id++;

            entidade.setCodMarca(id);
            insertOne(nomeTabela, entidade);
            return true;
        }catch (Exception e){
            System.out.println("Erro ao inserir registro na tabela " + nomeTabela);
            System.out.println(e.getMessage());

            return false;
        }
    }

    public boolean update (Marca entidade){
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

    public boolean delete (Long codMarca){
        Marca entidade = findById(codMarca);

        if (entidade == null){
            return true;
        }
        delete(nomeTabela, entidade.get_id());
        return true;
    }

    public Marca findById(Long id){
        try {
            String json = findOne(nomeTabela, String.format("{codMarca: %s}", id));
            return gson.fromJson(json, Marca.class);
        }catch (Exception e){
            System.out.println("Erro ao inserir registro na tabela " + nomeTabela);
            System.out.println(e.getMessage());

            return null;
        }
    }

    public List<Marca> listar() {
        try {
            List<String> jsons = listar(nomeTabela);

            List<Marca> list = new ArrayList<>();
            for (String s : jsons){
                Marca entidade = gson.fromJson(s, Marca.class);
                list.add(entidade);
            }
            return list;
        } catch (Exception e){
            System.out.println("Erro ao buscar lista");
            System.out.println(e.getMessage());

            return null;
        }
    }

    private void validar(Marca motorista) {
    }
}
