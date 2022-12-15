package Entidades;

public class Incidente extends IdGenerico {
    private Long codDes;
    private Long codInc;
    private String tipoInc;
    private String descricao;

    public Incidente() {
        this.codDes = 0L;
        this.codInc = 0L;
        this.tipoInc = "";
        this.descricao = "";
    }

    @Override
    public String gerarJsonUpdate() {
        return String.format("{$codDesc: %s, $codInc: %s, $descricao: \"%s\", $tipoInc: \"%s\" }",
                codDes, codInc, descricao, tipoInc);
    }

    public Long getCodDes() {
        return codDes;
    }

    public void setCodDes(Long codDes) {
        this.codDes = codDes;
    }

    public Long getCodInc() {
        return codInc;
    }

    public void setCodInc(Long codInc) {
        this.codInc = codInc;
    }

    public String getTipoInc() {
        return tipoInc;
    }

    public void setTipoInc(String tipoInc) {
        this.tipoInc = tipoInc;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
