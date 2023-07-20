package RefugioAnimal.Interfaz;
import RefugioAnimal.Metodos.Usuario;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;

@SuppressWarnings("serial")
public class IRegistroAdopcion extends JDialog implements ActionListener{

    private JTextArea txtHistorial;
    private JButton botonAdoptar;
    private InterfazRefugio principal;

    public IRegistroAdopcion (ArrayList<Usuario> adopciones){
        setTitle("Historial de Adopciones");
        setSize(600, 400);
        setLocationRelativeTo(null);

        JPanel panelGeneral = new JPanel();
        panelGeneral.setLayout(new BorderLayout());
        add(panelGeneral);

        txtHistorial = new JTextArea();
        txtHistorial.setEditable(false);
        panelGeneral.add(new JScrollPane(txtHistorial), BorderLayout.CENTER);

        mostrarHistorialAdopciones (adopciones);
    }

    private void mostrarHistorialAdopciones (ArrayList <Usuario> adopciones){
        StringBuilder historial = new StringBuilder();
        historial.append("----  Historial de Adopciones  ----");
        for (Usuario adopcion : adopciones){
            historial.append("Fecha de Adopcion: ").append(adopcion.get()).append("\n");
            historial.append("Perro Adoptado: ").append(adopcion.().darNombreani()).append("\n");
            historial.append("------------------------\n");
        }
        txtHistorial.setText(historial.toString());
    }
}
