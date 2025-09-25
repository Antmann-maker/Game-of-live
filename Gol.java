import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

Clerk.markdown(
        Text.fillOut(
                """
# **Game Of Life**
"""
)
);


public class Gol {
    int []world ;
    int rows;
    int cols;


    Gol(int rows,int cols){
        this.rows = rows;
        this.cols = cols;
        this.world = new int [rows*cols];
    }



    public List<Integer> getNeighbors(int index) {
        List<Integer> neighbors = new ArrayList<>();

        // rechnet die Position in  2D durch das index in 1D
        int row = index / cols;
        int col = index % cols;

        // die bewegung in der  8 Richtungen
        int[] dRow = {-1, -1, -1, 0, 0, 1, 1, 1};
        int[] dCol = {-1, 0, 1, -1, 1, -1, 0, 1};
        for (int i = 0; i < 8; i++) {
            int newRow = row + dRow[i];
            int newCol = col + dCol[i];

            // überVérifier si le voisin est dans les limites
            if (newRow >= 0 && newRow < rows && newCol >= 0 && newCol < cols) {
                int neighborIndex = newRow * cols + newCol; // Umrechnung von 2D-Position in 1D-Index des Nachbarns
                if(world[neighborIndex] == 1){
                    neighbors.add(world[neighborIndex]); // fügt das des Nachbarns hinzu
                }
            }
        }

        return neighbors;
    }




    int rule(int center) {
        List<Integer> neighbors = this.getNeighbors(center);

        // Zustand der aktuellen Zelle
        int currentState = world[center];

        // Neue Regelberechnung
        if (currentState == 1) { // Wenn die Zelle belebt ist
            if (neighbors.size() == 2 || neighbors.size() == 3) {
                return 1; // Zelle bleibt am Leben
            } else {
                return 0; // Zelle stirbt
            }
        } else { // Wenn die Zelle unbelebt ist
            if (neighbors.size() == 3) {
                return 1; // Zelle wird belebt
            } else {
                return 0; // Zelle bleibt unbelebt
            }
        }
    }


    Gol set(int row, int col) {
        // Grenzprüfung: Ist die angegebene Zelle innerhalb der Welt?
        if (row < 0 || row >= rows || col < 0 || col >= cols) {
            System.out.println("Ungültige Position: (" + row + ", " + col + ")");
        }

        // Umrechnung von 2D-Position in 1D-Index
        int index = row * cols + col;

        // Setze die Zelle auf belebt (1)
        this.world[index] = 1;

        return this;
    }


    Gol timeStep(){
        int []step = new int[this.world.length];
        for(int i =0; i<this.world.length ;i++){
            step[i] = this.rule(i);
        }
        world = step;
        return this;
    }


    Gol rotate(){
        int []r = new int[this.world.length];
        for(int i = 0; i<this.world.length; i++){
            int a = i / cols;
            int b = i % cols;
            int t =a;
            a = b;
            b = rows-1-t;
            int index = a * cols + b;
            r[index] = this.world[i];

        }
        for(int i = 0; i<world.length; i++){
            this.world[i] = r[i];
        }
        return this;
    }



    Gol insert(int row, int col, Gol source){
        int index = row * cols + col;
        for(int i=0;i <source.world.length;i++){
            int n = i+index;
            if(n>this.world.length) break;
            this.world[n] = source.world[i];
        }
        return this;
    }



    void run(int step) {
        assert step >=1;
        for (int i = 0; i < step; i++) {
            System.out.println(timeStep());// Berechnet den nächsten Zeitschritt

            //  Pause, um den Übergang zwischen den Zeitschritten zu verlangsamen
            try {
                Thread.sleep(500);  // 500 Millisekunden Pause zwischen den Zeitschritten
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
    Turtle turtle = new Turtle(2500,2500);
    @Override


    public String toString(){
        turtle.reset();
        int x = 100;
        int y = 100;
        String res = "";
        for(int i = 0; i < this.world.length; i++) {
            if(i % cols == 0 && i != 0) {
                x = 100;
                y = y + 50;
            }
            if(world[i] == 1) {
                turtle.moveTo(x, y).left(90).penUp().text("🏀").forward(20).penDown().left(90).forward(15).left(90).forward(40).left(90).forward(30).left(90).forward(40).left(90).forward(15).left(180);                    x = x + 40;
            } else {
                turtle.moveTo(x, y).left(90).penUp().forward(20).penDown().left(90).forward(15).left(90).forward(40).left(90).forward(30).left(90).forward(40).left(90).forward(15).left(180);
                x = x + 40;
            }
        }
        return res;
    }
}












