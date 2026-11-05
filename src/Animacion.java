// clase para gestionar el borrado de pantalla y animaciones
public class Animacion {

    // metodo para limpiar la pantalla de la consola
    public static void limpiarPantalla() {
        try {
            // comprobar si el sistema operativo es windows
            if (System.getProperty("os.name").contains("Windows")) {
                // borrar la pantalla en sistema windows
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                // borrar la pantalla en sistema unix o mac
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (Exception e) {
            // salto de linea si falla la limpieza
            System.out.println();
        }
    }

    // metodo para pausar la ejecucion un tiempo especificado
    public static void pausar(int ms) {
        try {
            // congelar el hilo durante los milisegundos indicados
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            // restaurar el estado de interrupcion del hilo
            Thread.currentThread().interrupt();
        }
    }

    // metodo para reproducir la animacion por fotogramas
    public static void reproducir(String accion, PetType tipo) {
        // obtener los fotogramas de la accion correspondiente
        String[] frames = getFrames(accion, tipo);
        // dibujar cada fotograma en pantalla
        if (frames != null) {
            for (String frame : frames) {
                // limpiar la pantalla antes del fotograma
                limpiarPantalla();
                // imprimir el fotograma actual
                System.out.println(frame);
                // esperar un segundo entre fotogramas
                pausar(1000);
            }
        }
    }

    // metodo para obtener los fotogramas segun la accion y tipo
    public static String[] getFrames(String accion, PetType tipo) {
        // convertir el texto de la accion a minusculas
        String act = accion.toLowerCase();

        // fotogramas para la accion dormir
        if (act.equals("dormir")) {
            if (tipo == PetType.CAT) {
                return new String[]{
                    "  /\\_/\\  \n ( z z )  z Z Z\n =( I )=\n\n [ Durmiendo... ]",
                    "  /\\_/\\  \n ( - - )  Z z z\n =( I )=\n\n [ Durmiendo... ]"
                };
            } else {
                return new String[]{
                    "  /^_\\^\\ \n ( z z  )  z Z Z\n (  --  )\n\n [ Durmiendo... ]",
                    "  /^_\\^\\ \n ( - -  )  Z z z\n (  --  )\n\n [ Durmiendo... ]"
                };
            }
        }

        // fotogramas para la accion jugar
        if (act.equals("jugar")) {
            if (tipo == PetType.CAT) {
                return new String[]{
                    "  /\\_/\\    o\n ( o o )  /\n =( u )=\n\n [ ¡Jugando! ]",
                    "  /\\_/\\      o\n ( ^ ^ )    /\n =( w )=   /\n\n [ ¡Jugando! ]"
                };
            } else {
                return new String[]{
                    "  /^_\\^\\   o\n ( o o  ) /\n (  u   )\n\n [ ¡Jugando! ]",
                    "  /^_\\^\\    o\n ( ^ ^  )  /\n (  w   ) /\n\n [ ¡Jugando! ]"
                };
            }
        }

        // fotogramas para la accion comer
        if (act.equals("comer")) {
            if (tipo == PetType.CAT) {
                return new String[]{
                    "  /\\_/\\ \n ( o o ) \n =( u )=   \\_/\n\n [ Comiendo... ]",
                    "  /\\_/\\ \n ( v v ) \n =(nom)=   \\_/\n\n [ ¡Ñam ñam! ]"
                };
            } else {
                return new String[]{
                    "  /^_\\^\\ \n ( o o  ) \n (  u   )  \\_/\n\n [ Comiendo... ]",
                    "  /^_\\^\\ \n ( v v  ) \n ( nom  )  \\_/\n\n [ ¡Ñam ñam! ]"
                };
            }
        }

        // fotogramas para la accion duchar
        if (act.equals("duchar")) {
            if (tipo == PetType.CAT) {
                return new String[]{
                    "    ~~~ 🛁 ~~~\n     .  :  . \n  /\\_/\\ \n ( > < ) \n =( i )=\n\n [ ¡Agua no! ]",
                    "    ~~~ 🛁 ~~~\n     :  .  :\n  /\\_/\\ \n ( ^ w ^ )\n =( u )=\n\n [ Lavando... ]"
                };
            } else {
                return new String[]{
                    "    ~~~ 🛁 ~~~\n     .  :  . \n  /^_\\^\\ \n ( > <  )\n (  u   )\n\n [ Ducha rápida... ]",
                    "    ~~~ 🛁 ~~~\n     :  .  :\n  /^_\\^\\ \n ( ^ ^  )\n (  w   )\n\n [ ¡Limpio! ]"
                };
            }
        }

        // fotogramas para la accion atacar
        if (act.equals("atacar")) {
            return new String[]{
                " (Tu mascota)          (Dummy)\n  /^_\\^\\     ===>      [ O_O ]\n ( O O  )              |     |\n (  w   )              |_____|\n\n [ ¡Preparando ataque! ]",
                " (Tu mascota)          (Dummy)\n  /^_\\^\\   💥 BAM!    [ X_X ]\n ( > <  ) =======>     |     |\n (  u   )              |_____|\n\n [ ¡Ataque directo! ]"
            };
        }

        // retornar nulo si no coincide ninguna accion
        return null;
    }
}