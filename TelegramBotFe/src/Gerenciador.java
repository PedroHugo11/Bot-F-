import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.GetUpdates;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.GetUpdatesResponse;
import com.pengrad.telegrambot.response.SendResponse;

import java.util.ArrayList;
import java.util.List;

public class Gerenciador implements ControlePatrimonio {

    private TelegramBot bot;
    private ArrayList<Localizacao> localizacoes = new ArrayList<Localizacao>();
    private ArrayList<Categoria> categorias = new ArrayList<Categoria>();
    private ArrayList<Bem> bens = new ArrayList<Bem>();

    public Gerenciador(TelegramBot bot) {
        this.bot = bot;
    }

    public ArrayList<Localizacao> getLocalizacoes() {
        return localizacoes;
    }
    public ArrayList<Categoria> getCategorias() {
        return categorias;
    }
    public ArrayList<Bem> getBens() { return bens; }

    public Localizacao buscaLocalizacaoPorNome(String localizacao) {
        for (Localizacao local : getLocalizacoes()) {
            if(localizacao.equals(local.getNome())){
                return local;
            }
        }
        return null;
    }

    public Categoria buscaCategoriaPorNome(String categoria) {
        for (Categoria categoria_bem : getCategorias()) {
            if(categoria.equals(categoria_bem.getNome())){
                return categoria_bem;
            }
        }
        return null;
    }

    @Override
    public void movimentaBem(String codigo, Localizacao local_destino) {
        for (Bem bem : getBens()) {
            if (codigo.equals(bem.getCodigo())) {
                bem.setLocalizacao(local_destino);
            }
        }
    }

    @Override
    public void geraRelatorio() {

    }
}
