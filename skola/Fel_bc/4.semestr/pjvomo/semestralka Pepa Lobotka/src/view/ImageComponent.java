package view;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import javax.swing.JComponent;

/*******************************************************************************
 * Component for Image.
 *
 * @author NeoGenet1c
 * @version 0.9
 */
public class ImageComponent extends JComponent {


//== CONSTANT CLASS ATTRIBUTES =================================================
//== VARIABLE CLASS ATTRIBUTES =================================================
//== CONSTANT INSTANCE ATTRIBUTES ==============================================
//== VARIABLE INSTANCE ATTRIBUTES ==============================================


  // IMG properties
  private Image image;
  private Dimension size;



//== CLASS GETTERS & SETTERS ===================================================
//== OTHER NON-PRIVATE CLASS METHODS ===========================================
//== CONSTRUCTORS & FACTORY METHODS ============================================


  public ImageComponent(Image image) throws InterruptedException {
    this.image = image;
    MediaTracker mt = new MediaTracker(this);
    mt.addImage(image, 0);

    mt.waitForAll(  );
    
    size = new Dimension (image.getWidth(null),
                          image.getHeight(null));
    setSize(size);
  }



//== ABSTRACT METHODS ==========================================================
//== INSTANCE GETTERS & SETTERS ================================================
//== OTHER NON-PRIVATE INSTANCE METHODS ========================================
//== PRIVATE & HELPFUL CLASS METHODS ===========================================
//== PRIVATE & HELPFUL INSTANCE METHODS ========================================


/*******************************************************************************
 * Overrides basic paint method to paint a picture.
 *
 * @param g Graphic context.
 */
  @Override
  public void paint(Graphics g) {
    g.drawImage(image, 0, 0, this);
  }


/*******************************************************************************
 * Gets dimensions of picture.
 *
 * @return Picture's dimensions.
 */
  @Override
  public Dimension getPreferredSize(  ) {
    return size;
  }


//== NESTING & INNER CLASSES ===================================================
//== MAIN METHOD ===============================================================  

}
