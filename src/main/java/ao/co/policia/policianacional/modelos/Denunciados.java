package ao.co.policia.policianacional.modelos;

public class Denunciados {

    private String codigo;
    private String nome;
    private String datas;
    private String tipoDenunca;
    private String crime;
    private int imagem;

    public Denunciados() {
    }

    public Denunciados(String codigo, String nome, String datas, String tipoDenunca, String crime, int imagem) {
        this.codigo = codigo;
        this.nome = nome;
        this.datas = datas;
        this.tipoDenunca = tipoDenunca;
        this.crime = crime;
        this.imagem = imagem;
    }


    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDatas() {
        return datas;
    }

    public void setDatas(String datas) {
        this.datas = datas;
    }

    public String getTipoDenunca() {
        return tipoDenunca;
    }

    public void setTipoDenunca(String tipoDenunca) {
        this.tipoDenunca = tipoDenunca;
    }

    public String getCrime() {
        return crime;
    }

    public void setCrime(String crime) {
        this.crime = crime;
    }

    public int getImagem() {
        return imagem;
    }

    public void setImagem(int imagem) {
        this.imagem = imagem;
    }
}
