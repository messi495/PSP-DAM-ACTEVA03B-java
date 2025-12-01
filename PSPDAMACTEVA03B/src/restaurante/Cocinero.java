package restaurante;

//=====================================================
//ARCHIVO 2: Cocinero.java
//=====================================================

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Cocinero extends Thread {
 private String nombre;
 private List<Pedido> listaPedidos;
 private static final String ARCHIVO_LOG = "log_pedidos.txt";
 private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

 public Cocinero(String nombre, List<Pedido> listaPedidos) {
     this.nombre = nombre;
     this.listaPedidos = listaPedidos;
 }

 @Override
 public void run() {
     while (true) {
         Pedido pedido = null;

         // Sincronización para acceder a la lista de pedidos
         synchronized (listaPedidos) {
             if (!listaPedidos.isEmpty()) {
                 pedido = listaPedidos.remove(0);
             } else {
                 break; // No hay más pedidos
             }
         }

         if (pedido != null) {
             prepararPedido(pedido);
         }
     }
 }

 private void prepararPedido(Pedido pedido) {
     try {
         // Mostrar en consola
         String mensaje = nombre + " está preparando " + pedido;
         System.out.println(mensaje);

         // Simular tiempo de preparación
         Thread.sleep((long) (Math.random() * 2000 + 1000)); // Entre 1 y 3 segundos

         String mensajeCompletado = nombre + " ha completado " + pedido;
         System.out.println(mensajeCompletado);

         // Registrar en el archivo (sincronizado)
         registrarEnLog(pedido);

     } catch (InterruptedException e) {
         System.err.println(nombre + " fue interrumpido");
         Thread.currentThread().interrupt();
     }
 }

 private void registrarEnLog(Pedido pedido) {
     // Sincronizar el acceso al archivo
     synchronized (ARCHIVO_LOG) {
         try (PrintWriter writer = new PrintWriter(new FileWriter(ARCHIVO_LOG, true))) {
             String timestamp = LocalDateTime.now().format(formatter);
             String registro = String.format("[%s] %s procesó %s%n", 
                                            timestamp, nombre, pedido);
             writer.print(registro);
         } catch (IOException e) {
             System.err.println("Error al escribir en el log: " + e.getMessage());
         }
     }
 }
}
