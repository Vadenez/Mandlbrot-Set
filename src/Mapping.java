import java.awt.Container;
import javax.swing.JFrame;

public class Mapping extends JFrame {

    private final int CA_WIDTH = 1024;
    private final int CA_HEIGHT = 1024;
    private final String CA_TITLE = "Mandelbrot Set";

    public Mapping() {
        this.configure();
    } // Mapping()

    private void configure() {
        this.setSize(CA_WIDTH, CA_HEIGHT);
        this.setTitle(CA_TITLE);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container pane = this.getContentPane();
        pane.add(new MappingPanel());

        this.setVisible(true);
    } // configure()

    public static void main(String[] args) {
        Mapping mapping
                = new Mapping();
    } // main( String [] )

} // Mapping
