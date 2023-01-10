import java.awt.*;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import javax.swing.*;



public class MappingPanel extends JPanel {



    private final int MappingPanel = 195;

    private final int BITMAP_WIDTH = 2048;
    private final int BITMAP_HEIGHT = 2048;

    private final BufferedImage image;


    public final double uMax = BITMAP_WIDTH;
    public final double uMin = 1;
    public final double vMax = BITMAP_HEIGHT;
    public final double vMin = 1;
    public final double xMin = -1;
    public final double yMin = -1;
    public final double xMax = 1;
    public final double yMax = 1;
    private int[][] bitmap;
    private int[] rhs;
    private final int x = 1;

    public MappingPanel() {

        int w = BITMAP_WIDTH;
        int h = BITMAP_HEIGHT;
        int imageType = BufferedImage.TYPE_INT_RGB;
        this.image = new BufferedImage(w, h, imageType);

        this.bitmap = new int[w][h];

        int code = MappingPanel;

        this.rhs = new int[8];

        for (int i = 0; i < 8; i++){
            if ((code % 2) == 1){
                this.rhs[i] = 1;
            }
            else{
                this.rhs[i] = 0;
            }

            code /= 2;
        }


    } // MappingPanel()


    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // Create an object to use in making
        // the picture fit the window.
        double w = this.getWidth();
        double h = this.getHeight();
        AffineTransform scale = new AffineTransform();
        scale.setToScale(w / BITMAP_WIDTH, h / BITMAP_HEIGHT);

        // Create an array of pixels
        WritableRaster raster = this.image.getRaster();


        // assign colors to pixels in raster
        for (int i = 0; i < BITMAP_HEIGHT; i++) {
            for (int j = 0; j < BITMAP_WIDTH; j++) {

                double x = xMin + (i - uMin)/(uMax - uMin) * (xMax-xMin);
                double y = yMin + (j - vMin)/(vMax - vMin) * (yMax-yMin);

                Color color = chooseColor(x,y);
                int[] colorArray = { color.getRed(), color.getGreen(), color.getBlue() };

                raster.setPixel( i, j, colorArray );

            } // for
        } // for

        g2d.drawImage(image, scale, this);
    } // paintComponent( Graphics )

    private Color chooseColor( double x, double y) {
        int count = 64;
        ComplexNumber z = new ComplexNumber(0.0,0.0);
        ComplexNumber c = new ComplexNumber(x,y);
        z = z.times(z);
        z = z.add(c);

        int i=0;
        while(i < count && z.magnitude() < 10){
            z = z.times(z);
            z = z.add(c);
            i++;
        }

        Color color = switch (i % 8){
            case 0 -> Color.YELLOW;
            case 1 -> Color.ORANGE;
            case 2 -> Color.RED;
            case 3 -> Color.BLACK;
            case 4 -> Color.DARK_GRAY;
            case 5 -> Color.GRAY;
            case 6 -> Color.LIGHT_GRAY;
            case 7 -> Color.WHITE;
            default -> Color.BLUE;
        };
        return color;
    }


} // MappingPanel