package restaurante;

//=====================================================
//ARCHIVO 3: Cocina.java (CLASE PRINCIPAL)
//=====================================================

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Cocina {
 private static final String ARCHIVO_LOG = "log_pedidos.txt";
 private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

 public static void main(String[] args) {
     // Inicializar el archivo log
     inicializarLog();

     // Crear lista de pedidos compartida
     List<Pedido> listaPedidos = new ArrayList<>();
     listaPedidos.add(new Pedido(1, "Paella Valenciana"));
     listaPedidos.add(new Pedido(2, "Tortilla de Patatas"));
     listaPedidos.add(new Pedido(3, "Gazpacho Andaluz"));
     listaPedidos.add(new Pedido(4, "Pulpo a la Gallega"));
     listaPedidos.add(new Pedido(5, "Croquetas de Jamón"));
     listaPedidos.add(new Pedido(6, "Fabada Asturiana"));

     System.out.println("=== INICIO DEL SERVICIO EN LA COCINA ===");
     System.out.println("Total de pedidos: " + listaPedidos.size());
     System.out.println("=========================================\n");

     // Crear cocineros (hilos)
     Cocinero cocinero1 = new Cocinero("Cocinero 1", listaPedidos);
     Cocinero cocinero2 = new Cocinero("Cocinero 2", listaPedidos);
     Cocinero cocinero3 = new Cocinero("Cocinero 3", listaPedidos);

     // Iniciar los hilos
     cocinero1.start();
     cocinero2.start();
     cocinero3.start();

     // Esperar a que todos los cocineros terminen
     try {
         cocinero1.join();
         cocinero2.join();
         cocinero3.join();
     } catch (InterruptedException e) {
         System.err.println("Error esperando a los cocineros: " + e.getMessage());
         Thread.currentThread().interrupt();
     }

     // Mensaje final
     System.out.println("\n=========================================");
     System.out.println("Todos los pedidos han sido procesados.");
     System.out.println("=========================================");
 }

 private static void inicializarLog() {
     try {
         File archivo = new File(ARCHIVO_LOG);
         if (archivo.exists()) {
             archivo.delete();
         }
         
         try (PrintWriter writer = new PrintWriter(new FileWriter(ARCHIVO_LOG, true))) {
             String timestamp = LocalDateTime.now().format(formatter);
             writer.println("=== LOG DE PEDIDOS - INICIO: " + timestamp + " ===");
             writer.println();
         }
     } catch (IOException e) {
         System.err.println("Error al inicializar el log: " + e.getMessage());
     }
 }
}