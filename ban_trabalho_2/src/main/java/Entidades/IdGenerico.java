package Entidades;

public class IdGenerico {

    private int _id;

    public int get_id() {
        return _id;
    }

    public IdGenerico() {

    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String gerarJsonUpdate(){
        return "";
    }
}
