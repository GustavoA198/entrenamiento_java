package entrenamiento;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

public class StreamTest {

    @BeforeAll
    public static void setup() {
        // Establecer la cantidad de hilos que se utilizarán
        System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism", "11");
    }

    @Test
    /**
     * map: Este método se utiliza para transformar los elementos de un Stream utilizando la función dada.
     */
    public void testMapearElementos() {
        // Arrange (Configuración): Crear la lista de nombres
        List<String> listaNombres = Arrays.asList("Juan", "Ana", "Carlos", "Elena");

        // Act (Acción): Aplicar map para convertir a mayúsculas
        List<String> nombresMayuscula = listaNombres.stream()
                .map(nombre -> nombre.toUpperCase())
                .collect(Collectors.toList());

        // Assert (Afirmación): Verificar resultados
        assertEquals(Arrays.asList("JUAN", "ANA", "CARLOS", "ELENA"), nombresMayuscula);
    }

    @Test
    /**
     * map: Este método se utiliza para transformar los elementos de un Stream utilizando la función dada.
     * Prueba unitaria para verificar que map genera una nueva lista sin modificar la original.
     */
    public void testMapGeneraNuevaLista() {
        // Arrange (Configuración): Crear la lista de nombres
        List<String> nombres = Arrays.asList("Juan", "Ana", "Carlos");

        // Act (Acción): Utilizar map para generar una nueva lista con los nombres en mayúsculas
        List<String> nombresMayusculas = nombres.stream()
                .map(String::toUpperCase)
                .collect(Collectors.toList());

        // Assert (Afirmación): Verificar que la lista original no ha sido modificada
        assertEquals(Arrays.asList("Juan", "Ana", "Carlos"), nombres);

        // Assert (Afirmación): Verificar que la nueva lista contiene los nombres en mayúsculas
        assertEquals(Arrays.asList("JUAN", "ANA", "CARLOS"), nombresMayusculas);
    }

    @Test
    /**
     * filter(Predicate predicate): Este método se utiliza para filtrar elementos de un Stream que coinciden con el predicado dado.
     * Prueba unitaria para verificar el filtrado de nombres que comienzan con "A".
     */
    public void testFiltrarElementos() {
        // Arrange (Configuración): Crear una lista de nombres
        List<String> listaNombres = Arrays.asList("Juan", "Ana", "Carlos", "Elena");

        // Act (Acción): Filtrar los nombres que comienzan con "A"
        List<String> nombresConA = listaNombres.stream()
                .filter(nombre -> nombre.startsWith("A"))
                .collect(Collectors.toList());

        // Assert (Afirmación): Comprobar que la lista filtrada contiene solo "Ana"
        assertEquals(Collections.singletonList("Ana"), nombresConA);
    }

    @Test
    /**
     * forEach: Este método se utiliza para realizar una acción en cada elemento del Stream.
     * Prueba unitaria para verificar el uso de forEach para convertir nombres a mayúsculas.
     */
    public void testForEach() {
        // Arrange (Configuración): Crear una lista de nombres
        List<String> nombres = new ArrayList<>();
        List<String> nombresMayusculas = new ArrayList<>();

        // Act (Acción): Agregar nombres y utilizar forEach para convertir a mayúsculas
        nombres.add("Juan");
        nombres.add("Ana");
        nombres.add("Carlos");
        nombres.stream()
                .forEach(nombre -> nombresMayusculas.add(nombre.toUpperCase()));

        // Assert (Afirmación): Verificar que los nombres en mayúsculas se hayan agregado correctamente
        assertEquals("JUAN", nombresMayusculas.get(0));
        assertEquals("ANA", nombresMayusculas.get(1));
        assertEquals("CARLOS", nombresMayusculas.get(2));
    }

    @Test
    /**
     * reduce: Este método se utiliza para combinar los elementos de un Stream en un solo resultado.
     * Prueba unitaria para verificar la concatenación de nombres usando reduce.
     */
    public void testReduce() {
        // Arrange (Configuración): Crear una lista de nombres
        List<String> lista = Arrays.asList("Juan", "Ana", "Carlos", "Elena");

        // Act (Acción): Utilizar reduce para concatenar los nombres
        String result = lista.stream().reduce("", (a, b) -> a + " " + b).trim();

        // Assert (Afirmación): Verificar el resultado
        assertEquals("Juan Ana Carlos Elena", result);
    }

