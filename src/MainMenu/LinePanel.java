package MainMenu;
import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

/**
 * @see http://stackoverflow.com/questions/6991648
 * @see http://stackoverflow.com/questions/6887296
 * @see http://stackoverflow.com/questions/5797965
 */
public class LinePanel extends JPanel {

	//fencer's variables
	private int LenSword = 264;
	private int LenForeArm = 88;
	private int LenUpArm = 88;
	
	//initial points for body parts
	private Point shoulder = new Point(200, 100); //shoulder point
	private Point elbow = new Point(shoulder.x, 100 - LenUpArm);
	private Point wrist = new Point(elbow.x + LenForeArm, 100); //wrist location
	private Point tip = new Point(wrist.x, LenSword); //tip of sabre
	private MouseHandler mouseHandler = new MouseHandler();
    private boolean drawing;

    public LinePanel() {
        this.setPreferredSize(new Dimension(640, 480)); //dimensions of JPanel
        this.addMouseListener(mouseHandler);
        this.addMouseMotionListener(mouseHandler);
    }

    protected void paintArm(Graphics h) {
        
    }
    
    //blade
    protected void paintComponent(Graphics g) {
        
    	//forearm
    	super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.blue);
        g2d.setRenderingHint(
            RenderingHints.KEY_ANTIALIASING,
            RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setStroke(new BasicStroke(8,
           BasicStroke.CAP_ROUND, BasicStroke.JOIN_BEVEL));
        g.drawLine(elbow.x, elbow.y, wrist.x, wrist.y);
    	
    	super.paintComponent(g);
        //Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.blue);
        g2d.setRenderingHint(
            RenderingHints.KEY_ANTIALIASING,
            RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setStroke(new BasicStroke(8,
            BasicStroke.CAP_ROUND, BasicStroke.JOIN_BEVEL));
        g.drawLine(wrist.x, wrist.y, tip.x, tip.y);
    }

    private class MouseHandler extends MouseAdapter {

        @Override
        public void mousePressed(MouseEvent e) {
            drawing = true;
            wrist = e.getPoint();
            tip = wrist;
            repaint();
        }

        @Override
        //if mouse is no longer pressed down
        public void mouseReleased(MouseEvent e) {
            drawing = false;
            tip = e.getPoint();
            repaint();
        }

        @Override
        public void mouseDragged(MouseEvent e) {
            if (drawing) {
                tip = e.getPoint();
                repaint();
            }
        }
    }

    private class ControlPanel extends JPanel {

        private static final int DELTA = 10;

        public ControlPanel() {
            this.add(new MoveButton("\u2190", KeyEvent.VK_LEFT, -DELTA, 0));
            this.add(new MoveButton("\u2191", KeyEvent.VK_UP, 0, -DELTA));
            this.add(new MoveButton("\u2192", KeyEvent.VK_RIGHT, DELTA, 0));
            this.add(new MoveButton("\u2193", KeyEvent.VK_DOWN, 0, DELTA));
        }        
        
        private class MoveButton extends JButton {

            KeyStroke k;
            int dx, dy;

            public MoveButton(String name, int code, final int dx, final int dy) {
                super(name);
                this.k = KeyStroke.getKeyStroke(code, 0);
                this.dx = dx;
                this.dy = dy;
                this.setAction(new AbstractAction(this.getText()) {

                    @Override
                    //redraw the line after it's been told to move
                    public void actionPerformed(ActionEvent e) {
                        LinePanel.this.wrist.translate(dx, dy);
                        LinePanel.this.tip.translate(dx, dy);
                        LinePanel.this.repaint();
                    }
                });
                ControlPanel.this.getInputMap(
                    WHEN_IN_FOCUSED_WINDOW).put(k, k.toString());
                ControlPanel.this.getActionMap().put(k.toString(), new AbstractAction() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        MoveButton.this.doClick();
                    }
                });
            }
        }
    }
    
    private void display() {
        JFrame f = new JFrame("LinePanel");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.add(this);
        f.add(new ControlPanel(), BorderLayout.SOUTH);
        f.pack();
        f.setLocationRelativeTo(null);
        f.setVisible(true);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                new LinePanel().display();
            }
        });
    }
    
}
