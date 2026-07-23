package org.example;

import org.example.domain.Championship;
import org.example.domain.Driver;
import org.example.service.ChampionshipService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.List;

@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        // Arrancamos el motor de Spring Boot
        ApplicationContext context = SpringApplication.run(Main.class, args);

        System.out.println("\n🏁 Arrancando Sistema del Turismo Carretera...");

        // Le pedimos a Spring que nos preste tu nuevo servicio
        ChampionshipService campeonatoService = context.getBean(ChampionshipService.class);

        try {
            // 1. Inicializamos el campeonato en la base de datos
            Championship torneo = campeonatoService.inicializarNuevoCampeonato();
            Long torneoId = torneo.getId();
            System.out.println("✅ Campeonato creado con éxito (ID: " + torneoId + ").");

            // 2. Inscribimos los 15 pilotos generados automáticamente
            campeonatoService.generarEInscribirPilotosDePrueba(torneoId);
            System.out.println("✅ 15 Pilotos generados e inscriptos en el torneo.");

            // 3. Clasificamos a la Copa de Oro (los mejores 12)
            campeonatoService.ejecutarClasificacionCopaDeOro(torneoId);
            System.out.println("🏆 Clasificados a la Copa de Oro definidos.");

            // 4. Agregamos a los 3 de Último Minuto
            campeonatoService.ejecutarClasificacionUltimoMinuto(torneoId);
            System.out.println("⏱️ 3 Pilotos de Último Minuto definidos.");

            // 5. Mostramos los resultados finales por consola
            List<Driver> clasificados = campeonatoService.obtenerClasificadosCopaDeOro(torneoId);
            System.out.println("\n--- 🏁 PILOTOS EN LA COPA DE ORO 🏁 ---");
            for (Driver piloto : clasificados) {
                System.out.println("- " + piloto.getName() + " (Puntos: " + piloto.getChampionshipPoints() + ")");
            }

            System.out.println("\n✅ Ejecución finalizada con éxito.");

        } catch (Exception e) {
            System.out.println("❌ Hubo un error en la simulación: " + e.getMessage());
        }
    }
}
