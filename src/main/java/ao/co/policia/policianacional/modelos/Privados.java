package ao.co.policia.policianacional.modelos;

public class Privados {

    private String nomes;
    private  String descricao;
    private String localizacao;
    private String imagem;

    public Privados() {
    }

    public Privados(String nomes, String descricao, String localizacao, String imagem) {
        this.nomes = nomes;
        this.descricao = descricao;
        this.localizacao = localizacao;
        this.imagem = imagem;
    }

    public String getNomes() {
        return nomes;
    }

    public void setNomes(String nomes) {
        this.nomes = nomes;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }
}
