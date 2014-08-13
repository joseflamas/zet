class Linea
{
  int x, y, largo, colorLinea, a;

  Linea(int X, int Y, color COLORLINEA, int A)
  {
    x=X; 
    y=Y; 
    colorLinea=COLORLINEA;
    a=A;
  }

  void conectarHorizontal(int I, int NORMI, int INTEN, int DISTI, int A1, int A2 , float GROSLIN, color COLORLINEA, int A)
  {
    stroke(COLORLINEA, A);
    strokeWeight(GROSLIN);
    line( x+I, y+in0.left.get(NORMI)*INTEN,       x+I+1, y+A1+in0.left.get(NORMI)*INTEN);
    //line( y+DISTI+in0.right.get(NORMI)*INTEN, x+I, y+A2+DISTI+in0.right.get(NORMI)*INTEN, x+I+1);
    
  }
  
  void conectarVertical(int I, int NORMI, int INTEN, int DISTI, int A1, int A2 , float GROSLIN, color COLORLINEA, int A)
  {
    stroke(COLORLINEA, A);
    strokeWeight(GROSLIN);
    line( x+I, y+in0.left.get(NORMI)*INTEN,       x+I+1, y+A1+in0.left.get(NORMI)*INTEN);
    //line( y+DISTI+in0.right.get(NORMI)*INTEN, x+I, y+A2+DISTI+in0.right.get(NORMI)*INTEN, x+I+1);
    }
  
  
  void moverVertical(boolean MOVER, int CUANTO, boolean WIDTH)
  {
    
    if(MOVER == true)
    {
     y+=CUANTO;
     int distancia = (WIDTH == true) ? width : height;
     if(y>distancia)
     {
       y=0;
     }
    }
    
  }
  

}

