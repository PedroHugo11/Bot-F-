public class Bem {
    private String codigo;
    private String nome;
    private String descricao;
    private Localizacao localização;
    private CategoriaBem categoria;


    public Bem(String codigo, String nome, String descricao, Localizacao localização, CategoriaBem categoria) {
        this.codigo = codigo;
        this.nome = nome;
        this.descricao = descricao;
        this.localização = localização;
        this.categoria = categoria;
    }

    //GETTERS E SETTERS
    public String getCodigo() { return codigo;  }
    public void setCodigo(String codigo) { this.codigo = codigo; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    public CategoriaBem getCategoria() { return categoria; }
    public Localizacao getLocalização() { return localização; }
}
