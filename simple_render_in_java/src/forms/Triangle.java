package forms;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JSlider;

import matrix.Matrix;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Path2D;

import vertex.Vertex;

public class Triangle {
    public Vertex v1;
    public Vertex v2;
    public Vertex v3;
    public Color color;
   public Triangle(Vertex v1, Vertex v2, Vertex v3, Color color){
        this.v1 = v1;
        this.v2 = v2;
        this.v3 = v3;
        this.color = color;
    }
   public Triangle(){
      
   }
    public void triangleForm(Graphics2D g2d, JSlider horizontal){
    	double heading = Math.toRadians(horizontal.getValue());
    	
    	Matrix transform = new Matrix(new double[] {
    	        Math.cos(heading), 0, -Math.sin(heading),
    	        0, 1, 0,
    	        Math.sin(heading), 0, Math.cos(heading)
    	    });


        List<Triangle> triangle = new ArrayList<>();
        triangle.add(new Triangle(new Vertex(100, 100, 100),
                      new Vertex(-100, -100, 100),
                      new Vertex(-100, 100, -100),
                      Color.WHITE));
        triangle.add(new Triangle(new Vertex(100, 100, 100),
                      new Vertex(-100, -100, 100),
                      new Vertex(100, -100, -100),
                      Color.RED));
       triangle.add(new Triangle(new Vertex(-100, 100, -100),
                      new Vertex(100, -100, -100),
                      new Vertex(100, 100, 100),
                      Color.GREEN));
       triangle.add(new Triangle(new Vertex(-100, 100, -100),
                      new Vertex(100, -100, -100),
                      new Vertex(-100, -100, 100),
                      Color.BLUE));
       g2d.translate(getWidth() / 2, getHeight() / 2);
       g2d.setColor(Color.WHITE);
       for (Triangle t : triangle) {
    	   Vertex v1 = transform.transform(t.v1);
    	    Vertex v2 = transform.transform(t.v2);
    	    Vertex v3 = transform.transform(t.v3);
    	    Path2D path = new Path2D.Double();
    	    path.moveTo(v1.x, v1.y);
    	    path.lineTo(v2.x, v2.y);
    	    path.lineTo(v3.x, v3.y);
    	    path.closePath();
    	    g2d.draw(path);}
 }
    }
