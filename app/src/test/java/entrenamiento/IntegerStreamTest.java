package entrenamiento;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

public class IntegerStreamTest {
    @Test
    public void testMapearNumerosDuplicados() {
        List<Integer> listaNumeros = Arrays.asList(1, 2, 3, 4);
        // Utilizar map para duplicar los números
        // Verificar que la lista de números duplicados contenga 2, 4, 6 y 8
    }

    @Test
    public void testFiltrarNumerosPares() {
        List<Integer> listaNumeros = Arrays.asList(1, 2, 3, 4);
        // Utilizar filter para obtener los números pares
        //Verificar que la lista de números pares contenga 2 y 4
    }

    @Test
    public void testReduce() {
        List<Integer> lista = Arrays.asList(1, 2, 3, 4,5);
        // Utilizar reduce para sumar todos los números de la lista
        // Verificar que la suma sea 15
    }


    @Test
    public void testAllMatch() {
        List<Integer> listaNumeros = Arrays.asList(1, 2, 3, 4, 5);
        // Utilizar allMatch para verificar que todos son mayores a 0
        // verificar que el resultado sea verdadero
    }


}
