//import java.util.ArrayList;
//
//import static org.junit.Assert.*;
//
//public class LocalizacaoTest {
//
//    @org.junit.Before
//    public void setUp() throws Exception {
//        verificaSeLocalizacaoFoiCadastrada();
//    }
//
//    @org.junit.Test
//    public void verificaSeLocalizacaoFoiCadastrada() {
//        //Arrange
//        Localizacao local = new Localizacao("Sala", "A192");
//        Gerenciador gerenciador = new Gerenciador();
//        ArrayList<Localizacao> localizacoes = new ArrayList<Localizacao>();
//
//        //Act
//        localizacoes = gerenciador.cadastrarLocalizacao( local);
//
//        //Assert
//        for (Localizacao loc : localizacoes) {
//            assertTrue(loc.getNome() == local.getNome());
//        }
//    }
//}