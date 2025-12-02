# PSP-DAM-ACTEVA03B-java
## Simulación de cocina con hilos en Java

Este proyecto reproduce el funcionamiento de una cocina de restaurante usando hilos en Java. Tres cocineros comparten una lista de pedidos, los procesan de forma sincronizada y registran cada acción en un archivo de log.

## Descripción general

La clase Cocina genera una lista compartida de Pedido y lanza 3 hilos Cocinero.

Cada Cocinero:

Extrae pedidos de la lista usando synchronized.

“Prepara” cada pedido con un tiempo aleatorio entre 1 y 3 segundos.

Muestra avances por consola.

Registra la actividad en el archivo log_pedidos.txt.

El programa finaliza cuando no quedan pedidos por procesar y los hilos terminan su ejecución.

Estructura de clases (paquete restaurante)
## Pedido

Atributos: id, nombrePlato
Representa un pedido individual.
toString() devuelve:
Pedido #id: nombrePlato

## Cocinero (extends Thread)

Atributos:

nombre

List<Pedido> listaPedidos

run():

Extrae un pedido desde listaPedidos dentro de un bloque synchronized.

Llama a prepararPedido(pedido).

prepararPedido(Pedido):

Muestra cuándo empieza y termina la preparación.

Duerme entre 1 y 3 segundos.

Llama a registrarEnLog(pedido).

registrarEnLog(Pedido):

Escribe fecha, hora, cocinero y pedido en log_pedidos.txt.

Usa sincronización sobre la constante ARCHIVO_LOG.

## Cocina (clase principal)

Constantes:
ARCHIVO_LOG = "log_pedidos.txt"

main():

Llama a inicializarLog() para limpiar y crear el log con cabecera.

Crea la lista compartida de Pedido.

Muestra “Inicio del servicio”.

Crea tres hilos Cocinero (1, 2 y 3).

Arranca los hilos con start().

Usa join() para esperar a que finalicen.

Muestra un mensaje final indicando que no quedan pedidos.

inicializarLog():

Elimina el log previo.

Crea uno nuevo con fecha/hora usando LocalDateTime y DateTimeFormatter.

Requisitos

Java 8 o superior

Todas las clases dentro del paquete restaurante/

Compilación
javac restaurante/*.java

Ejecución
java restaurante.Cocina

## Salida esperada

Mensaje de inicio del servicio.

El nombre del cocinero que prepara cada pedido.

Mensaje final cuando no quedan pedidos.

Archivo log_pedidos.txt con todas las preparaciones registradas.
