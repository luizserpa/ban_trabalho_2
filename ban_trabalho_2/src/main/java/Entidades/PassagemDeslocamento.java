package Entidades;

public class PassagemDeslocamento extends IdGenerico {
    private Long codDes;
    private Long seq;
    private String tipoDes;

    public PassagemDeslocamento() {
        this.codDes = 0L;
        this.seq = 0L;
        this.tipoDes = "";
    }

    @Override
    public String gerarJsonUpdate() {
        return String.format("{$codDes: %s, $seq: %s, $nome: \"%s\"}",
                codDes, seq, tipoDes);
    }

    public Long getCodDes() {
        return codDes;
    }

    public void setCodDes(Long codDes) {
        this.codDes = codDes;
    }

    public Long getSeq() {
        return seq;
    }

    public void setSeq(Long seq) {
        this.seq = seq;
    }

    public String getTipoDes() {
        return tipoDes;
    }

    public void setTipoDes(String tipoDes) {
        this.tipoDes = tipoDes;
    }
}
