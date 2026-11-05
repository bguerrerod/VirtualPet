import java.util.Random;

// clase que define la mascota y sus acciones
public class Mascota {
    // atributos principales para almacenar los estados o datos de la mascota
    private String nombre;
    private PetType tipo;
    private int energia;
    private int higiene;
    private int peso;
    private int animo;

    // constructor para inicializar la mascota segun su tipo
    public Mascota(String nombre, PetType tipo) {
        this.nombre = nombre;
        this.tipo = tipo;

        // si es dummy se establecen todos los atributos en cien
        if (tipo == PetType.DUMMY) {
            this.energia = 100;
            this.higiene = 100;
            this.peso = 100;
            this.animo = 100;
        } else {
            // para dog o cat se asigna un valor aleatorio entre cuarenta y sesenta
            Random rand = new Random();
            this.energia = rand.nextInt(21) + 40;
            this.higiene = rand.nextInt(21) + 40;
            this.peso = rand.nextInt(21) + 40;
            this.animo = rand.nextInt(21) + 40;
        }
    }

    // metodo para comprobar si la mascota continua viva
    public boolean estaViva() {
        // si cualquier atributo cae a cero o alcanza o supera cien muere
        if (energia <= 0 || energia >= 100) return false;
        if (higiene <= 0 || higiene >= 100) return false;
        if (peso <= 0 || peso >= 100) return false;
        if (animo <= 0 || animo >= 100) return false;
        return true;
    }

    // metodo para mostrar el estado actual de los atributos
    public void mostrarEstado() {
        System.out.println("--- ESTADO DE " + nombre.toUpperCase() + " (" + tipo + ") ---");
        System.out.println("Energia: " + energia);
        System.out.println("Higiene: " + higiene);
        System.out.println("Peso: " + peso);
        System.out.println("Animo: " + animo);
        System.out.println("---------------------------------------");
    }

    // metodo para la opcion uno dormir
    public void dormir() {
        // reproducir animacion de dormir
        Animacion.reproducir("dormir", tipo);
        // incrementar el animo en diez puntos
        animo += 10;
        // ajustar energia segun el tipo de mascota
        if (tipo == PetType.DOG) {
            energia += 25;
        } else if (tipo == PetType.CAT) {
            energia += 20;
        }
        // mostrar la frase descriptiva con las estadisticas actualizadas
        System.out.println(nombre + " ha dormido bien. Energia actual: " + energia + " Animo actual: " + animo);
    }

    // metodo para la opcion dos jugar
    public void jugar() {
        // reproducir animacion de jugar
        Animacion.reproducir("jugar", tipo);
        // reducir la energia en veinte puntos
        energia -= 20;
        // incrementar el animo en veinte puntos
        animo += 20;
        // reducir el peso en diez puntos
        peso -= 10;
        // mostrar la frase descriptiva con las estadisticas actualizadas
        System.out.println(nombre + " se ha divertido jugando. Animo: " + animo + " Energia: " + energia + " Peso: " + peso);
    }

    // metodo para la opcion tres comer
    public void comer() {
        // reproducir animacion de comer
        Animacion.reproducir("comer", tipo);
        // incrementar la energia en diez puntos
        energia += 10;
        // reducir la higiene en quince puntos
        higiene -= 15;
        // incrementar el peso en diez puntos
        peso += 10;
        // mostrar la frase descriptiva con las estadisticas actualizadas
        System.out.println(nombre + " ha comido delicioso. Energia: " + energia + " Peso: " + peso + " Higiene: " + higiene);
    }

    // metodo para la opcion cuatro duchar
    public void duchar() {
        // reproducir animacion de duchar
        Animacion.reproducir("duchar", tipo);
        // ajustar atributos segun el tipo de mascota
        if (tipo == PetType.DOG) {
            energia -= 10;
            higiene += 25;
            animo -= 15;
        } else if (tipo == PetType.CAT) {
            energia -= 5;
            higiene += 25;
            animo += 5;
        }
        // mostrar la frase descriptiva con las estadisticas actualizadas
        System.out.println(nombre + " se ha tomado una ducha. Higiene: " + higiene + " Energia: " + energia + " Animo: " + animo);
    }

    // metodo para la opcion cinco atacar
    public void atacar(Mascota dummy, int veces) {
        // reproducir animacion de atacar
        Animacion.reproducir("atacar", tipo);
        // bucle para repetir el ataque la cantidad de veces que indique el usuario
        for (int i = 0; i < veces; i++) {
            this.energia -= 3;
            this.higiene -= 3;
            this.animo -= 3;

            dummy.energia -= 3;
            dummy.higiene -= 3;
            dummy.animo -= 3;
        }
        // mostrar mensaje informativo de los ataques
        System.out.println(nombre + " ataco " + veces + " veces al dummy");
    }

    // metodo para reiniciar las estadisticas del dummy a cien
    public void reiniciarDummy() {
        this.energia = 100;
        this.higiene = 100;
        this.peso = 100;
        this.animo = 100;
    }

    // getter para obtener el nombre
    public String getNombre() {
        return nombre;
    }

    // getter para obtener el tipo
    public PetType getTipo() {
        return tipo;
    }

    // getter para obtener la energia
    public int getEnergia() {
        return energia;
    }

    // getter para obtener la higiene
    public int getHigiene() {
        return higiene;
    }

    // getter para obtener el peso
    public int getPeso() {
        return peso;
    }

    // getter para obtener el animo
    public int getAnimo() {
        return animo;
    }
}