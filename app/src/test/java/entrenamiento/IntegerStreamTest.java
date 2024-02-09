package entrenamiento;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class IntegerStreamTest {
    @Test
    public void testMapearNumerosDuplicados() {
        List<Integer> listaNumeros = Arrays.asList(1, 2, 3, 4);
        // Utilizar map para duplicar los números
        List<Integer> numerosDuplicados = listaNumeros.stream()
                .map(numero -> numero * 2)
                .collect(Collectors.toList());

        // Verificar que la lista de números duplicados contenga 2, 4, 6 y 8
        assertEquals(Arrays.asList(2, 4, 6, 8),numerosDuplicados);
    }

    @Test
    public void testFiltrarNumerosPares() {
        List<Integer> listaNumeros = Arrays.asList(1, 2, 3, 4);
        // Utilizar filter para obtener los números pares
        List<Integer> numerosPares = listaNumeros.stream()
                .filter(numero -> (numero % 2 == 0) )
                .collect(Collectors.toList());

        //Verificar que la lista de números pares contenga 2 y 4
        assertEquals(Arrays.asList(2, 4),numerosPares);
    }

    @Test
    public void testReduce() {
        List<Integer> lista = Arrays.asList(1, 2, 3, 4, 5);
        // Utilizar reduce para sumar todos los números de la lista
        int sumatoria = lista.stream()
                .reduce(0, (a, b) -> a + b );
        // Verificar que la suma sea 15
        assertEquals(15, sumatoria);
    }

    @Test
    public void testAllMatch() {
        List<Integer> listaNumeros = Arrays.asList(1, 2, 3, 4, 5);
        // Utilizar allMatch para verificar que todos son mayores a 0
        boolean mayoresCero = listaNumeros.stream()
                .allMatch(numero -> numero > 0);
        // verificar que el resultado sea verdadero
        assertEquals(mayoresCero, true);
    }


}
