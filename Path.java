package Part2;

public class Path {
    private final int MAX_VERTS = 20;
    private City[] cityList;
    private int[][] matrix;
    private int city;

    public int getnCity() {
        return city;
    }

    public Path(int vertex) {
        city = 0;
        matrix = new int[vertex][vertex];
        cityList = new City[vertex];
    }

    public void addCity(String lab) {
        cityList[city++] = new City(lab);
    }

    public int getCity(String lbl) {
        for (int i = 0; i < city; i++) {
            if (lbl.equals(cityList[i].label)) {
                return i;
            }
        }
        return -1;
    }

    public void displayVertex(int v) {
        System.out.print(cityList[v].label + " ");
    }

    public void showMatrix() {
        for (int i = 0; i < city; i++) {
            for (int j = 0; j < city; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    public int getAdjUnvisitedVertex(int v) {
        for (int j = 0; j < city; j++) {
            if (matrix[v][j] > 0 && !cityList[j].visited) {
                return j;
            }
        }
        return -1;
    }

    public void addPath(String source, String destination, int weight) {
        int sourceVertex = getCity(source);
        int destinationVertex = getCity(destination);
        matrix[sourceVertex][destinationVertex] = weight;
    }

    int getMinimumVertex(int[] key) {
        int minKey = Integer.MAX_VALUE;
        int vertex = -1;
        for (int i = 0; i < city; i++) {
            if (!cityList[i].isVisited() && minKey > key[i]) {
                minKey = key[i];
                vertex = i;
            }
        }
        return vertex;
    }

    public void dijkstra(String sourceCity) {
        int[] distance = new int[city];
        int max = Integer.MAX_VALUE;

        for (int i = 0; i < city; i++) {
            distance[i] = max;
        }

        int source = getCity(sourceCity);
        if (source == -1) {
            System.out.println("City not found: " + sourceCity);
            return;
        }

        distance[source] = 0;
        for (int i = 0; i < city - 1; i++) {
            int cityNow = getMinimumVertex(distance);
            cityList[cityNow].setVisited(true);
            for (int cityExplore = 0; cityExplore < city; cityExplore++) {
                if (matrix[cityNow][cityExplore] > 0) {
                    if (!cityList[cityExplore].isVisited() && matrix[cityNow][cityExplore] != max) {
                        int newKey = matrix[cityNow][cityExplore] + distance[cityNow];
                        if (newKey < distance[cityExplore])
                            distance[cityExplore] = newKey;
                    }
                }
            }
        }

        System.out.println("Shortest Path");
        for (int i = 0; i < city; i++) {
            System.out.println("From City " + cityList[source].label
                    + " to City " + cityList[i].label
                    + " : Shortest Path = " + distance[i]);
        }
    }

    public void printPath(String source, String destination) {
        int sourceIndex = getCity(source);
        int destinationIndex = getCity(destination);

        if (sourceIndex == -1 || destinationIndex == -1) {
            System.out.println("City not found: " + (sourceIndex == -1 ? source : destination));
            return;
        }

        // Menjalankan algoritma Dijkstra dari kota asal
        dijkstra(source);

        // Memeriksa apakah kota tujuan terjangkau dari kota asal
        if (cityList[destinationIndex].isVisited()) {
            resetVisited(); // Reset status visited setiap kota
            System.out.println("Shortest Path from " + source + " to " + destination + ":");
            printPathRecursive(sourceIndex, destinationIndex);
            System.out.println();
        } else {
            System.out.println("No path found from " + source + " to " + destination);
        }
    }

    private void resetVisited() {
        for (int i = 0; i < city; i++) {
            cityList[i].setVisited(false);
        }
    }

    private void printPathRecursive(int sourceIndex, int destinationIndex) {
        // Rekursi: Cari kota yang belum dikunjungi dan cetak jalur ke kota selanjutnya
        int nextCityIndex = getAdjUnvisitedVertex(sourceIndex);
        if (nextCityIndex != -1) {
            cityList[sourceIndex].setVisited(true);
            printPathRecursive(nextCityIndex, destinationIndex);
            System.out.print(" " + cityList[nextCityIndex].displayCity()); // Menambahkan kota ke output
        }

        // Basis rekursi: Jika mencapai tujuan, hentikan rekursi
        if (nextCityIndex == destinationIndex) {
            return;
        }
    }
}
