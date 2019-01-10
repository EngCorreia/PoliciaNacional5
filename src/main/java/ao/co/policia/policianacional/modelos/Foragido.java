package ao.co.policia.policianacional.modelos;

public class Foragido {

    private String codigo;
    private String nome;
    private String descricao;
    private String crime;
    private String datas;
    private int imagem;

    public Foragido() {
    }

    public Foragido(String codigo, String nome, String descricao, String crime, String datas, int imagem) {
        this.codigo = codigo;
        this.nome = nome;
        this.descricao = descricao;
        this.crime = crime;
        this.datas = datas;
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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getCrime() {
        return crime;
    }

    public void setCrime(String crime) {
        this.crime = crime;
    }

    public String getDatas() {
        return datas;
    }

    public void setDatas(String datas) {
        this.datas = datas;
    }

    public int getImagem() {
        return imagem;
    }

    public void setImagem(int imagem) {
        this.imagem = imagem;
    }
}
