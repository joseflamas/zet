//////////////////////////////////////////////////////////////////////
// 
// WRAP 
// Syphon/minim/fun000
// 
// HORIZONTALMIX
//
// V.00 
// FeelGoodSoftwareporGuillermoGonzálezIrigoyen
//
//////////////////////////////////////////////////////////////////////

//minim
import ddf.minim.*;
import ddf.minim.analysis.*;
//syphon
import codeanticode.syphon.*;
// ... importa tus librerias






//VARIABLES DE CONFIGURACIÓN
//Tamaño de la pantalla , proporciones y tweak
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
// ... escribe tus variables aquí






//
//##SETUP##
//
void setup()
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
	// ... escribe tu setup aquí
  

  


  
}




//
//##DRAW##
//
void draw()
{
  // Para usar minim genera un loop del tamaño del buffer (1024) y usalo en la entrada de audio.
  background(0);
  for( int i = 0; i < buffer; i++ )
  {
  	stroke(255);
  	if(i>buffer||i==buffer){ i = int(norm(i,0,buffer)); }
   	line( i, (height/2)+in0.mix.get(i)*50, i+1, (height/2)+in0.mix.get(i)*50);
  }
  // ... borra y haz halgo
  







  
  //SYPHON SERVER
  server.sendScreen();
}





//
//##CONTROLES##
//
void keyPressed() 
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
boolean sketchFullScreen() { return pCompleta; }