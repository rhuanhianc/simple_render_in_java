package frame;
import java.awt.*;
import javax.swing.*;

public class FrameClass {
    
    public Container conteiner(Container contentPane){
        contentPane.setLayout(new BorderLayout());
  
        JSlider horizontalSlider = new JSlider(JSlider.HORIZONTAL, 0, 360, 100);
        contentPane.add(horizontalSlider, BorderLayout.SOUTH);
      
        JSlider verticalSlider = new JSlider(JSlider.VERTICAL, -90, 90, 0);
        contentPane.add(verticalSlider, BorderLayout.EAST);
      
        JPanel renderPanel = new JPanel(){
          public void showRender(Graphics graphics){
              Graphics2D g2d = (Graphics2D) graphics;
              g2d.setColor(Color.BLACK);
              g2d.fillRect(0, 0, getWidth(), getHeight());
          }
        };
        contentPane.add(renderPanel, BorderLayout.CENTER);
        return contentPane;
    }
}
