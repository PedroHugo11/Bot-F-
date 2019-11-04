public class CategoriaBem {

    private String codigo;
    private String nome;
    private String descricao;

    public CategoriaBem(String codigo, String nome) {
        this.codigo = codigo;
        this.nome = nome;
    }

    //GETTERS E SETTERS
    public String getCodigo() { return codigo;  }
    public void setCodigo(String codigo) { this.codigo = codigo; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
}
