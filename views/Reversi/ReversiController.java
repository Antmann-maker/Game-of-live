public class ReversiController implements Clerk {
    final String ID;
    final int width , height ;
    final String libPath = "views/Reversi/reversi.js";
    LiveView view;

    
    

    ReversiController(LiveView view) {
        this.view = view;
        Clerk.load(view, libPath);
        ID = Clerk.getHashID(this);
        this.width = 500;
        this.height = 400;
        Clerk.write(view, "<canvas id='reversiCanvas" + ID + "' width='" + this.width + "' height='" + this.height + "' style='border:3px  solid #000; box-shadow: 3px 3px 10px rgba(0,0,0,0.3); border-radius: 15px;'></canvas>");
        Clerk.script(view, "const reversi" + ID + " = new Reversi(document.getElementById('reversiCanvas" + ID + "'), 'reversi" + ID + "');");
    }
    
     
    ReversiController() { this(Clerk.view());}
    
    
        // Methode, um einen Spielstein zu zeichnen
        ReversiController drawToken(boolean isWhite, int position) {
            Clerk.call(view, "reversi" + ID + ".drawToken(" + isWhite + ", " + position + ")");
            return this;
        }
    
        // Methode, um ein weißes X anzuzeigen (gültiger Zug)
        ReversiController drawXw(int position) {
            Clerk.call(view, "reversi" + ID + ".drawXw(" + position + ")");
            return this;
        }
    
        // Methode, um ein schwarzes X anzuzeigen (gültiger Zug)
        ReversiController drawXb(int position) {
            Clerk.call(view, "reversi" + ID + ".drawXb(" + position + ")");
            return this;
        }
    
        // Methode, um die Anzeige der gültigen Züge zu löschen
        ReversiController drawGreen(int position) {
            Clerk.call(view, "reversi" + ID + ".drawGreen(" + position + ")");
            return this;
        }
    
        // Methode, um den Gewinner anzuzeigen
        ReversiController showWinner(int winner) {
            Clerk.call(view, "reversi" + ID + ".showWinner(" + winner + ")");
            return this;
        }
    
        
}
