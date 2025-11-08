package calculadora;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

/**
 * Classe de testes unitários para a classe Calculadora.
 */
@DisplayName("Testes da classe Calculadora")
public class CalculadoraTest {

    private Calculadora calc;

    @BeforeEach
    public void inicializa() {
        calc = new Calculadora();
    }

    @Test
    @DisplayName("Deve somar dois números corretamente")
    public void testSomaDoisNumeros() {
        int resultado = calc.soma(4, 5);
        assertEquals(9, resultado, "A soma de 4 + 5 deve ser 9");
    }

    @Test
    @DisplayName("Deve subtrair dois números corretamente")
    public void testSubtracaoDoisNumeros() {
        int resultado = calc.subtracao(10, 3);
        assertEquals(7, resultado, "A subtração de 10 - 3 deve ser 7");
    }

    @Test
    @DisplayName("Deve multiplicar dois números corretamente")
    public void testMultiplicacaoDoisNumeros() {
        int resultado = calc.multiplicacao(6, 7);
        assertEquals(42, resultado, "A multiplicação de 6 x 7 deve ser 42");
    }

    @Test
    @DisplayName("Deve dividir dois números corretamente")
    public void testDivisaoDoisNumeros() {
        int resultado = calc.divisao(8, 4);
        assertEquals(2, resultado, "A divisão de 8 / 4 deve ser 2");
    }

    @Test
    @DisplayName("Deve lançar exceção ao dividir por zero (try/catch)")
    public void testDivisaoPorZeroTryCatch() {
        try {
            calc.divisao(8, 0);
            fail("Deveria ter lançado ArithmeticException");
        } catch (ArithmeticException e) {
            assertEquals("/ by zero", e.getMessage());
        }
    }

    @Test
    @DisplayName("Deve lançar exceção ao dividir por zero (assertThrows)")
    public void testDivisaoPorZeroAssertThrows() {
        assertThrows(ArithmeticException.class,
                () -> calc.divisao(8, 0),
                "Dividir por zero deve lançar ArithmeticException");
    }
}
