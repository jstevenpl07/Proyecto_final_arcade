package ProyectoFinalArcade;

import java.util.Scanner;

public class casino {
    
    static Scanner sc =
        new Scanner(System.in);

    static jugador jugador1;

    static int bonusTurnos = 0;

    public static void iniciar() {

        System.out.print(
            "Ingrese nombre: ");

        String nombre =
            sc.nextLine();

        jugador1 =
            archivocsv.cargarJugador(nombre);

        if (jugador1 == null) {

            jugador1 =
                new jugador(nombre, 1000);

            archivocsv.guardarJugador(
                jugador1
            );

            System.out.println(
                "Jugador creado!"
            );
        }
        mostrarTitulo();
        menu();
    }
    public static void mostrarTitulo() {

    System.out.println(

            utilidades.CYAN +

    "████████╗██╗  ██╗███████╗\n" +
    "╚══██╔══╝██║  ██║██╔════╝\n" +
    "   ██║   ███████║█████╗  \n" +
    "   ██║   ██╔══██║██╔══╝  \n" +
    "   ██║   ██║  ██║███████╗\n" +
    "   ╚═╝   ╚═╝  ╚═╝╚══════╝\n" +
    "██╗     ██╗   ██╗ ██████╗██╗  ██╗██╗   ██╗\n" +
    "██║     ██║   ██║██╔════╝██║ ██╔╝╚██╗ ██╔╝\n" +
    "██║     ██║   ██║██║     █████╔╝  ╚████╔╝ \n" +
    "██║     ██║   ██║██║     ██╔═██╗   ╚██╔╝  \n" +
    "███████╗╚██████╔╝╚██████╗██║  ██╗   ██║   \n" +
    "╚══════╝ ╚═════╝  ╚═════╝╚═╝  ╚═╝   ╚═╝   \n" +

    " ██████╗ █████╗ ███████╗██╗███╗   ██╗ ██████╗ \n" +
    "██╔════╝██╔══██╗██╔════╝██║████╗  ██║██╔═══██╗\n" +
    "██║     ███████║███████╗██║██╔██╗ ██║██║   ██║\n" +
    "██║     ██╔══██║╚════██║██║██║╚██╗██║██║   ██║\n" +
    "╚██████╗██║  ██║███████║██║██║ ╚████║╚██████╔╝\n" +
    " ╚═════╝╚═╝  ╚═╝╚══════╝╚═╝╚═╝  ╚═══╝ ╚═════╝ \n"
            

            + utilidades.RESET
            );
    }

    public static void menu() {

        int opcion = 0;

        while (opcion != 5) {

            System.out.println(
                " [1] Apostar");

            System.out.println(
                " [2] Ver saldo");

            System.out.println(
                " [3] Ver Ranking");

            System.out.println(
                " [4] Ingresar Saldo");
            
            System.out.println(
                " [5] Salir");

            System.out.print("> ");
            opcion = sc.nextInt();

            switch (opcion) {

                case 1:
                    apostar();
                    break;

                case 2:
                    System.out.println(

                    utilidades.VERDE
                    + "Saldo actual: $"
                    + jugador1.getSaldo()
                    + utilidades.RESET
                );
                    break;

                case 3:
                    System.out.println(
                        "Ranking próximamente"
                    );
                    break;

                case 4:

                    ingresarDinero();

                    break;

                case 5:

                    System.out.println(
                        "Gracias por jugar!"
                    );

                    break;
            }
        }
    }

