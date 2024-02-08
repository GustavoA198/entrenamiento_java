package entrenamiento;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

/**
 * Esta clase contiene pruebas unitarias para aprender a utilizar Flux y Mono de Project Reactor.
 * Cada método de prueba demuestra un caso de uso diferente.
 */
public class ReactorTest {

    @Test
    /**
     * Prueba unitaria para verificar el uso de Mono.just.
     * Se espera que el resultado sea "Juan".
     */
    public void testMonoJust() {
        Mono<String> mono = Mono.just("Juan");

        //En esta prueba, se crea un nuevo StepVerifier que se adjunta al Mono que se pasa como argumento.
        //Luego, se establece una expectativa de que el próximo elemento emitido por el Mono será "Juan".
        //Si el Mono emite algo diferente, la prueba fallará.
        //Finalmente, se verifica que el Mono ha completado su emisión de elementos.
        //Si el Mono emite más elementos después de "Juan" o si no completa en absoluto, la prueba fallará
        StepVerifier.create(mono)
                .expectNext("Juan")
                .verifyComplete();
    }

    @Test
    /**
     * Prueba unitaria para verificar el uso de Flux.just.
     * Se espera que el resultado sea "Juan", "Ana", "Carlos", "Elena".
     */
    public void testFluxJust() {
        Flux<String> flux = Flux.just("Juan", "Ana", "Carlos", "Elena");

        StepVerifier.create(flux)
                .expectNext("Juan", "Ana", "Carlos", "Elena")
                .verifyComplete();
    }

    @Test
    /**
     * Prueba unitaria para verificar el uso de Flux.fromIterable.
     * Se espera que el resultado sea "Juan", "Ana", "Carlos", "Elena".
     */
    public void testFluxFromIterable() {
        List<String> nombres = Arrays.asList("Juan", "Ana", "Carlos", "Elena");
        Flux<String> flux = Flux.fromIterable(nombres);

        StepVerifier.create(flux)
                .expectNext("Juan", "Ana", "Carlos", "Elena")
                .verifyComplete();
    }

    @Test
    /**
     * Prueba unitaria para verificar el uso de Flux.map.
     * Se espera que el resultado sea "JUAN", "ANA", "CARLOS", "ELENA".
     */
    public void testFluxMap() {
        Flux<String> flux = Flux.just("Juan", "Ana", "Carlos", "Elena")
                .map(String::toUpperCase);

        StepVerifier.create(flux)
                .expectNext("JUAN", "ANA", "CARLOS", "ELENA")
                .verifyComplete();
    }

    @Test
    /**
     * Prueba unitaria para verificar el uso de Flux.filter.
     * Se espera que el resultado sea "Ana".
     */
    public void testFluxFilter() {
        Flux<String> flux = Flux.just("Juan", "Ana", "Carlos", "Elena")
                .filter(nombre -> nombre.startsWith("A"));

        StepVerifier.create(flux)
                .expectNext("Ana")
                .verifyComplete();
    }

    @Test
    /**
     * Prueba unitaria para verificar el uso de Flux.delayElements y Flux.map.
     * Se espera que el resultado sea 1, 4, 9, 16, 25, 36, 49, 64, 81, 100, 121.
     */
    public void testFluxDelayElements() {
        Flux<Integer> flux = Flux.just(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11)
                .delayElements(Duration.ofMillis(1000))
                .map(n -> n * n);

        StepVerifier.create(flux)
                .expectNext(1, 4, 9, 16, 25, 36, 49, 64, 81, 100, 121)
                .verifyComplete();
    }

    @Test
    public void testFluxConcatWith(){
        Flux<String> flux1 = Flux.just("Juan", "Ana");
        Flux<String> flux2 = Flux.just("Carlos", "Elena");
        Flux<String> flux = flux1.concatWith(flux2);

        StepVerifier.create(flux)
                .expectNext("Juan", "Ana", "Carlos", "Elena")
                .verifyComplete();
    }

    @Test
    public void testFluxMergeWith(){
        Flux<String> flux1 = Flux.just("Juan", "Ana").delayElements(Duration.ofMillis(1000));
        Flux<String> flux2 = Flux.just("Carlos", "Elena");
        Flux<String> flux = flux1.mergeWith(flux2);

        StepVerifier.create(flux)
                .expectNext("Carlos", "Elena", "Juan", "Ana")
                .verifyComplete();
    }

    @Test
    public void testFluxZipWith(){
        Flux<String> flux1 = Flux.just("Juan", "Ana");
        Flux<String> flux2 = Flux.just("Carlos", "Elena");
        Flux<String> flux = flux1.zipWith(flux2, (f1, f2) -> f1 + " - " + f2);

        StepVerifier.create(flux)
                .expectNext("Juan - Carlos", "Ana - Elena")
                .verifyComplete();
    }

}