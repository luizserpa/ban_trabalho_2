package Entidades;

public class Marca extends IdGenerico {
    private Long codMarca;
    private String nome;
    private Integer dataDesat;


    public Marca() {
        this.codMarca = 0L;
        this.nome = "";
        this.dataDesat = 0;
    }

    @Override
    public String gerarJsonUpdate() {
        return String.format("{$codMarca: %s, $dataDesat: %s, $nome: \"%s\" }",
                codMarca, dataDesat, nome);
    }

    public Integer getDataDesat() {
        return dataDesat;
    }

    public void setDataDesat(Integer dataDesat) {
        this.dataDesat = dataDesat;
    }

    public Long getCodMarca() {
        return codMarca;
    }

    public void setCodMarca(Long codMarca) {
        this.codMarca = codMarca;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
