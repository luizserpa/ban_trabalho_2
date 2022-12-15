package DAOs;

import Entidades.Motorista;

import java.util.ArrayList;
import java.util.List;

public class MotoristaDAO extends GenericDAO {

    final String nomeTabela = "motorista";
    
    public boolean create (Motorista entidade){
        validar(entidade);
        try {
            Long id = Long.parseLong(maxValue(nomeTabela, "codMot"));
            id++;

            entidade.setCodMot(id);
            insertOne(nomeTabela, entidade);
            return true;
        }catch (Exception e){
            System.out.println("Erro ao inserir registro na tabela Motorista");
            System.out.println(e.getMessage());

            return false;
        }
    }

    public boolean update (Motorista entidade){
        validar(entidade);
        try {
            update(nomeTabela, entidade.get_id(), entidade);
            return true;
        }catch (Exception e){
            System.out.println("Erro ao inserir registro na tabela Motorista");
            System.out.println(e.getMessage());

            return false;
        }
    }

    public boolean delete (Long codMot){
        Motorista entidade = findById(codMot);

        if (entidade == null){
            return true;
        }

        delete(nomeTabela, entidade.get_id());
        return true;
    }

    public Motorista findById(Long codMot){
        try {
            String json = findOne(nomeTabela, String.format("{codMot: %s}", codMot));
            return gson.fromJson(json, Motorista.class);
        }catch (Exception e){
            System.out.println("Erro ao inserir registro na tabela Motorista");
            System.out.println(e.getMessage());

            return null;
        }
    }

    public List<Motorista> listar() {
        try {
            List<String> jsons = listar(nomeTabela);

            List<Motorista> list = new ArrayList<>();
            for (String s : jsons){
                Motorista entidade = gson.fromJson(s, Motorista.class);
                list.add(entidade);
            }
            return list;
        } catch (Exception e){
            System.out.println("Erro ao buscar lista");
            System.out.println(e.getMessage());

            return null;
        }
    }

    public String getNomeTabela() {
        return nomeTabela;
    }

    private void validar(Motorista motorista) {
    }
}
