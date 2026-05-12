package ProyectoFinalArcade;

import java.io.*;
import java.util.*;

public class archivocsv {

    public static jugador cargarJugador(String nombre) {

        try (
            BufferedReader br =
            new BufferedReader(
            new FileReader("jugadores.csv"))
        ) {

            String linea;

            while ((linea = br.readLine()) != null) {

                String[] datos = linea.split(",");

                if (datos[0].equalsIgnoreCase(nombre)) {

                    return new jugador(
                        datos[0],
                        Integer.parseInt(datos[1])
                    );
                }
            }

        } catch (IOException e) {

            System.out.println(
                "Error leyendo archivo"
            );
        }

        return null;
    }

    public static void guardarJugador(
            jugador jugador1) {

        try (
            BufferedWriter bw =
            new BufferedWriter(
            new FileWriter(
            "jugadores.csv",
            true))
        ) {

            bw.write(
                jugador1.getNombre()
                + ","
                + jugador1.getSaldo()
            );

            bw.newLine();

        } catch (IOException e) {

            System.out.println(
                "Error escribiendo archivo"
            );
        }
    }

    public static void actualizarJugador(
            jugador jugador1) {

        ArrayList<String> lineas =
            new ArrayList<>();

        try (
            BufferedReader br =
            new BufferedReader(
            new FileReader("jugadores.csv"))
        ) {

            String linea;

            while ((linea = br.readLine()) != null) {

                String[] datos =
                    linea.split(",");

                if (datos[0].equalsIgnoreCase(
                    jugador1.getNombre())) {

                    lineas.add(
                        jugador1.getNombre()
                        + ","
                        + jugador1.getSaldo()
                    );

                } else {

                    lineas.add(linea);
                }
            }

        } catch (IOException e) {

            System.out.println(
                "Error leyendo archivo"
            );
        }

        try (
            BufferedWriter bw =
            new BufferedWriter(
            new FileWriter("jugadores.csv"))
        ) {

            for (String linea : lineas) {

                bw.write(linea);

                bw.newLine();
            }

        } catch (IOException e) {

            System.out.println(
                "Error escribiendo archivo"
            );
        }
    }
            public static void mostrarRanking() {

            jugador[] jugadores =
                new jugador[100];

            int contador = 0;

            try (

                BufferedReader br =
                new BufferedReader(
                new FileReader("jugadores.csv"))

            ) {

                String linea;

                while ((linea = br.readLine()) != null) {

                    String[] datos =
                        linea.split(",");

                    jugadores[contador] =
                        new jugador(

                        datos[0],

                        Integer.parseInt(
                        datos[1])
                    );

                    contador++;
                }

            } catch (IOException e) {

                System.out.println(
                    "Error leyendo ranking"
                );
            }

            // ordenar mayor a menor
            for (int i = 0;
                i < contador - 1;
                i++) {

                for (int j = 0;
                    j < contador - 1 - i;
                    j++) {

                    if (

                        jugadores[j].getSaldo()
                        <
                        jugadores[j + 1]
                        .getSaldo()

                    ) {

                        jugador temp =
                            jugadores[j];

                        jugadores[j] =
                            jugadores[j + 1];

                        jugadores[j + 1] =
                            temp;
                    }
                }
            }

            System.out.println(

                utilidades.AMARILLO
                + "\n=== RANKING ===\n"
                + utilidades.RESET
            );

            for (int i = 0;
                i < contador;
                i++) {

                System.out.println(

                    (i + 1)
                    + ". "

                    + jugadores[i]
                    .getNombre()

                    + " - $"

                    + jugadores[i]
                    .getSaldo()
                );
            }
        }
}