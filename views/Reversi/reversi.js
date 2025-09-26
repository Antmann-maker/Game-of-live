class Reversi {
    constructor(canvas,endpoint) {
        this.canvas = canvas;
        this.ctx = canvas.getContext("2d");
        this.marginX = this.canvas.width / 20;
        this.marginY = this.canvas.height / 20;
        this.fieldWidth = this.canvas.width / 8;
        this.fieldHeight = this.canvas.height / 8;

        this.ctx.lineWidth = 4;
        this.drawBoard();

        this.board = Array(64).fill(null);

        this.drawInitialPawns();

        
        
    }

    drawToken(isWeiß, index) {
        const x = (index % 8) * this.fieldWidth;
        const y = Math.floor(index / 8) * this.fieldHeight;

        if (isWeiß) {
            this.drawWeiß(x, y);
        } else {
            this.drawSchwarz(x, y);
        }
    }
    
    

    drawWeiß(x, y) {
        this.ctx.fillStyle = "white";
        this.ctx.beginPath();

        this.ctx.arc(x + this.fieldWidth / 2, y+ this.fieldHeight / 2, Math.min(this.fieldHeight - this.marginY, this.fieldWidth - this.marginX) / 4, Math.PI * 2, 0, false);
        this.ctx.fill();
    }


    drawSchwarz(x, y) {
        this.ctx.fillStyle = "black";
        this.ctx.beginPath();

        this.ctx.arc(x + this.fieldWidth / 2, y+ this.fieldHeight / 2, Math.min(this.fieldHeight - this.marginY, this.fieldWidth - this.marginX) / 4, Math.PI * 2, 0, false);
        this.ctx.fill();
    }

    drawGreen(index) {
        const x = (index % 8) * this.fieldWidth;
        const y = Math.floor(index / 8) * this.fieldHeight;
        this.ctx.fillStyle = "green";
        this.ctx.beginPath();
        this.ctx.arc(x + this.fieldWidth / 2, y+ this.fieldHeight / 2, Math.min(this.fieldHeight - this.marginY, this.fieldWidth - this.marginX) / 4 + 2, Math.PI * 2, 0, false);
        this.ctx.fill();
    }

    drawXb(index) {
        this.ctx.strokeStyle = "black";
        const x = (index % 8) * this.fieldWidth;
        const y = Math.floor(index / 8) * this.fieldHeight;
        this.ctx.beginPath();

        this.ctx.moveTo(x + this.marginX, y + this.marginY);
        this.ctx.lineTo(x + this.fieldWidth - this.marginX, y + this.fieldHeight - this.marginY);

        this.ctx.moveTo(x + this.marginX, y + this.fieldHeight - this.marginY);
        this.ctx.lineTo(x + this.fieldWidth - this.marginX, y + this.marginY);
        this.ctx.stroke();
    }

    drawXw(index) {
        this.ctx.strokeStyle = "white";
        const x = (index % 8) * this.fieldWidth;
        const y = Math.floor(index / 8) * this.fieldHeight;
        this.ctx.beginPath();

        this.ctx.moveTo(x + this.marginX, y + this.marginY);
        this.ctx.lineTo(x + this.fieldWidth - this.marginX, y + this.fieldHeight - this.marginY);

        this.ctx.moveTo(x + this.marginX, y + this.fieldHeight - this.marginY);
        this.ctx.lineTo(x + this.fieldWidth - this.marginX, y + this.marginY);
        this.ctx.stroke();
    }

    drawBoard() {
        this.ctx.fillStyle = "green";
        this.ctx.fillRect(0, 0, this.canvas.width, this.canvas.height);

        this.ctx.beginPath();

        for (let i = 1; i < 8; i++) {
            // Horizontal lines
            this.ctx.moveTo(0, i * this.fieldHeight);
            this.ctx.lineTo(this.canvas.width, i * this.fieldHeight);

            // Vertical lines
            this.ctx.moveTo(i * this.fieldWidth, 0);
            this.ctx.lineTo(i * this.fieldWidth, this.canvas.height);
        }
        this.ctx.stroke();
        for(let i = 1; i <= 8;i++){
            this.ctx.fillStyle = "black";
            this.ctx.textAlign = 'center';
            this.ctx.fillText(i,this.fieldWidth / 5 + this.fieldWidth * (i - 1),this.fieldWidth / 5);
            this.ctx.fillText(i,(this.fieldHeight / 5),(this.fieldHeight / 5) + this.fieldHeight * (i - 1))
        }
        
    }
    showWinner(value) {
        // zeichne ein Rechteck über das gesamte Canvas
        const rectWidth = 4 * this.fieldWidth;  // 
        const rectHeight = 2 * this.fieldHeight; // größe des Rechtecks
    
        // berechne die Position des Rechtecks
        const rectX = (this.canvas.width - rectWidth) / 2;
        const rectY = (this.canvas.height - rectHeight) / 2;
    
        // zeichne das Rechteck
        this.ctx.fillStyle = "white";
        this.ctx.beginPath();
        this.ctx.moveTo(rectX, rectY);
        this.ctx.lineTo(rectX + rectWidth, rectY);
        this.ctx.lineTo(rectX + rectWidth, rectY + rectHeight);
        this.ctx.lineTo(rectX, rectY + rectHeight);
        this.ctx.closePath();
        this.ctx.fill();
    
        // konfiguriere den Text
        this.ctx.font = "12px Verdana"; // Schriftgröße und -art
        this.ctx.fillStyle = "black"; // Schriftfarbe
        this.ctx.textAlign = "center"; // Zentrieren horizontal
        this.ctx.textBaseline = "middle"; // Zentrieren vertikal
    
        // Text abhängig vom Wert anzeigen
        let text;
        if (value == 1) {
            text = "Weiß hat gewonnen 🎊🎊🎊🎊 "; 
        } else if (value == -1) {
            text = "Schwarz hat gewonnen🎊🎊🎊🎊"; 
        } else {
            text = "Das Spiel ist unentschieden 😞😞"; 
        }
    
        // Position des Textes
        const textX = this.canvas.width / 2; // horizontal zentriert
        const textY = this.canvas.height / 2; // vertikal zentriert
        this.ctx.fillText(text, textX, textY); // Text zeichnen
    }
    
    
    

    
    drawInitialPawns() {
        this.drawToken(true, 27); // weiß  (3, 3)
        this.drawToken(true, 36); // weiß  (4, 4)
        this.drawToken(false, 28);  // schwarz  (3, 4)
        this.drawToken(false, 35);  // schwarz  (4, 3)
    }
    
    

}