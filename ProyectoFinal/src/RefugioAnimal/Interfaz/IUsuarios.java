package RefugioAnimal.Interfaz;
import RefugioAnimal.Metodos.Usuario;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class IUsuarios extends JPanel implements ActionListener {
    private final static String REGISTRAR_PERRO = "Registrar Perro";
    private final static String REGISTRAR_DONACION = "Registrar Donacion";
    private JTextField txtNombre;
    private JTextField txtCedula;
    private JTextField txtCalcedad;
    private JButton btnRegistrarPerro;
    private JButton btnRegistrarDonacion;
    private InterfazRefugio principal;

    public IUsuarios(InterfazRefugio pPrincipal) {

        setBorder(new TitledBorder("Datos Usuario"));
        setLayout(new BorderLayout());
        principal = pPrincipal;

        JPanel panelDatos = new JPanel();
        panelDatos.setLayout(new GridLayout(9, 2, 5, 5));
        add(panelDatos, BorderLayout.CENTER);

        JLabel lblNombre = new JLabel("Nombre:");
        panelDatos.add(lblNombre);
        txtNombre = new JTextField();
        txtNombre.setEditable(false);
        panelDatos.add(txtNombre);

        JLabel lblCedula = new JLabel("Cedula:");
        panelDatos.add(lblCedula);
        txtCedula = new JTextField();
        txtCedula.setEditable(false);
        panelDatos.add(txtCedula);

        JLabel lblEdad = new JLabel("Edad:");
        panelDatos.add(lblEdad);
        txtCalcedad = new JTextField();
        txtCalcedad.setEditable(false);
        panelDatos.add(txtCalcedad);
        panelDatos.add( new JLabel( ) );
        panelDatos.add( new JLabel( ) );
        panelDatos.add( new JLabel( ) );
        panelDatos.add( new JLabel( ) );
        panelDatos.add( new JLabel( ) );
        panelDatos.add( new JLabel( ) );

        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new FlowLayout(FlowLayout.CENTER));
        add(panelBotones, BorderLayout.SOUTH);

        btnRegistrarPerro = new JButton(REGISTRAR_PERRO);
        btnRegistrarPerro.setEnabled(false);
        btnRegistrarPerro.setActionCommand(REGISTRAR_PERRO);
        btnRegistrarPerro.addActionListener(this);
        panelBotones.add(btnRegistrarPerro);

        btnRegistrarDonacion = new JButton(REGISTRAR_DONACION);
        btnRegistrarDonacion.setEnabled(false);
        btnRegistrarDonacion.setActionCommand(REGISTRAR_DONACION);
        btnRegistrarDonacion.addActionListener(this);
        panelBotones.add(btnRegistrarDonacion);
    }

    public void actualizar(Usuario pUsuario)
    {
        txtNombre.setText( pUsuario.darNombre( ) );
        txtCedula.setText( pUsuario.darCedula( ) );
        txtCalcedad.setText(Integer.toString(pUsuario.darCalcEdad()));

        btnRegistrarPerro.setEnabled( true );
        btnRegistrarDonacion.setEnabled( true );
    }
    public void actionPerformed( ActionEvent pEvento )
    {
        String comando = pEvento.getActionCommand( );

        if( comando.equals( REGISTRAR_PERRO ) )
        {
            RegistrarPerro dialogo = new RegistrarPerro(principal, principal.darPerrosUsuario( ));
            dialogo.setVisible( true );
        }
        else if( comando.equals( REGISTRAR_DONACION ) )
        {
            RegistrarDonacion dialogo = new RegistrarDonacion(principal, principal.darDonacionUsuario( ));
            dialogo.setVisible( true );
        }

    }
}
