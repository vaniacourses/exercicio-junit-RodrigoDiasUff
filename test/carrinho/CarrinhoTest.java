package carrinho;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import produto.Produto;
import produto.ProdutoNaoEncontradoException;

@DisplayName("Testes da classe Carrinho")
public class CarrinhoTest {

    private Carrinho carrinho;
    private Produto produto1;
    private Produto produto2;

    @BeforeEach
    void setUp() {
        carrinho = new Carrinho();
        produto1 = new Produto("Mouse", 100.0);
        produto2 = new Produto("Teclado", 150.0);
    }

    @Test
    @DisplayName("Carrinho deve iniciar vazio")
    void testCarrinhoIniciaVazio() {
        assertEquals(0, carrinho.getQtdeItems(), "O carrinho deve começar sem itens");
        assertEquals(0.0, carrinho.getValorTotal(), 0.001, "O valor total inicial deve ser zero");
    }

    @Test
    @DisplayName("Deve adicionar itens corretamente")
    void testAddItem() {
        carrinho.addItem(produto1);
        carrinho.addItem(produto2);

        assertEquals(2, carrinho.getQtdeItems(), "Quantidade de itens deve ser 2");
        assertEquals(250.0, carrinho.getValorTotal(), 0.001, "Valor total deve ser a soma dos produtos");
    }

    @Test
    @DisplayName("Deve remover item existente sem lançar exceção")
    void testRemoveItemExistente() {
        carrinho.addItem(produto1);
        carrinho.addItem(produto2);

        assertDoesNotThrow(() -> carrinho.removeItem(produto1),
                "Não deve lançar exceção ao remover item existente");

        assertEquals(1, carrinho.getQtdeItems(), "Deve restar apenas 1 item no carrinho");
        assertEquals(150.0, carrinho.getValorTotal(), 0.001, "Valor total deve ser apenas do teclado");
    }

    @Test
    @DisplayName("Deve lançar ProdutoNaoEncontradoException ao remover item inexistente")
    void testRemoveItemInexistente() {
        carrinho.addItem(produto1);

        Produto produtoNaoAdicionado = new Produto("Monitor", 800.0);

        assertThrows(ProdutoNaoEncontradoException.class,
                () -> carrinho.removeItem(produtoNaoAdicionado),
                "Deve lançar exceção ao tentar remover produto que não está no carrinho");
    }

    @Test
    @DisplayName("Deve esvaziar o carrinho corretamente")
    void testEsvazia() {
        carrinho.addItem(produto1);
        carrinho.addItem(produto2);

        carrinho.esvazia();

        assertEquals(0, carrinho.getQtdeItems(), "Após esvaziar, não deve haver itens");
        assertEquals(0.0, carrinho.getValorTotal(), 0.001, "Após esvaziar, o valor total deve ser zero");
    }

    @Test
    @DisplayName("getValorTotal deve somar corretamente vários itens")
    void testGetValorTotalComVariosItens() {
        carrinho.addItem(new Produto("Produto A", 10.0));
        carrinho.addItem(new Produto("Produto B", 20.0));
        carrinho.addItem(new Produto("Produto C", 30.0));

        assertEquals(60.0, carrinho.getValorTotal(), 0.001, "Soma dos valores deve ser 60.0");
    }
}
