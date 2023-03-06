import java.util.Scanner;
public class LaplaceDeterminant {
    static double det (int n, int w, int[] WK, double[][] A) {
        int i, j, k, m;
        int[] KK;
        double s;

        if(n == 1) { // sprawdzamy warunek zakończenia rekurencji
            return A[w][WK[0]]; // macierz 1 x 1, wyznacznik równy elementowi
        } else {
            KK = new int[n - 1]; // tworzymy dynamiczny wektor kolumn
            s = 0; // zerujemy wartość rozwinięcia
            m = 1; // początkowy mnożnik

            for(i = 0; i < n; i++) { // pętla obliczająca rozwinięcie
                k = 0; // tworzymy wektor kolumn dla rekurencji
                for(j = 0; j < n - 1; j++) { // ma on o 1 kolumnę mniej niż WK
                    if(k == i) {
                        k++; // pomijamy bieżącą kolumnę
                    }
                    KK[j] = WK[k++]; // pozostałe kolumny przenosimy do KK
                }
                s += m * A[w][WK[i]] * det(n - 1, w + 1, KK, A);
                m = -m; // kolejny mnożnik
            }
            // usuwamy zbędną już tablicę dynamiczną
            KK = null;
            return s; // ustalamy wartość funkcji
        }
    }

    public static void main(String[] args) {
        int n, i, j; // stopień macierzy
        int[] WK; // wektor kolumn
        double[][] A; // macierz
        Scanner scanner = new Scanner(System.in);
        System.out.print("Podaj stopień macierzy: ");
        n = scanner.nextInt(); // odczytujemy stopień macierzy
        A = new double[n][]; // tworzymy macierz wskaźników

        for(i = 0; i < n; i++) {
            A[i] = new double[n]; // tworzymy wiersz
            for(j = 0; j < n; j++) {
                System.out.print("Podaj element [" + i + "][" + j + "]: ");
                A[i][j] = scanner.nextDouble(); // czytamy wiersz macierzy
            }
        }

        WK = new int[n]; // tworzymy wiersz kolumn
        for(i = 0; i < n; i++) { // wypełniamy go numerami kolumn
            WK[i] = i;
        }
        System.out.println();
        System.out.println(det(n, 0, WK, A)); // obliczamy i wyświetlamy wyznac
    }
}