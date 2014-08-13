import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import java.util.Iterator; 
import ddf.minim.*; 
import ddf.minim.analysis.*; 
import codeanticode.syphon.*; 
import toxi.math.waves.*; 
import toxi.geom.*; 
import toxi.geom.mesh.*; 

import toxi.util.datatypes.*; 
import toxi.math.noise.*; 
import toxi.math.waves.*; 
import toxi.geom.*; 
import toxi.math.*; 
import toxi.geom.mesh.*; 
import toxi.math.conversion.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class lostheritage extends PApplet {

//////////////////////////////////////////////////////////////////////
// 
// LOSTHERITAGEByLAO
// GENERATIVEVIDEO
//
// V.00 
// FeelGoodSoftwareporGuillermoGonz\u00e1lezIrigoyen
//
//////////////////////////////////////////////////////////////////////

//java iter tools

//minim


//syphon

//toxic








//VARIABLES DE CONFIGURACI\u00d3N
//Tama\u00f1o de la pantalla , proporciones y tweak 
boolean pCompleta   = false;
boolean pDeformable = true;
int proporcionP     = 4;
int pP,pW,pH;
//Syphon
SyphonServer server;
String nombreServer = "lostHeritage";
//Audio
Minim minim;
AudioInput in0;
BeatDetect beat;
BeatListener bl;
int estereo = 1;
int buffer  = 1024;
int sampleR = 44100;
int profBit = 8;
//Toxic 
//Objeto Harm\u00f3nico
TriangleMesh mesh = new TriangleMesh();
boolean isWireFrame;
boolean showNormals;
boolean doSave;
Matrix4x4 normalMap = new Matrix4x4().translateSelf(128,128,128).scaleSelf(127);

AbstractWave waveX,waveY;
int STEP = 10;
int DIM = 800;
int D2 = DIM/2;
int AMP = 50;






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
  server  = new SyphonServer(this, nombreServer);
  //AUDIO
  minim   = new Minim(this);
  in0   = minim.getLineIn(estereo, buffer, sampleR, profBit);
  beat = new BeatDetect(in0.bufferSize(), in0.sampleRate());
  beat.setSensitivity(100);
  bl = new BeatListener(beat, in0);
  // ... escribe tu setup aqu\u00ed
  randomizeMesh();

  


  
}




//
//##DRAW##
//
public void draw()
{

  
  background(0);
  translate(width / 2, height / 2, 0);
  rotateX(cos(millis()) * 0.01f);
  rotateY(sin(millis()) * 0.01f);
  //lights();
  // shininess(16);
  // directionalLight(255,255,255,0,-1,1);
  // specular(255);
  noStroke();
  if (beat.isKick()) {
    lights();
    shininess(random(0,20));
    directionalLight(random(0,255),random(0,255),random(0,255),0,-1,1);
    specular(random(0,255));
    randomizeMesh();
    translate(random(-width/2,width/2), random(-height/2,height/2), 0);
    //drawMesh(g, mesh, !isWireFrame, showNormals);
    translate(random(-width/2,width/2), random(-height/2,height/2), 0);
    //drawMesh(g, mesh, !isWireFrame, showNormals);
    translate(random(-width/2,width/2), random(-height/2,height/2), 0);
    //drawMesh(g, mesh, !isWireFrame, showNormals);
  } 
  if (beat.isSnare()) {
    lights();
    shininess(random(10,25));
    directionalLight(255,0,0,0,-1,1);
    specular(random(0,255));
    //randomizeMesh();
    //drawMesh(g, mesh, !isWireFrame, showNormals);
  } 
  if (beat.isHat()) {
    lights();
    shininess(16);
    directionalLight(0,0,255,1,0,1);
    specular(random(0,255));
    //randomizeMesh();
    //drawMesh(g, mesh, !isWireFrame, showNormals);
  } 

  

 drawMesh(g, mesh, !isWireFrame, showNormals);





  
  //SYPHON SERVER
  server.sendScreen();
}

