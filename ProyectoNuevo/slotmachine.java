package ProyectoFinalArcade;

public class slotmachine {

    static String[] simbolos = {
        "7", "X", "$",
        "*", "@", "#"
    };

    public static void girar(
            String[][] matriz) {

        for (int i = 0; i < 3; i++) {

            for (int j = 0; j < 3; j++) {

                int random =
                    (int)(Math.random()
                    * simbolos.length);

                matriz[i][j] =
                    simbolos[random];
            }
        }
    }

    public static void dibujar(
            String[][] matriz) {

        System.out.println(
            "╔═══╦═══╦═══╗");

        for (int i = 0; i < 3; i++) {

            System.out.print("║ ");

            for (int j = 0; j < 3; j++) {

                System.out.print(
                    matriz[i][j] + " ║ "
                );
            }

            System.out.println();

            if (i < 2) {

                System.out.println(
                    "╠═══╬═══╬═══╣"
                );
            }
        }

        System.out.println(
            "╚═══╩═══╩═══╝"
        );
    }

        public static int calcularPremio(
                String[][] matriz,
                int apuesta) {

            int premio = 0;

            int lineasGanadas = 0;

            // horizontales
            for (int i = 0; i < 3; i++) {

                if (
                    matriz[i][0].equals(
                    matriz[i][1])
                    &&
                    matriz[i][1].equals(
                    matriz[i][2])
                ) {

                    lineasGanadas++;

                    premio += obtenerValor(
                        matriz[i][0],
                        apuesta
                    );
                }
            }

            // diagonal principal
            if (
                matriz[0][0].equals(
                matriz[1][1])
                &&
                matriz[1][1].equals(
                matriz[2][2])
            ) {

                lineasGanadas++;

                premio += obtenerValor(
                    matriz[0][0],
                    apuesta
                );
            }

            // diagonal secundaria
            if (
                matriz[0][2].equals(
                matriz[1][1])
                &&
                matriz[1][1].equals(
                matriz[2][0])
            ) {

                lineasGanadas++;

                premio += obtenerValor(
                    matriz[0][2],
                    apuesta
                );
            }

            // multiplicador
            if (lineasGanadas >= 2) {

                premio *= lineasGanadas;

                System.out.println(

                    utilidades.MORADO
                    + "MULTIPLICADOR x"
                    + lineasGanadas
                    + utilidades.RESET
                );
            }

            return premio;
        }
    public static int obtenerValor(
            String simbolo,
            int apuesta) {

        switch (simbolo) {

            case "7":
                return apuesta * 10;

            case "$":
                return apuesta * 5;

            case "@":
                return apuesta * 4;

            default:
                return apuesta * 2;
        }
    }
        public static void animarGiro(
            String[][] matriz) {

        try {

            for (int i = 0; i < 12; i++) {

                girar(matriz);

                utilidades.limpiarPantalla();

                System.out.println(
                utilidades.AMARILLO
                + "GIRANDO...\n"
                + utilidades.RESET
                );

                dibujar(matriz);

                Thread.sleep(120);
            }

        } catch (InterruptedException e) {

            System.out.println(
                "Error en animacion"
            );
        }
    }
}