    @Test
    /**
     * Prueba unitaria para verificar si todos los elementos de la lista comienzan con "A".
     * La lista de entrada es ["Ana", "Alberto"].
     * Se espera que el resultado sea verdadero, ya que ambos nombres comienzan con "A".
     */
    public void testAllMatch() {
        // Arrange (Configuración): Crear una lista de nombres
        List<String> lista = Arrays.asList("Ana", "Alberto");

        // Act (Acción): Verificar si todos los elementos comienzan con "A"
        boolean result = lista.stream().allMatch(name -> name.startsWith("A"));

        // Assert (Afirmación): Verificar que todos los elementos cumplen con la condición
        assertTrue(result);
    }

    @Test
    /**
     * Prueba unitaria para verificar si al menos un elemento de la lista comienza con "A".
     * La lista de entrada es ["Juan", "Ana", "Carlos", "Elena"].
     * Se espera que el resultado sea verdadero, ya que "Ana" cumple con la condición.
     */
    public void testAnyMatch() {
        // Arrange (Configuración): Crear una lista de nombres
        List<String> lista = Arrays.asList("Juan", "Ana", "Carlos", "Elena");

        // Act (Acción): Verificar si al menos un elemento comienza con "A"
        boolean result = lista.stream().anyMatch(name -> name.startsWith("A"));

        // Assert (Afirmación): Verificar que al menos un elemento cumple con la condición
        assertTrue(result);
    }


    @Test
    /**
     * Prueba unitaria para verificar la suma de cuadrados en un Stream secuencial.
     * Se espera un tiempo de ejecución mayor debido al procesamiento secuencial.
     */
    public void testSumaCuadradosSecuencial() {
        // Arrange (Configuración): Crear una lista de números
        List<Integer> numeros = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11);

        // Act (Acción): Stream Secuencial con delay
        long inicioSecuencial = System.currentTimeMillis();
        double sumaSecuencial = numeros.stream()
                .mapToDouble(n -> {
                    try {
                        // Simular un procesamiento intensivo con un pequeño retraso
                        TimeUnit.MILLISECONDS.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    // Act (Acción): Elevar al cuadrado
                    return Math.pow(n, 2);
                })
                .sum();
        long finSecuencial = System.currentTimeMillis();

        // Assert (Afirmación): Verificar resultados y rendimiento
        System.out.println("Suma Secuencial: " + sumaSecuencial);
        System.out.println("Tiempo Secuencial: " + (finSecuencial - inicioSecuencial) + " ms");
        assertTrue((finSecuencial - inicioSecuencial) > 11000);
        assertEquals(sumaSecuencial, 506);
    }

    // Stream Paralelo

    @Test
    /**
     * Prueba unitaria para verificar la suma de cuadrados en un Stream paralelo.
     * Se espera un tiempo de ejecución menor debido al procesamiento paralelo.
     */
    public void testSumaCuadradosParalelo() {
        // Arrange (Configuración): Crear una lista de números
        List<Integer> numeros = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11);

        // Act (Acción): Stream Paralelo con delay
        long inicioParalelo = System.currentTimeMillis();
        double sumaParalela = numeros.parallelStream()
                .mapToDouble(n -> {
                    try {
                        // Simular un procesamiento intensivo con un pequeño retraso
                        TimeUnit.MILLISECONDS.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    // Act (Acción): Elevar al cuadrado
                    return Math.pow(n, 2);
                })
                .sum();
        long finParalelo = System.currentTimeMillis();

        // Assert (Afirmación): Verificar resultados y rendimiento
        System.out.println("Suma Paralela: " + sumaParalela);
        System.out.println("Tiempo Paralelo: " + (finParalelo - inicioParalelo) + " ms");
        assertTrue((finParalelo - inicioParalelo) < 2000);
        assertEquals(sumaParalela, 506);
    }
}
