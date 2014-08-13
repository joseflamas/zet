import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import ddf.minim.spi.*; 
import ddf.minim.signals.*; 
import ddf.minim.*; 
import ddf.minim.analysis.*; 
import ddf.minim.ugens.*; 
import ddf.minim.effects.*; 
import codeanticode.syphon.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class ANTES01 extends PApplet {










//
//////////
// 
// WRAP 
// Syphon/minim/fun000
//
// V.00 paraelfestival"ANTES"
// feelGoodSoftwareporGuillermoGonz\u00e1lezIrigoyen
//
//////////

//
//VARIABLES DE CONFIGURACI\u00d3N
//Tama\u00f1o de la pantalla , proporciones y tweak
boolean pCompleta = false; //salidas a proyector 1024X768
boolean pCambiable= true;
int pPantalla     = 4;
int pP,pW,pH;
//Syphon
SyphonServer server;
String nombreServer = "ANTES01";
//Audio
Minim minim;
AudioInput in0;
//
// ...


//LINEA
Linea lin,lin1;
int[] coloresAntes = { 0xff2693FF, 0xffEF3B63 , 0xffFFFFFB, 0xff000000};
int numColor      = 1;
boolean hay;
int alfa          = 255;
boolean moverL    = false;
boolean moverH    = false;
int     xIL       = 0;
int     yIL       = 10;
int     intA      = 50;
int     sepLR     = 5;
int     anchoL    = 0;
int     anchoR    = 0;
int     anchoS    = 1;
int     distMov   = 1;


//FONDO
boolean fondo      = true;
boolean textura    = true;
boolean sinCuadricula = true;
int R  = 85;
int G  = 85;
int B  = 85;
int A  = 10;


//CONTROLES
boolean sumar   = true;
int paso   = 2;



//##SETUP##
//
public void setup()
{
  //CANVAS
  pP = (pCompleta == true)? 1 : pPantalla;
  size(displayWidth/pP, displayHeight/pP, P3D);
  pW=width;pH=height;
  frame.setResizable(pCambiable);
  //SYPHON SERVER
  server = new SyphonServer(this, nombreServer);
  //AUDIO
  minim = new Minim(this);
  in0 = minim.getLineIn();
  //
  // ...
  
  yIL = height/2;
  lin = new Linea(xIL, yIL, coloresAntes[numColor], alfa);
  lin1 = new Linea(xIL, yIL-50, coloresAntes[numColor+1], alfa);
  


  
}



//##DRAW##
//
public void draw()
{
  pW=width;
  pH=height;

 
  if(fondo){ background(coloresAntes[2]); }
  
  int normi;
  pushMatrix();
  for(int i = 0; i < pW; i++)
  {
    normi=i;
    if(i>1024||i==1024){ normi = PApplet.parseInt(norm(i,0,1024)); }
    lin.conectarHorizontal(i, normi, intA, sepLR, anchoL, anchoR, anchoS, coloresAntes[numColor], alfa);
     
    
  }
  if(moverL==true){ lin.moverVertical(moverL, distMov, moverH);
                    lin1.moverVertical(moverL, distMov, moverH); }
  popMatrix();
  

  //fondo
  if(textura){ pushMatrix(); fondodePapel(R,G,B,A,sinCuadricula); popMatrix(); }

  
  //SYPHON SERVER
  server.sendScreen();
}






//
//##CONTROLES##
//
public void keyPressed() 
{
  char k = key;
  switch(k)
  {
    //////////////////////////////////////////// 
    ////////////////////////////////////////////  
    //controles//
    case '<':
       sumar = !sumar;
       print("sumar :",sumar,"\n");
       print("paso :",paso,"\n");
       print("___________________\n");
       break;
    case '0':
       break;
    case '1':
       fondo=!fondo;
       print("fondo :",fondo,"\n");
       print("___________________\n");
       break;
    case '2':
       sinCuadricula=!sinCuadricula;
       print("sinCuadricula :",sinCuadricula,"\n");
       print("___________________\n");
       break;
    case '3':
       textura=!textura;
       print("textura :",textura,"\n");
       print("___________________\n");
       break;
    case 'z':
       paso--;
       paso = (paso>255)?255:paso;
       paso = (paso<0)?0:paso;
       print("paso :",paso,"\n");
       print("___________________\n");
       break;
    case 'x':
       paso++;
       paso = (paso>255)?255:paso;
       paso = (paso<0)?0:paso;
       print("paso :",paso,"\n");
       print("___________________\n");
       break;
 
 
    //////////////////////////////////////////// 
    ////////////////////////////////////////////  
    //fondo//
    case 'q':
       if(sumar==true)
       { 
         R+=paso;
         R = (R>255)?255:R;
         R = (R<0)?0:R;
       } else {
         R-=paso;
         R = (R>255)?255:R;
         R = (R<0)?0:R;
       }
       print("Rojo :",R,"\n");
       print("___________________\n");
       break;
    case 'w':
       if(sumar==true)
       { 
         G+=paso;
         G = (G>255)?255:G;
         G = (G<0)?0:G;
       } else {
         G-=paso;
         G = (G>255)?255:G;
         G = (G<0)?0:G;
       }
       print("Verde :",G,"\n");
       print("___________________\n");
       break;
    case 'e':
       if(sumar==true)
       { 
         B+=paso;
         B = (B>255)?255:B;
         B = (B<0)?0:B;
       } else {
         B-=paso;
         B = (B>255)?255:B;
         B = (B<0)?0:B;
       }
       print("Azul :",B,"\n");
       print("___________________\n");
       break;
    case 'a':
       if(sumar==true)
       { 
         A+=paso;
         A = (A>255)?255:A;
         A = (A<0)?0:A;
       } else {
         A-=paso;
         A = (A>255)?255:A;
         A = (A<0)?0:A;
       }
       print("Grano Papel :",A,"\n");
       print("___________________\n");
       break;
    case 's':
       break;
       
       
    //////////////////////////////////////////// 
    ////////////////////////////////////////////  
    //Linea//
    case 'r':
      numColor+=1;
      hay = (numColor > coloresAntes.length || numColor == coloresAntes.length)? false : true;
      numColor = (hay)? numColor : 0;
      print("numColor :",numColor,"\n");
      print("___________________\n");
      break;
    case 't':
      if(sumar==true)
       { 
         anchoL+=paso;
         anchoL = (anchoL>1000)?1000:anchoL;
         anchoL = (anchoL<0)?0:anchoL;
       } else {
         anchoL-=paso;
         anchoL = (anchoL>1000)?1000:anchoL;
         anchoL = (anchoL<0)?0:anchoL;
       }
      print("Ancho canal izquierdo :",anchoL,"\n");
      print("___________________\n");
      break;
    case 'y':
      if(sumar==true)
       { 
         anchoR+=paso;
         anchoR = (anchoR>1000)?1000:anchoR;
         anchoR = (anchoR<0)?0:anchoR;
       } else {
         anchoR-=paso;
         anchoR = (anchoR>1000)?1000:anchoR;
         anchoR = (anchoR<0)?0:anchoR;
       }
      print("Ancho canal derecho :",anchoR,"\n");
      print("___________________\n");
      break;
    case 'f':
       moverL = !moverL;
       print("Mover Linea :",moverL,"\n");
       print("___________________\n");
       break;
    case 'g':
       moverH = !moverH;
       print("Mover distancia horizontal :",moverH,"\n");
       print("___________________\n");
       break;
    case 'h':
      if(sumar==true)
         { 
           sepLR+=paso;
           sepLR = (anchoR>1000)?1000:sepLR;
           sepLR = (anchoR<0)?0:sepLR;
         } else {
           sepLR-=paso;
           sepLR = (anchoR>1000)?1000:sepLR;
           sepLR = (anchoR<0)?10:anchoR;
         }
        print("Separaci\u00f3n canales :",sepLR,"\n");
        print("___________________\n");
       break;
    case 'v':
       if(sumar==true)
         { 
           intA+=paso;
           intA = (intA>1000)?1000:intA;
           intA = (intA<0)?0:intA;
         } else {
           intA-=paso;
           intA = (intA>1000)?1000:intA;
           intA = (intA<0)?0:intA;
         }
        print("intencidad respuesta input :",intA,"\n");
        print("___________________\n");
       break;
    case 'b':
       if(sumar==true)
         { 
           distMov+=paso;
           distMov = (distMov>1000)?1000:distMov;
           distMov = (distMov<0)?0:distMov;
         } else {
           distMov-=paso;
           distMov = (distMov>1000)?1000:distMov;
           distMov = (distMov<0)?0:distMov;
         }
        print("distMov :",distMov,"\n");
        print("___________________\n");
        break;
     case 'n':
       if(sumar==true)
         { 
           anchoS+=paso;
           anchoS = (anchoS>1000)?1000:anchoS;
           anchoS = (anchoS<0)?0:anchoS;
         } else {
           anchoS-=paso;
           anchoS = (anchoS>1000)?1000:anchoS;
           anchoS = (anchoS<0)?0:anchoS;
         }
        print("distMov :",anchoS,"\n");
        print("___________________\n");
        break;
       
       
       
       
    //////////////////////////////////////////// 
    ////////////////////////////////////////////   
    //RESETS 7 8 9//
    case '9':
        numColor      = 1;
        alfa          = 255;
        moverL    = false;
        moverH    = false;
        xIL       = 0;
        yIL       = 10;
        intA      = 50;
        sepLR     = 5;
        anchoL    = 0;
        anchoR    = 0;
        anchoS    = 1;
        distMov   = 1;
       fondo      = true;
       textura    = true;
       sinCuadricula = true;
       R  = 85;
       G  = 85;
       B  = 85;
       A  = 10;
       break;
    case '8':
      numColor      = 1;
      alfa          = 255;
      moverL    = false;
      moverH    = false;
      xIL       = 0;
      yIL       = 10;
      intA      = 50;
      sepLR     = 5;
      anchoL    = 0;
      anchoR    = 0;
      anchoS    = 1;
      distMov   = 1;
       break;
    case '7':
       fondo      = false;
       textura    = true;
       sinCuadricula = true;
       R  = 85;
       G  = 85;
       B  = 85;
       A  = 10;
       break;
    //
    //DEFAULT
    default:
       break;
  }
  
}








//
//##FONDO
//
public void fondodePapel(int R, int G, int B, int A, boolean sinCuadricula) 
{
  if(sinCuadricula){ noStroke(); }
  for (int i = 0; i<width; i+=2) 
  {
    for (int j = 0; j<height; j+=2) 
    {
      fill(random(R-10, R+10),random(G-10, G+10),random(B-10, B+10), random(A*2.5f,A*3));
      rect(i, j, 2, 2);
    }
  }  
   
  for (int i = 0; i<30; i++) 
  {
    fill(random(R/2, R+R/2), random(A*2.5f, A*3));
    rect(random(0, width-2), random(0, height-2), random(1, 3), random(1, 3));
  }
}






//
//##OVER##
//
public boolean sketchFullScreen() { return pCompleta; }
class Linea
{
  int x, y, largo, colorLinea, a;

  Linea(int X, int Y, int COLORLINEA, int A)
  {
    x=X; 
    y=Y; 
    colorLinea=COLORLINEA;
    a=A;
  }

  public void conectarHorizontal(int I, int NORMI, int INTEN, int DISTI, int A1, int A2 , float GROSLIN, int COLORLINEA, int A)
  {
    stroke(COLORLINEA, A);
    strokeWeight(GROSLIN);
    line(  y+in0.left.get(NORMI)*INTEN,      x+I,  y+A1+in0.left.get(NORMI)*INTEN, x+I+1);
    line(  y+DISTI+in0.right.get(NORMI)*INTEN, x+I, y+A2+DISTI+in0.right.get(NORMI)*INTEN, x+I+1);
    
  }
  
  public void conectarVertical(int I, int NORMI, int INTEN, int DISTI, int A1, int A2 , float GROSLIN, int COLORLINEA, int A)
  {
    stroke(COLORLINEA, A);
    strokeWeight(GROSLIN);
    line( y+in0.left.get(NORMI)*INTEN,        x+I,   y+A1+in0.left.get(NORMI)*INTEN,        x+I+1);
    line( y+DISTI+in0.right.get(NORMI)*INTEN, x+I+1, y+A2+DISTI+in0.right.get(NORMI)*INTEN, x+I);
  }
  
  
  public void moverVertical(boolean MOVER, int CUANTO, boolean WIDTH)
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

  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "ANTES01" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
