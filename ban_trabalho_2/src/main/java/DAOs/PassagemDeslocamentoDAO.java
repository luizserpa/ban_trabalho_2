package DAOs;

import Entidades.Incidente;
import Entidades.PassagemDeslocamento;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class PassagemDeslocamentoDAO extends GenericDAO {

    final String nomeTabela = "passagem_deslocamento";


    private void validar(PassagemDeslocamento entidade) {
    }

    public boolean create (PassagemDeslocamento entidade){
        validar(entidade);
        try {
            Long id = Long.parseLong(maxValue(nomeTabela, "seq"));
            id++;

            entidade.setSeq(id);
            insertOne(nomeTabela, entidade);

            return true;
        }catch (Exception e){
            System.out.println("Erro ao inserir registro na tabela " + nomeTabela);
            System.out.println(e.getMessage());

            return false;
        }
    }

    public boolean update (PassagemDeslocamento entidade){
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

    public boolean delete (Long codDes, Long seq){
        PassagemDeslocamento entidade = findById(codDes, seq);

        if (entidade == null){
            return true;
        }

        try {
            delete(nomeTabela, entidade.get_id());
            return true;
        }catch (Exception e){
            System.out.println("Erro ao inserir registro na tabela " + nomeTabela);
            System.out.println(e.getMessage());

            return false;
        }
    }

    public PassagemDeslocamento findById(Long codDes, Long seq){
        try {
            String json = findOne(nomeTabela, String.format("{codDes: %s, seq: %s}", codDes, seq));
            return gson.fromJson(json, PassagemDeslocamento.class);
        }catch (Exception e){
            System.out.println("Erro ao inserir registro na tabela " + nomeTabela);
            System.out.println(e.getMessage());

            return null;
        }
    }

    public List<PassagemDeslocamento> findAllByCodDes(Long codDes){
        try {
            List<String> jsons = findList(nomeTabela, String.format("{codDes: %s}", codDes));

            List<PassagemDeslocamento> list = new ArrayList<>();
            for (String s : jsons){
                PassagemDeslocamento entidade = gson.fromJson(s, PassagemDeslocamento.class);
                list.add(entidade);
            }
            return list;
        }catch (Exception e){
            System.out.println("Erro ao inserir registro na tabela " + nomeTabela);
            System.out.println(e.getMessage());

            return null;
        }
    }

}
