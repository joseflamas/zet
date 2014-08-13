import ddf.minim.spi.*;
import ddf.minim.signals.*;
import ddf.minim.*;
import ddf.minim.analysis.*;
import ddf.minim.ugens.*;
import ddf.minim.effects.*;

import codeanticode.syphon.*;

//
//////////
// 
// WRAP 
// Syphon/minim/fun000
//
// V.00 paraelfestival"ANTES"
// feelGoodSoftwareporGuillermoGonzálezIrigoyen
//
//////////

//
//VARIABLES DE CONFIGURACIÓN
//Tamaño de la pantalla , proporciones y tweak
boolean pCompleta = false; //salidas a proyector 1024X768
boolean pCambiable= true;
int pPantalla     = 4;
int pP,pW,pH;
//Syphon
SyphonServer server;
String nombreServer = "WRAP";
//Audio
Minim minim;
AudioInput in0;
//
// ...


//tests
int distCanal, distIn;



//##SETUP##
//
void setup()
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
  
  

  
}



//##DRAW##
//
void draw()
{
  
  pW=width;
  pH=height;
  distCanal=5;
  distIn=25;
  

  
 
  background(#FFFFFB);
  for(int i = 0; i < pW; i++)
  {
    stroke(#ED3C65);
    line( i, 10 + in0.left.get(i)*50, i+1, 10 + in0.left.get(i+1)*50 );
    line( i, distCanal + in0.right.get(i)*50, i+1, distCanal + in0.right.get(i+1)*50 );
  }
  
 

  
  
  //fondo
  fondodePapel(85,85,85,11,true);

  
  //SYPHON SERVER
  server.sendScreen();
}






//
//##OVER##
//
boolean sketchFullScreen() { return pCompleta; }


//
//##CONTROLES##
//
void keyPressed() 
{
  if(key=='q')
  {
    
  }
}

//
//##FONDO
//
void fondodePapel(int R, int G, int B, int A, boolean sinCuadricula) 
{
  if(sinCuadricula){ noStroke(); }
  for (int i = 0; i<width; i+=2) 
  {
    for (int j = 0; j<height; j+=2) 
    {
      fill(random(R-10, R+10),random(G-10, G+10),random(B-10, B+10), random(A*2.5,A*3));
      rect(i, j, 2, 2);
    }
  }  
   
  for (int i = 0; i<30; i++) 
  {
    fill(random(R/2, R+R/2), random(A*2.5, A*3));
    rect(random(0, width-2), random(0, height-2), random(1, 3), random(1, 3));
  }
}
