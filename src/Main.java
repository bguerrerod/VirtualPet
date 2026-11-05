import java.util.Scanner;

// clase principal del juego
public class Main {

    // metodo principal
    public static void main(String[] args) {
        // objeto scanner para leer la entrada del usuario
        Scanner sc = new Scanner(System.in);

        // limpiar posibles animaciones iniciales en consola
        Animacion.limpiarPantalla();

        // mostrar bienvenida al jugador
        System.out.println("Bienvenido a la mascota virtual!");

        // variable para guardar el tipo seleccionado
        PetType tipoElegido = null;

        // bucle para pedir el tipo de mascota hasta que sea valido
        while (tipoElegido == null) {
            // solicitar elegir perro o gato
            System.out.print("Elige el tipo de mascota (perro / gato): ");
            // leer el valor ingresado por teclado
            String entradaTipo = sc.nextLine().trim().toLowerCase();

            // comprobar si escribio perro
            if (entradaTipo.equals("perro") || entradaTipo.equals("dog")) {
                // guardar el tipo perro
                tipoElegido = PetType.DOG;
            } else if (entradaTipo.equals("gato") || entradaTipo.equals("cat")) {
                // guardar el tipo gato
                tipoElegido = PetType.CAT;
            } else {
                // avisar que la seleccion no es valida
                System.out.println("Opcion no valida. Intenta perro o gato.");
            }
        }

        // solicitar el nombre de la mascota
        System.out.print("Introduce el nombre de la mascota: ");
        // leer el nombre ingresado
        String nombreMascota = sc.nextLine().trim();
        // si no escribe nada asignar nombre mascota
        if (nombreMascota.isEmpty()) {
            nombreMascota = "Mascota";
        }

        // crear la mascota principal con valores aleatorios
        Mascota miMascota = new Mascota(nombreMascota, tipoElegido);

        // crear la variable dummy vacia para esperar a que el jugador seleccione atacar
        Mascota dummy = null;

        // variable de control del bucle principal (si sigue jugando o no)
        boolean jugando = true;

        // bucle principal del menu
        while (jugando) {
            // mostrar las opciones del menu
            System.out.println("\nMENU DE OPCIONES:");
            System.out.println("1. Sleep (Dormir)");
            System.out.println("2. Play (Jugar)");
            System.out.println("3. Eat (Comer)");
            System.out.println("4. Shower (Duchar)");
            System.out.println("5. Attack (Atacar)");
            System.out.println("6. Dance (Bailar)");
            System.out.println("0. Salir");
            System.out.print("Selecciona una opcion: ");

            // leer la opcion del usuario
            String opcion = sc.nextLine().trim();

            // evaluar la opcion introducida
            switch (opcion) {
                case "1":
                    // ejecutar la accion dormir
                    miMascota.dormir();
                    miMascota.mostrarEstado();
                    break;

                case "2":
                    // ejecutar la accion jugar
                    miMascota.jugar();
                    miMascota.mostrarEstado();
                    break;

                case "3":
                    // ejecutar la accion comer
                    miMascota.comer();
                    miMascota.mostrarEstado();
                    break;

                case "4":
                    // ejecutar la accion duchar
                    miMascota.duchar();
                    miMascota.mostrarEstado();
                    break;

                case "5":
                    // preguntar la cantidad de ataques
                    System.out.print("Cuantas veces quieres atacar al objetivo?: ");
                    int veces = 1;
                    try {
                        // convertir la respuesta a numero entero
                        veces = Integer.parseInt(sc.nextLine().trim());
                    } catch (NumberFormatException e) {
                        // asignar uno si la entrada no es un numero
                        veces = 1;
                    }

                    // comprobar si existe el dummy
                    if (dummy == null) {
                        // crear el dummy con stats iniciales a cien
                        dummy = new Mascota("Dummy", PetType.DUMMY);
                    }

                    // ejecutar el bucle de ataques
                    miMascota.atacar(dummy, veces);

                    // comprobar si las estadisticas del dummy cayeron a cero
                    if (!dummy.estaViva()) {
                        // avisar que el dummy ha muerto
                        System.out.println("\nEl enemigo ha muerto!");
                        // mostrar solo las estadisticas de nuestra mascota
                        miMascota.mostrarEstado();
                        // reiniciar el dummy a cien por si vuelve a atacar
                        dummy.reiniciarDummy();
                    } else {
                        // mostrar las estadisticas del dummy
                        System.out.println("\nEstado del objetivo Dummy:");
                        dummy.mostrarEstado();
                        // mostrar las estadisticas de tu mascota
                        System.out.println("Estado de tu mascota:");
                        miMascota.mostrarEstado();
                    }
                    break;

                case "6":
                    // ejecutar la nueva accion bailar
                    miMascota.bailar();
                    // mostrar las estadisticas actualizadas
                    miMascota.mostrarEstado();
                    break;

                case "0":
                    // imprimir el mensaje final
                    System.out.println("\nGracias por jugar!");
                    // finalizar el bucle
                    jugando = false;
                    continue;

                default:
                    // notificar opcion incorrecta
                    System.out.println("Opcion no valida. Intentalo de nuevo.");
                    continue;
            }

            // comprobar la regla del diagrama: stats a cero o iguales/superiores a cien
            if (!miMascota.estaViva()) {
                // imprimir pantalla de game over
                System.out.println("\nGAME OVER: Tu mascota ha muerto.");
                // finalizar el juego
                jugando = false;
            }
        }

        // cerrar el objeto scanner
        sc.close();
    }
}
