public class Bem {
    private String codigo;
    private String nome;
    private String descricao;
    private Localizacao localizacao;
    private Categoria categoria;


    public Bem(String codigo, String nome, String descricao, Localizacao localizacao, Categoria categoria) {
        this.codigo = codigo;
        this.nome = nome;
        this.descricao = descricao;
        this.localizacao = localizacao;
        this.categoria = categoria;
    }

    //GETTERS E SETTERS
    public String getCodigo() { return codigo;  }
    public void setCodigo(String codigo) { this.codigo = codigo; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    public Categoria getCategoria() { return categoria; }
    public Localizacao getLocalizacao() { return localizacao; }
}
