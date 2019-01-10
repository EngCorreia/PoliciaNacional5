package ao.co.policia.policianacional.modelos;

public class ModeloUsuarios {

    private String nome;
    private String status;
    private int imagem;
    private int imagem_pol;

    public ModeloUsuarios() {
    }

    public ModeloUsuarios(String nome, String status, int imagem) {
        this.nome = nome;
        this.status = status;
        this.imagem = imagem;

    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getImagem() {
        return imagem;
    }

    public void setImagem(int imagem) {
        this.imagem = imagem;
    }

    public int getImagem_pol() {
        return imagem_pol;
    }

    public void setImagem_pol(int imagem_pol) {
        this.imagem_pol = imagem_pol;
    }
}
