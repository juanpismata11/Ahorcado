package ProyectoAhorcado;

import javax.swing.*;
import javax.swing.plaf.DimensionUIResource;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;
import java.awt.*;

public class Ahorcado extends JPanel {

    private String[] palabras = {"los increibles", "señora influencer", "fight club", "nightcrawler", "the shining", "forrest gump", "inception", "matrix", "gladiador", "titanic", "avatar", "rey león", "braveheart", "interstellar", "avengers", "pulp fiction", "la la land", "dune", "intensamente", "the nice guys", "the truman show", "akira", "your name", "mi vecino totoro", "hereditary", "kung fu panda"};
    private ArrayList<JLabel> labels = new ArrayList<>();
    private String palabraActual;
    private JPanel panelPalabra;
    private PanelAhorcado panelAhorcado;
    public int errores = 0;
    private JLabel lab;
    private JLabel lab2;
    public JTextField letraTexto;

    public Ahorcado() {
      JFrame frame = new JFrame("Ventana");
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setSize(new DimensionUIResource(600, 600));
      frame.setVisible(true);
  
      JPanel panelPrincipal = new JPanel();
      panelPrincipal.setSize(600, 600);
      panelPrincipal.setOpaque(true);
      panelPrincipal.setLayout(null);
  
      JLabel bienvenida = new JLabel("AHORCADO");
      bienvenida.setBounds(270, 20, 150, 20);
      panelPrincipal.add(bienvenida);
  
      JLabel adivina = new JLabel("Intenta adivinar la pelicula!");
      adivina.setBounds(220, 50, 200, 20);
      panelPrincipal.add(adivina);
  
      JLabel letra = new JLabel("Letra:");
      letra.setBounds(20, 90, 50, 20);
      panelPrincipal.add(letra);
  
      letraTexto = new JTextField();
      letraTexto.setBounds(80, 90, 110, 25);
      panelPrincipal.add(letraTexto);
  
      JButton enviar = new JButton("Adivinar");
      enviar.setBounds(220, 89, 80, 25);
      panelPrincipal.add(enviar);
  
      JLabel caracteres = new JLabel("La película tiene ___ caracteres");
      caracteres.setBounds(20, 130, 200, 25);
      panelPrincipal.add(caracteres);
  
      panelAhorcado = new PanelAhorcado();
      panelAhorcado.setBounds(50, 150, 500, 350);
      panelAhorcado.setLayout(null);
      panelPrincipal.add(panelAhorcado);
  
      JButton resetear = new JButton("INICIAR JUEGO");
      resetear.setBounds(230, 500, 150, 40);
      panelPrincipal.add(resetear);
  
      JPanel panelError = new JPanel();
      panelError.setLayout(new FlowLayout());
      panelError.setOpaque(false);
      panelError.setBounds(280, 230, 200, 30);
      panelPrincipal.add(panelError);
  
      lab = new JLabel();
      lab2 = new JLabel();
      ImageIcon imagen = new ImageIcon("C:/Users/JP/Documents/Clases Visual/pau mala clase/ProyectoAhorcado/dunkIzq.png");
      ImageIcon imagen2 = new ImageIcon("C:/Users/JP/Documents/Clases Visual/pau mala clase/ProyectoAhorcado/dunkDer.png");
      Image original = imagen.getImage();
      Image original2 = imagen2.getImage();
  
      int widNuevo = 50;
      int heiNuevo = 50;
      Image nuevo = original.getScaledInstance(widNuevo, heiNuevo, Image.SCALE_SMOOTH);
      Image nuevo2 = original2.getScaledInstance(widNuevo, heiNuevo, Image.SCALE_SMOOTH);
  
      ImageIcon nuevaImg = new ImageIcon(nuevo);
      ImageIcon nuevaImg2 = new ImageIcon(nuevo2);
      lab.setIcon(nuevaImg);
      lab2.setIcon(nuevaImg2);
      panelAhorcado.add(lab);
      panelAhorcado.add(lab2);
      lab.setBounds(155, 200, 100, 100);
      lab2.setBounds(250, 200, 100, 100);
  
      lab.setVisible(false);
      lab2.setVisible(false);
  
      iniciarJuego();
  
      enviar.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
              String letraIngresada = letraTexto.getText().toLowerCase();
              letraTexto.setText("");
              boolean letraCorrecta = false;
              for (int i = 0; i < palabraActual.length(); i++) {
                  if (String.valueOf(palabraActual.charAt(i)).equals(letraIngresada)) {
                      labels.get(i).setVisible(true);
                      letraCorrecta = true;
                  }
              }
  
              if (!letraCorrecta) {
                  JLabel error = new JLabel(letraIngresada + " -");
                  panelError.add(error);
                  panelAhorcado.add(panelError);
                  panelError.revalidate();
                  panelError.repaint();
                  errores += 1;
                  System.out.println(errores);
              }
          }
      });
  
      resetear.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
              resetear.setText("REINICIAR JUEGO");
              iniciarJuego();
              caracteres.setText("La película tiene " + palabraActual.length() + " caracteres");
              errores = 0;
              panelError.removeAll();
              panelError.revalidate();
              panelError.repaint();
              letraTexto.setEditable(true);
              lab.setVisible(false);
              lab2.setVisible(false);

          }
      });
  
      frame.add(panelPrincipal);
      panelPrincipal.revalidate();
      panelPrincipal.repaint();
  }
  

    private void iniciarJuego() {
        labels.clear();
        if(panelPalabra != null) {
            panelAhorcado.remove(panelPalabra);
        }
        panelPalabra = new JPanel();
        panelPalabra.setLayout(new FlowLayout());
        panelPalabra.setOpaque(false);
        panelPalabra.setBounds(280, 160, 220, 30);

        palabraActual = palabras[new Random().nextInt(palabras.length)];
        for(int i = 0; i < palabraActual.length(); i++) {
            JLabel label = new JLabel(String.valueOf(palabraActual.charAt(i)));
            label.setVisible(false);
            labels.add(label);
            panelPalabra.add(label);
        }

        panelAhorcado.add(panelPalabra);
        reVaPa();
    }

    public void reVaPa(){
      panelPalabra.revalidate();
      panelPalabra.repaint();
      panelAhorcado.revalidate();
      panelAhorcado.repaint();
    }

    public class PanelAhorcado extends JPanel{
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawLine(50, 280, 200, 280);
            g.drawLine(125, 280, 125, 50);
            g.drawLine(125, 50, 225, 50);
            g.drawLine(225, 50, 225, 80);
            g.drawLine(280, 200, 500, 200);

            switch(errores){
              case 1:
                g.setColor(Color.RED);
                g.fillOval(200, 75, 50, 50);
                reVaPa();
                break;
              case 2:
                g.drawLine(225, 100, 225, 200);
                g.setColor(Color.RED);
                g.fillOval(200, 75, 50, 50);
                g.setColor(Color.BLACK);
                reVaPa();
                break;
              case 3:
                g.drawLine(225, 100, 225, 200);
                g.setColor(Color.RED);
                g.fillOval(200, 75, 50, 50);
                g.setColor(Color.BLACK);
                g.drawLine(225, 130, 200, 160);
                reVaPa();
                break;
              case 4:
                g.drawLine(225, 100, 225, 200);
                g.setColor(Color.RED);
                g.fillOval(200, 75, 50, 50);
                g.setColor(Color.BLACK);
                g.drawLine(225, 130, 200, 160);
                g.drawLine(225, 130, 250, 160);
                reVaPa();
                break;
              case 5:
                g.drawLine(225, 100, 225, 200);
                g.setColor(Color.RED);
                g.fillOval(200, 75, 50, 50);
                g.setColor(Color.BLACK);
                g.drawLine(225, 130, 200, 160);
                g.drawLine(225, 130, 250, 160);
                g.drawLine(225, 200, 200, 240);
                lab.setVisible(true);
                reVaPa();
                break;
              case 6:
                g.drawLine(225, 100, 225, 200);
                g.setColor(Color.RED);
                g.fillOval(200, 75, 50, 50);
                g.setColor(Color.BLACK);
                g.drawLine(225, 130, 200, 160);
                g.drawLine(225, 130, 250, 160);
                g.drawLine(225, 200, 200, 240);
                lab.setVisible(true);
                g.drawLine(225, 200, 250, 240);
                lab2.setVisible(true);
                letraTexto.setEditable(false);
                reVaPa();
                break;
            }

        }
    }

    public static void main(String[] args) {
        Ahorcado mono = new Ahorcado();
    }
}
