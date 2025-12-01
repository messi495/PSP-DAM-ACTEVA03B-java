# PSP-DAM-ACTEVA03B-java
Simulación de cocina con hilos en Java
Este proyecto simula el trabajo de una cocina de restaurante usando hilos en Java. Varios cocineros comparten una lista de pedidos, los van sacando de forma segura y registran el trabajo en un archivo de log.

Descripción general
Cocina crea una lista de Pedido y arranca 3 hilos Cocinero.

Cada Cocinero toma pedidos de la lista compartida (con synchronized), los “prepara” durante 1–3 segundos y escribe en consola y en un archivo log_pedidos.txt.

El programa termina cuando todos los pedidos han sido procesados y todos los hilos finalizan.

Estructura de clases (paquete restaurante)
Pedido

Atributos: id, nombrePlato.

Representa un pedido de cocina.

toString() devuelve: Pedido #id: nombrePlato.

Cocinero (extiende Thread)

Atributos: nombre, List<Pedido> listaPedidos.

En run():

Extrae pedidos de listaPedidos dentro de un bloque synchronized.

Llama a prepararPedido(pedido).

prepararPedido(Pedido):

Muestra por consola que empieza y termina el pedido.

Duerme entre 1 y 3 segundos.

Llama a registrarEnLog(pedido).

registrarEnLog(Pedido):

Escribe una línea con fecha, hora, cocinero y pedido en log_pedidos.txt usando sincronización sobre ARCHIVO_LOG.

Cocina (clase principal)

Constantes:

ARCHIVO_LOG = "log_pedidos.txt".

main(String[] args):

Llama a inicializarLog() (borra el archivo si existe y escribe cabecera).

Crea la lista compartida de Pedido con varios platos.

Muestra inicio del servicio.

Crea 3 instancias Cocinero (Cocinero 1, 2 y 3) con la misma lista.

Llama a start() en cada hilo.

Espera con join() a que terminen.

Muestra mensaje final de que todos los pedidos han sido procesados.

inicializarLog():

Elimina el log antiguo (si existe) y crea uno nuevo con una línea de inicio con LocalDateTime y DateTimeFormatter.

Requisitos
Java 8 o superior.

Todas las clases en el paquete restaurante (carpeta restaurante/).

Compilación
Desde el directorio raíz del proyecto:

bash
javac restaurante/*.java
Ejecución
bash
java restaurante.Cocina
Se mostrará en consola:

Inicio del servicio.

Qué cocinero está preparando y completando cada Pedido.

Mensaje final cuando no quedan pedidos.

En el archivo log_pedidos.txt se registran los pedidos procesados con marca de tiempo y nombre del cocinero.
