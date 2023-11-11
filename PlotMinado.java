import org.knowm.xchart.*;
import org.knowm.xchart.BitmapEncoder.BitmapFormat;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class PlotMinado {

    public static void main(String[] args) {
        List<Integer> dificultades = new ArrayList<>();
        List<Long> tiempos = new ArrayList<>();

        List<Integer> dificultades512 = new ArrayList<>();
        List<Long> tiempos512 = new ArrayList<>();

        try {
            BufferedReader fileReader = new BufferedReader(new FileReader("./docs/tiempoMinado256.txt"));
            fileReader.readLine(); // Ignorar la primera línea (encabezado)

            String line;
            while ((line = fileReader.readLine()) != null) {
                String[] parts = line.split("\t\t");
                dificultades.add(Integer.parseInt(parts[0]));
                tiempos.add(Long.parseLong(parts[1]));
            }

            fileReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            BufferedReader fileReader512 = new BufferedReader(new FileReader("./docs/tiempoMinadoSHA512.txt"));
            fileReader512.readLine();

            String line;
            while ((line = fileReader512.readLine()) != null) {
                String[] parts = line.split("\t\t");
                dificultades512.add(Integer.parseInt(parts[0]));
                tiempos512.add(Long.parseLong(parts[1]));
            }

            fileReader512.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Crear la gráfica
        XYChart chart = new XYChartBuilder().width(800).height(600).title("Tiempo de minado por dificultad 256")
                .xAxisTitle("Dificultad").yAxisTitle("Tiempo (ms)").build();

        XYChart chart512 = new XYChartBuilder().width(800).height(600).title("Tiempo de minado por dificultad 256")
                .xAxisTitle("Dificultad").yAxisTitle("Tiempo (ms)").build();

        // Agregar series al gráfico
        chart.addSeries("Tiempo de minado", dificultades, tiempos);

        chart512.addSeries("Tiempo de minado", dificultades512, tiempos512);

        // Mostrar la gráfica
        // new SwingWrapper<>(chart).displayChart();

        try {
            BitmapEncoder.saveBitmap(chart, "./docs/char256", BitmapFormat.PNG);
            BitmapEncoder.saveBitmap(chart512, "./docs/char512", BitmapFormat.PNG);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
