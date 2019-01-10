package ao.co.policia.policianacional.modelos;

public class Conctatos {

    private String nomes;
    private  String descricao;
    private String localizacao;
    private int imagem;

    public Conctatos() {
    }

    public Conctatos(String nomes, String descricao, String localizacao, int imagem) {
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

    public int getImagem() {
        return imagem;
    }

    public void setImagem(int imagem) {
        this.imagem = imagem;
    }
}
