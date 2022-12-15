package DAOs;

import Entidades.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class IncidenteDAO extends GenericDAO {

    final String nomeTabela = "incidente";
    private VeiculoDAO veiculoDAO = new VeiculoDAO();
    private ModeloDAO modeloDAO = new ModeloDAO();
    private MotoristaDAO motoristaDAO = new MotoristaDAO();
    private DeslocamentoDAO deslocamentoDAO = new DeslocamentoDAO();


    private void validar(Incidente entidade) {
    }

    public boolean create (Incidente entidade){
        validar(entidade);
        try {
            Long id = Long.parseLong(maxValue(nomeTabela, "codInc"));
            id++;

            entidade.setCodInc(id);
            insertOne(nomeTabela, entidade);
        }catch (Exception e){
            System.out.println("Erro ao inserir registro na tabela " + nomeTabela);
            System.out.println(e.getMessage());

            return false;
        }
        return true;
    }

    public boolean update (Incidente entidade){
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

    public boolean delete (Long codDes, Long codInc){
        Incidente entidade = findById(codDes, codInc);

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

    public Incidente findById(Long codDes, Long codInc){
        try {
            String json = findOne(nomeTabela, String.format("{codDes: %s, codInc: %s}", codDes, codInc));
            return gson.fromJson(json, Incidente.class);
        }catch (Exception e){
            System.out.println("Erro ao inserir registro na tabela " + nomeTabela);
            System.out.println(e.getMessage());

            return null;
        }
    }

    public Incidente findByCodInc(Long codInc){
        try {
            String json = findOne(nomeTabela, String.format("{codInc: %s}", codInc));
            return gson.fromJson(json, Incidente.class);
        }catch (Exception e){
            System.out.println("Erro ao inserir registro na tabela " + nomeTabela);
            System.out.println(e.getMessage());

            return null;
        }
    }


    public List<Incidente> listar(){
        try {
            List<String> jsons = listar(nomeTabela);

            List<Incidente> list = new ArrayList<>();
            for (String s : jsons){
                Incidente entidade = gson.fromJson(s, Incidente.class);
                list.add(entidade);
            }
            return list;
        }catch (Exception e){
            System.out.println("Erro na tabela " + nomeTabela);
            System.out.println(e.getMessage());

            return null;
        }
    }

    public List<Incidente> listarPorTipo(String tipo){
        try {
            List<String> jsons = findList(nomeTabela, String.format("{tipoInc: %s}", tipo));

            List<Incidente> list = new ArrayList<>();
            for (String s : jsons){
                Incidente entidade = gson.fromJson(s, Incidente.class);
                list.add(entidade);
            }
            return list;
        }catch (Exception e){
            System.out.println("Erro na tabela " + nomeTabela);
            System.out.println(e.getMessage());

            return null;
        }
    }

    public List<DeslocamentoIncidente> findAllDeslocamentoIncidenteByCodMot(long id) {
        try {
            List<DeslocamentoIncidente> list = new ArrayList<>();
            List<Deslocamento> deslocamentoList = deslocamentoDAO.findAllByCodMot(id);
            for (Deslocamento d : deslocamentoList){
                List<Incidente> incidentes = findAllBycodDes(d.getCodDes());
                for (Incidente i : incidentes){
                    Veiculo veiculoList = veiculoDAO.findById(d.getCodVeiculo());
                    Modelo modelo = modeloDAO.findById(veiculoList.getCodModelo());
                    Motorista motorista = motoristaDAO.findById(d.getCodMot());
                    DeslocamentoIncidente di = new DeslocamentoIncidente();
                    di.setCodDes(d.getCodDes());
                    di.setCodVeiculo(veiculoList.getCodVeiculo());
                    di.setCodMot(d.getCodMot());
                    di.setDescricao(d.getDescricao());
                    di.setTipoInc(i.getTipoInc());
                    di.setDescricaoIncidente(i.getDescricao());
                    di.setVeiculo(modelo.getNome());
                    di.setMotorista(motorista.getNome());
                    list.add(di);

                }
            }
            return list;
        }catch (Exception e){
            System.out.println("Erro na tabela " + nomeTabela);
            System.out.println(e.getMessage());

            return null;
        }
    }

    private List<Incidente> findAllBycodDes(Long codDes) {
        try {
            List<String> jsons = findList(nomeTabela, String.format("{codDes: %d}", codDes));

            List<Incidente> list = new ArrayList<>();
            for (String s : jsons){
                Incidente entidade = gson.fromJson(s, Incidente.class);
                list.add(entidade);
            }
            return list;
        }catch (Exception e){
            System.out.println("Erro ao inserir registro na tabela " + nomeTabela);
            System.out.println(e.getMessage());

            return null;
        }
    }

    public List<DeslocamentoIncidente> findAllDeslocamentoIncidenteByCodVeiculo(long id) {
        try {
            List<DeslocamentoIncidente> list = new ArrayList<>();
            List<Deslocamento> deslocamentoList = deslocamentoDAO.findAllByCodVeiculo(id);
            for (Deslocamento d : deslocamentoList){
                List<Incidente> incidenteList = findAllBycodDes(d.getCodDes());
                for (Incidente i : incidenteList){
                    Motorista m = motoristaDAO.findById(d.getCodMot());
                    Veiculo veiculo = veiculoDAO.findById(d.getCodVeiculo());
                    Modelo modelo = modeloDAO.findById(veiculo.getCodModelo());
                    DeslocamentoIncidente di = new DeslocamentoIncidente();
                    di.setCodDes(d.getCodDes());
                    di.setCodVeiculo(veiculo.getCodVeiculo());
                    di.setCodMot(d.getCodMot());
                    di.setDescricao(d.getDescricao());
                    di.setTipoInc(i.getTipoInc());
                    di.setDescricaoIncidente(i.getDescricao());
                    di.setVeiculo(modelo.getNome());
                    di.setMotorista(m.getNome());
                    list.add(di);
                }
            }
            return new ArrayList<>();
        }catch (Exception e){
            System.out.println("Erro na tabela " + nomeTabela);
            System.out.println(e.getMessage());

            return null;
        }
    }
}
