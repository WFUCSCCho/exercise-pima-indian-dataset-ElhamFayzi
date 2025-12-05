import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.io.FileInputStream;
import java.util.Scanner;

public class PimaCorrelation {

    // Method to calculate correlation coefficient
    public static double Correlation(ArrayList<Double> xs, ArrayList<Double> ys) {
        // Check if both ArrayLists are non-null and of the same length
        if (xs == null || ys == null || xs.size() != ys.size()) {
            throw new IllegalArgumentException("Input arrays must not be null and must have the same length.");
        }

        double sx = 0.0;
        double sy = 0.0;
        double sxx = 0.0;
        double syy = 0.0;
        double sxy = 0.0;

        int n = xs.size();  // Get the size of the ArrayLists

        // Loop through the elements in the ArrayLists
        for (int i = 0; i < n; ++i) {
            double x = xs.get(i);
            double y = ys.get(i);

            sx += x;
            sy += y;
            sxx += x * x;
            syy += y * y;
            sxy += x * y;
        }

        // Covariance
        double cov = sxy / n - sx * sy / n / n;
        // Standard deviation of x
        double sigmax = Math.sqrt(sxx / n - sx * sx / n / n);
        // Standard deviation of y
        double sigmay = Math.sqrt(syy / n - sy * sy / n / n);

        // Correlation is the normalized covariance
        return cov / sigmax / sigmay;
    }

    public static void main(String[] args) {
        // TODO: Replace sample data with Pima Indians Diabetes Dataset https://www.kaggle.com/datasets/uciml/pima-indians-diabetes-database

        FileInputStream fis = null;
        try {
            fis = new FileInputStream("/Users/elhamahmadfayzi/IdeaProjects/exercise-pima-indian-dataset-ElhamFayzi/src/diabetes.csv");
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            System.exit(1);
        }
        Scanner reader = new Scanner(fis);

        reader.nextLine();

        ArrayList<Double> pregnancies = new ArrayList<>();
        ArrayList<Double> diabetes = new ArrayList<>();

        while (reader.hasNextLine()) {
            String[] line = reader.nextLine().split(",");

            pregnancies.add(Double.parseDouble(line[0]));
            diabetes.add(Double.parseDouble(line[8]));
        }


        // Function call to correlationCoefficient
        System.out.printf("%6f\n", Correlation(pregnancies, diabetes));
    }
}