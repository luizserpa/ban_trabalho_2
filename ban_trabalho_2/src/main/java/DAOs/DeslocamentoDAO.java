package DAOs;

import Entidades.Deslocamento;
import Entidades.Incidente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DeslocamentoDAO extends GenericDAO{

    final String nomeTabela = "deslocamento";


    private void validar(Deslocamento entidade) {
    }

    public Long create (Deslocamento entidade){
        validar(entidade);
        try {
            Long id = Long.parseLong(maxValue(nomeTabela, "codDes"));
            id++;

            entidade.setCodDes(id);
            insertOne(nomeTabela, entidade);

            return id;
        }catch (Exception e){
            System.out.println("Erro ao inserir registro na tabela " + nomeTabela);
            System.out.println(e.getMessage());

            return 0L;
        }
    }

    public boolean update (Deslocamento entidade){
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
        Deslocamento entidade = findById(id);

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

    public Deslocamento findById(Long id){
        try {
            String json = findOne(nomeTabela, String.format("{codDes: %s}", id));
            return gson.fromJson(json, Deslocamento.class);
        }catch (Exception e){
            System.out.println("Erro ao inserir registro na tabela " + nomeTabela);
            System.out.println(e.getMessage());

            return null;
        }
    }

    public List<Deslocamento> findByStatus(String status){
        try {
            List<String> jsons = findList(nomeTabela, String.format("{statusDes: \"%s\"}", status));

            List<Deslocamento> list = new ArrayList<>();
            for (String s : jsons){
                Deslocamento entidade = gson.fromJson(s, Deslocamento.class);
                list.add(entidade);
            }
            return list;
        }catch (Exception e){
            System.out.println("Erro ao inserir registro na tabela " + nomeTabela);
            System.out.println(e.getMessage());

            return null;
        }
    }

    public List<Deslocamento> listar(){
        try {
            List<String> jsons = listar(nomeTabela);

            List<Deslocamento> list = new ArrayList<>();
            for (String s : jsons){
                Deslocamento entidade = gson.fromJson(s, Deslocamento.class);
                list.add(entidade);
            }
            return list;
        }catch (Exception e){
            System.out.println("Erro ao inserir registro na tabela " + nomeTabela);
            System.out.println(e.getMessage());

            return null;
        }
    }


    public List<Deslocamento> findAllByCodMot(long id) {
        try {
            List<String> jsons = findList(nomeTabela, String.format("{codMot: %s}", id));

            List<Deslocamento> list = new ArrayList<>();
            for (String s : jsons){
                Deslocamento entidade = gson.fromJson(s, Deslocamento.class);
                list.add(entidade);
            }
            return list;
        }catch (Exception e){
            System.out.println("Erro ao inserir registro na tabela " + nomeTabela);
            System.out.println(e.getMessage());

            return null;
        }
    }

    public List<Deslocamento> findAllByCodVeiculo(long id) {
        try {
            List<String> jsons = findList(nomeTabela, String.format("{codVeiculo: %d}", id));

            List<Deslocamento> list = new ArrayList<>();
            for (String s : jsons){
                Deslocamento entidade = gson.fromJson(s, Deslocamento.class);
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
