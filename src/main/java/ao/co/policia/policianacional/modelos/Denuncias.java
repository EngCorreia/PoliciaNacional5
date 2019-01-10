package ao.co.policia.policianacional.modelos;

public class Denuncias {

    private String codigo;
    private String nome;
    private  String descricao;
    private int imagem;

    public Denuncias() {


    }

    public Denuncias(String nome, String descricao, int imagem) {
        this.nome = nome;
        this.descricao = descricao;
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

    public int getImagem() {
        return imagem;
    }

    public void setImagem(int imagem) {
        this.imagem = imagem;
    }
}
