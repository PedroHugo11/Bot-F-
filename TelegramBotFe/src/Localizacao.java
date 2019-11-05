import java.util.ArrayList;

public class Localizacao {

    private String nome;
    private String descricao;

    private ArrayList<Localizacao> localizacoes = new ArrayList<Localizacao>();

    public Localizacao(String nome, String descricao) {
        this.nome = nome;
        this.descricao = descricao;
    }

    public Localizacao() {
        //Construtor
    }

    //GETTERS E SETTERS
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    public ArrayList<Localizacao> getLocalizacoes() { return localizacoes; }


    public ArrayList<Localizacao> cadastrarLocalizacao(ArrayList<Localizacao> localizacoes, Localizacao localizacao) {
        localizacoes.add(localizacao);
        return localizacoes;
    }
}

