package Entidades;

public class Veiculo extends IdGenerico {
   private Long codVeiculo;
   private String chassi;
   private Long codModelo;
   private String placa;
   private String uf;
   private Integer dataDesat;

    public Veiculo() {
        this.codVeiculo = 0L;
        this.chassi = "";
        this.codModelo = 0L;
        this.placa = "";
        this.uf = "";
        this.dataDesat = 0;
    }

    @Override
    public String gerarJsonUpdate() {
        return String.format("{$codVeiculo: %s, $chassi: \"%s\", $codModelo: %s, $placa: \"%s\", $uf: \"%s\", $dataDesat: %s }",
                codVeiculo, chassi, codModelo, placa, uf, dataDesat);
    }

    public Long getCodVeiculo() {
        return codVeiculo;
    }

    public void setCodVeiculo(Long codVeiculo) {
        this.codVeiculo = codVeiculo;
    }

    public String getChassi() {
        return chassi;
    }

    public void setChassi(String chassi) {
        this.chassi = chassi;
    }

    public Long getCodModelo() {
        return codModelo;
    }

    public void setCodModelo(Long codModelo) {
        this.codModelo = codModelo;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public Integer getDataDesat() {
        return dataDesat;
    }

    public void setDataDesat(Integer dataDesat) {
        this.dataDesat = dataDesat;
    }
}