class BeatListener implements AudioListener
{
  private BeatDetect beat;
  private AudioInput source;
  
  BeatListener(BeatDetect beat, AudioInput source)
  {
    this.source = source;
    this.source.addListener(this);
    this.beat = beat;
  }
  
  public void samples(float[] samps)
  {
    beat.detect(source.mix);
  }
  
  public void samples(float[] sampsL, float[] sampsR)
  {
    beat.detect(source.mix);
  }
}


public void randomizeMesh() {
  float[] m=new float[8];
  for(int i=0; i<8; i++) {
    m[i]=(int)random(9);
    // SurfaceMeshBuilder b = new SurfaceMeshBuilder(new SphericalHarmonics(m));
    // mesh = b.createMesh(120, 90);//b.createMesh(80, 50);
  }
  SurfaceMeshBuilder b = new SurfaceMeshBuilder(new SphericalHarmonics(m));
  mesh = b.createMesh(80,50);//(120, 90);//b.createMesh(80, 50);
}


public void drawMesh(PGraphics gfx, TriangleMesh mesh, boolean vertexNormals, boolean showNormals) {
  gfx.beginShape(PConstants.TRIANGLES);
  AABB bounds=mesh.getBoundingBox();
  Vec3D min=bounds.getMin();
  Vec3D max=bounds.getMax();
  if (vertexNormals) {
    for (Iterator i=mesh.faces.iterator(); i.hasNext();) {
      TriangleMesh.Face f=(TriangleMesh.Face)i.next();
      Vec3D n = normalMap.applyTo(f.a.normal);
      gfx.fill(n.x, n.y, n.z);
      gfx.normal(f.a.normal.x, f.a.normal.y, f.a.normal.z);
      gfx.vertex(f.a.x, f.a.y, f.a.z);
      n = normalMap.applyTo(f.b.normal);
      gfx.fill(n.x, n.y, n.z);
      gfx.normal(f.b.normal.x, f.b.normal.y, f.b.normal.z);
      gfx.vertex(f.b.x, f.b.y, f.b.z);
      n = normalMap.applyTo(f.c.normal);
      gfx.fill(n.x, n.y, n.z);
      gfx.normal(f.c.normal.x, f.c.normal.y, f.c.normal.z);
      gfx.vertex(f.c.x, f.c.y, f.c.z);
    }
  } 
  else {
    for (Iterator i=mesh.faces.iterator(); i.hasNext();) {
      TriangleMesh.Face f=(TriangleMesh.Face)i.next();
      gfx.normal(f.normal.x, f.normal.y, f.normal.z);
      gfx.vertex(f.a.x, f.a.y, f.a.z);
      gfx.vertex(f.b.x, f.b.y, f.b.z);
      gfx.vertex(f.c.x, f.c.y, f.c.z);
    }
  }
  gfx.endShape();
  if (showNormals) {
    if (vertexNormals) {
      for (Iterator i=mesh.vertices.values().iterator(); i.hasNext();) {
        TriangleMesh.Vertex v=(TriangleMesh.Vertex)i.next();
        Vec3D w = v.add(v.normal.scale(5));
        Vec3D n = v.normal.scale(127);
        gfx.stroke(n.x + 128, n.y + 128, n.z + 128);
        gfx.line(v.x, v.y, v.z, w.x, w.y, w.z);
      }
    } 
    else {
      for (Iterator i=mesh.faces.iterator(); i.hasNext();) {
        TriangleMesh.Face f=(TriangleMesh.Face)i.next();
        Vec3D c = f.a.add(f.b).addSelf(f.c).scaleSelf(1f / 3);
        Vec3D d = c.add(f.normal.scale(5));
        Vec3D n = f.normal.scale(127);
        gfx.stroke(n.x + 128, n.y + 128, n.z + 128);
        gfx.line(c.x, c.y, c.z, d.x, d.y, d.z);
      }
    }
  }
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
    String[] appletArgs = new String[] { "lostheritage" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
