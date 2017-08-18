package arbolB;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import static javax.swing.JFrame.EXIT_ON_CLOSE;

public class Main extends JFrame implements ActionListener {
    int FRAME_HEIGHT;
    int FRAME_WIDTH;
    int FRAME_X_ORIGIN;
    int FRAME_Y_ORIGIN;
    private final JLabel response;
    private final JMenu AccionMenu;
    JMenuItem item;
    Arbol arbol;
    JTextArea areaTexto;
    public static void main(String[] args) {
        Main frame = new Main();
        frame.setVisible(true);
    }

    public Main(){

        FRAME_HEIGHT = 700;
        FRAME_WIDTH = 700;
        FRAME_X_ORIGIN = 400;
        FRAME_Y_ORIGIN = 100;

        Container contentPane = getContentPane();

        int x;
        String entrada;
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        setResizable(true);
        setTitle("Árbol B");
        setLocation(FRAME_X_ORIGIN, FRAME_Y_ORIGIN);
        contentPane.setLayout(null);
        contentPane.setBackground(new Color(180, 215, 191));

        areaTexto = new JTextArea(665,670);
        areaTexto.setEditable(false);

        Font font = new Font("Verdana", Font.BOLD, 30);
        areaTexto.setFont(font);
        JScrollPane scroll = new JScrollPane(areaTexto);
        scroll.setBounds(5, 5, 665, 675);
        contentPane.add(scroll);

        AccionMenu = new JMenu("Acciones");

        item = new JMenuItem("Iniciar de Nuevo");
        item.addActionListener( this );
        AccionMenu.add( item );

        item = new JMenuItem("Agregar un dato");
        item.addActionListener( this );
        AccionMenu.add( item );

        item = new JMenuItem("Eliminar un dato");
        item.addActionListener( this );
        AccionMenu.add( item );

        AccionMenu.addSeparator();

        item = new JMenuItem("Salir del programa");
        item.addActionListener( this );
        AccionMenu.add( item );

        JMenuBar BarraMenu = new JMenuBar();
        setJMenuBar(BarraMenu);
        BarraMenu.add(AccionMenu);
        response = new JLabel("");
        response.setBounds(50,50,50,50);
        contentPane.add(response);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        areaTexto.setText("");
        entrada = JOptionPane.showInputDialog(null, "Orden: ");
        x = Integer.parseInt(entrada);
        arbol = new Arbol(x);
    }

    public void actionPerformed(ActionEvent event) {
        String  MenuName, entrada;
        int dato;
        MenuName = event.getActionCommand();
        if (MenuName.equals("Salir del programa"))
        {
            System.exit(0);
        }
        else
        {
            if(MenuName.equals("Agregar un dato"))
            {
                entrada = JOptionPane.showInputDialog(null, "Agregar dato: ");
                dato = Integer.parseInt(entrada);
                arbol.agregar(dato);
                areaTexto.setText(arbol.toString());

        }
            else if (MenuName.equals("Iniciar de Nuevo"))
            {
                String[] str = new String[0];
                main(str);
            }
        }
    }
}




