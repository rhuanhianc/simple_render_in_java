package frame;
import java.awt.*;
import javax.swing.*;

import forms.Triangle;



public class FrameClass {
    
    public Container conteiner(Container contentPane){
        contentPane.setLayout(new BorderLayout());
  
        JSlider horizontalSlider = new JSlider(JSlider.HORIZONTAL, 0, 360, 100);
        contentPane.add(horizontalSlider, BorderLayout.SOUTH);
      
        JSlider verticalSlider = new JSlider(JSlider.VERTICAL, -90, 90, 0);
        contentPane.add(verticalSlider, BorderLayout.EAST);
     
        JPanel renderPanel = new JPanel(){
          /**
			 * 
			 */
			private static final long serialVersionUID = 1L;

		public void paintComponent(Graphics graphics){
              Graphics2D g2d = (Graphics2D) graphics;
              g2d.fillRect(0, 0, getWidth(), getHeight());
              g2d.translate(getWidth() / 2, getHeight() / 2);

              Triangle tri = new Triangle();
              tri.triangleForm(g2d,horizontalSlider,verticalSlider, this);
              
          }
        };
        verticalSlider.addChangeListener(e -> renderPanel.repaint());
        horizontalSlider.addChangeListener(e -> renderPanel.repaint());
        contentPane.add(renderPanel, BorderLayout.CENTER);
        return contentPane;
    }

}