        public static void apostar() {

           System.out.print(
            "Ingrese apuesta:\n> "
            );

            if (!sc.hasNextInt()) {

                System.out.println(

                    utilidades.ROJO +

                    "Ingrese un numero valido"

                    + utilidades.RESET
                );

                sc.nextLine();

                return;
            }

            int apuesta = sc.nextInt();

            sc.nextLine();

            if (

                apuesta == 777

                ||

                jugador1.getNombre()
                .equalsIgnoreCase("LUCKY")

            ) {

                bonusTurnos = 3;

                System.out.println(

                    utilidades.AMARILLO +

                    "\n AMULETO ACTIVADO \n"
                    +
                    "Premios dobles por 3 turnos!\n"

                    + utilidades.RESET
                );
            }

            if (apuesta > jugador1.getSaldo()) {

                System.out.println(
                utilidades.ROJO
                + "Saldo insuficiente"
                + utilidades.RESET
            );

                return;
            }

            jugador1.setSaldo(
                jugador1.getSaldo()
                - apuesta
            );

            String[][] matriz =
                new String[3][3];

            slotmachine.animarGiro(matriz);

            int premio =
                slotmachine.calcularPremio(
                    matriz,
                    apuesta
                );

            if (bonusTurnos > 0) {

            premio *= 2;

            bonusTurnos--;

            System.out.println(

                utilidades.MORADO +

                " BONUS x2 ACTIVADO "

                + utilidades.RESET
            );

            System.out.println(

                "Turnos restantes: "
                + bonusTurnos
            );
        }

            jugador1.setSaldo(
                jugador1.getSaldo()
                + premio
            );

            archivocsv.actualizarJugador(
                jugador1
            );

            System.out.println(
            utilidades.VERDE
            + "Ganaste: "
            + premio
            + utilidades.RESET
        );

                if (premio >= apuesta * 10) {

            System.out.println(

            utilidades.AMARILLO +

                "\n JACKPOT!!\n"

                + utilidades.RESET
            );
        }

            System.out.println(

                    utilidades.VERDE
                    + "Saldo actual: $"
                    + jugador1.getSaldo()
                    + utilidades.RESET
                );
           System.out.println();

            System.out.println(
                "[1] Volver a apostar"
            );

            System.out.println(
                "[2] Ver saldo"
            );

            System.out.println(
                "[3] Ranking"
            );

            System.out.println(
                "[4] Ingresar dinero"
            );

            System.out.println(
                "[5] Salir"
            );

            System.out.print("> ");

            if (!sc.hasNextInt()) {

                System.out.println(

                    utilidades.ROJO +

                    "Ingrese un numero valido"

                    + utilidades.RESET
                );

                sc.nextLine();

                return;
            }

            int opcion = sc.nextInt();

            sc.nextLine();

            switch (opcion) {

                case 1:

                    apostar();

                    break;

                case 2:

                    System.out.println(

                        utilidades.VERDE +

                        "Saldo actual: $"

                        + jugador1.getSaldo()

                        + utilidades.RESET
                    );

                    break;

                case 3:

                    archivocsv.mostrarRanking();

                    break;

                case 4:

                    ingresarDinero();

                    break;

                case 5:

                    System.out.println(
                        "Gracias por jugar!"
                    );

                    System.exit(0);

                default:

                    System.out.println(

                        utilidades.ROJO +

                        "Opcion invalida"

                        + utilidades.RESET
                    );
            }
        }   
        public static void ingresarDinero() {

            System.out.print(
                "Cantidad a ingresar:\n> "
            );

            if (!sc.hasNextInt()) {

                System.out.println(

                    utilidades.ROJO +

                    "Ingrese un numero valido"

                    + utilidades.RESET
                );

                sc.nextLine();

                return;
            }

            int dinero = sc.nextInt();

            sc.nextLine();

            if (dinero <= 0) {

                System.out.println(

                    utilidades.ROJO +

                    "Cantidad invalida"

                    + utilidades.RESET
                );

                return;
            }

            jugador1.setSaldo(

                jugador1.getSaldo()
                + dinero
            );

            archivocsv.actualizarJugador(
                jugador1
            );

            System.out.println(

                utilidades.VERDE +

                "Dinero agregado correctamente"

                + utilidades.RESET
            );

            System.out.println(

                "Saldo actual: $"
                + jugador1.getSaldo()
            );
        }
}