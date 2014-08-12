import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import ddf.minim.*; 
import ddf.minim.analysis.*; 
import codeanticode.syphon.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class WRAP extends PApplet {

//////////////////////////////////////////////////////////////////////
// 
// WRAP 
// Syphon/minim/fun000
// 
// SIMPLEWRAP
// Esta es la versi\u00f3n m\u00e1s simple del proyecto al momento.
// 		+ pantalla deformable en tiempo real (proporci\u00f3n inicial 4 a 1 y opci\u00f3n a pantalla completa)
// 		+ entrada de audio estereo (1024, 44100)
// 		+ servidor de video para mezcla en otros programas (Resolume, Modul8, Quartz ...)
// 
//
// V.00 
// FeelGoodSoftwareporGuillermoGonz\u00e1lezIrigoyen
//
//////////////////////////////////////////////////////////////////////


//minim


//syphon

// ... importa tus librerias






//VARIABLES DE CONFIGURACI\u00d3N
//Tama\u00f1o de la pantalla , proporciones y tweak
boolean pCompleta   = false;
boolean pDeformable = true;
int proporcionP     = 4;
int pP,pW,pH;
//Syphon
SyphonServer server;
String nombreServer = "SIMPLEWRAP";
//Audio
Minim minim;
AudioInput in0;
int estereo = 1;
int buffer  = 1024;
int sampleR = 44100;
int profBit = 8;
// ... escribe tus variables aqu\u00ed






//
//##SETUP##
//
public void setup()
{
	//CANVAS
	pP = (pCompleta == true)? 1 : proporcionP;
	size(displayWidth/pP, displayHeight/pP, P3D);
	pW=width;pH=height;
	frame.setResizable(pDeformable);
	//SYPHON SERVER
	server 	= new SyphonServer(this, nombreServer);
	//AUDIO
	minim 	= new Minim(this);
	in0 	= minim.getLineIn(estereo, buffer, sampleR, profBit);
	// ... escribe tu setup aqu\u00ed
  

  


  
}




//
//##DRAW##
//
public void draw()
{
  // Para usar minim genera un loop del tama\u00f1o del buffer (1024) y usalo en la entrada de audio.
  background(0);
  for( int i = 0; i < buffer; i++ )
  {
  	stroke(255);
  	if(i>buffer||i==buffer){ i = PApplet.parseInt(norm(i,0,buffer)); }
   	line( i, (height/2)+in0.mix.get(i)*50, i+1, (height/2)+in0.mix.get(i)*50);
  }
  // ... borra y haz halgo
  







  
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
    //controles//
    case 'a':
       background(255);
       break;
    default:
       break;
    // ... programa el teclado





  } 
}







//
//##OVERS##
//
public boolean sketchFullScreen() { return pCompleta; }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "WRAP" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
