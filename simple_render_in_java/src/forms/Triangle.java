package forms;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JSlider;

import matrix.Matrix;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Path2D;
import java.awt.image.BufferedImage;

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
    public void triangleForm(Graphics2D g2d, JSlider horizontal, JSlider vertical, JPanel jPanel){
    	double heading = Math.toRadians(horizontal.getValue());
    	
    	Matrix headingTransform = new Matrix(new double[] {
    	        Math.cos(heading), 0, Math.sin(heading),
    	        0, 1, 0,
    	        -Math.sin(heading), 0, Math.cos(heading)
    	    });
    	double pitch = Math.toRadians(vertical.getValue());
    	Matrix pitchTransform = new Matrix(new double[] {
    	        1, 0, 0,
    	        0, Math.cos(pitch), Math.sin(pitch),
    	        0, -Math.sin(pitch), Math.cos(pitch)
    	    });
    	Matrix transform = headingTransform.multiply(pitchTransform);
        List<Triangle> triangle = new ArrayList<Triangle>();
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
       
       
       BufferedImage img = 
   		    new BufferedImage(jPanel.getWidth(), jPanel.getHeight(), BufferedImage.TYPE_INT_ARGB);

   		for (Triangle t : triangle) {
   		    Vertex v1 = transform.transform(t.v1);
   		    Vertex v2 = transform.transform(t.v2);
   		    Vertex v3 = transform.transform(t.v3);

   		    // since we are not using Graphics2D anymore,
   		    // we have to do translation manually
   		    v1.x += jPanel.getWidth() / 2;
   		    v1.y += jPanel.getHeight() / 2;
   		    v2.x += jPanel.getWidth() / 2;
   		    v2.y += jPanel.getHeight() / 2;
   		    v3.x += jPanel.getWidth() / 2;
   		    v3.y += jPanel.getHeight() / 2;

   		    // compute rectangular bounds for triangle
   		    int minX = (int) Math.max(0, Math.ceil(Math.min(v1.x, Math.min(v2.x, v3.x))));
   		    int maxX = (int) Math.min(img.getWidth() - 1, 
   		                              Math.floor(Math.max(v1.x, Math.max(v2.x, v3.x))));
   		    int minY = (int) Math.max(0, Math.ceil(Math.min(v1.y, Math.min(v2.y, v3.y))));
   		    int maxY = (int) Math.min(img.getHeight() - 1,
   		                              Math.floor(Math.max(v1.y, Math.max(v2.y, v3.y))));

   		    double triangleArea =
   		       (v1.y - v3.y) * (v2.x - v3.x) + (v2.y - v3.y) * (v3.x - v1.x);

   		    for (int y = minY; y <= maxY; y++) {
   		        for (int x = minX; x <= maxX; x++) {
   		            double b1 = 
   		              ((y - v3.y) * (v2.x - v3.x) + (v2.y - v3.y) * (v3.x - x)) / triangleArea;
   		            double b2 =
   		              ((y - v1.y) * (v3.x - v1.x) + (v3.y - v1.y) * (v1.x - x)) / triangleArea;
   		            double b3 =
   		              ((y - v2.y) * (v1.x - v2.x) + (v1.y - v2.y) * (v2.x - x)) / triangleArea;
   		            if (b1 >= 0 && b1 <= 1 && b2 >= 0 && b2 <= 1 && b3 >= 0 && b3 <= 1) {
   		                img.setRGB(x, y, t.color.getRGB());
   		            }
   		            
   		        }
   		        
   		    }

   		}

   		g2d.drawImage(img, -150,-150, null);
   		
   	}
      
 }
    


