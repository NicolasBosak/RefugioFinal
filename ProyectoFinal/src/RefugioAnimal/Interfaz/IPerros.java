package RefugioAnimal.Interfaz;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.TitledBorder;

import RefugioAnimal.Metodos.Animal;
import RefugioAnimal.Metodos.Usuario;

public class IPerros extends JPanel implements ActionListener{
    private static final String ADOPTAR_PERRO = "Adoptar Perro";
    private static final String INGRESAR_VETERINARIA = "Ingresar Veterinaria";
    private JTextField txtNombre;
    private JTextField txtRaza;
    private JTextField txtPeso;
    private JTextField txtEdad;
    private JTextField txtSalud;
    private JTextField txtConcepto;
    private JTextField txtDiain;
    private JTextField txtMesin;
    private JTextField txtAnioin;
    private JList listaPerros;
    private JButton btnAdoptarPerro;
    private JButton btnIngresarVeterinaria;
    private InterfazRefugio principal;
    public IPerros(InterfazRefugio pPrincipal) {
        setBorder(new TitledBorder("Datos Perro"));
        setLayout(new BorderLayout());
        principal = pPrincipal;

        JPanel panelDatos = new JPanel();
        panelDatos.setLayout(new GridLayout(12, 2, 5, 5));
        add(panelDatos, BorderLayout.CENTER);

        JLabel lblNombre = new JLabel("Nombre:");
        panelDatos.add(lblNombre);
        txtNombre = new JTextField();
        txtNombre.setEditable(false);
        panelDatos.add(txtNombre);

        JLabel lblRaza = new JLabel("Raza:");
        panelDatos.add(lblRaza);
        txtRaza = new JTextField();
        txtRaza.setEditable(false);
        panelDatos.add(txtRaza);

        JLabel lblPeso = new JLabel("Peso:");
        panelDatos.add(lblPeso);
        txtPeso = new JTextField();
        txtPeso.setEditable(false);
        panelDatos.add(txtPeso);

        JLabel lblEdad = new JLabel("Edad:");
        panelDatos.add(lblEdad);
        txtEdad = new JTextField();
        txtEdad.setEditable(false);
        panelDatos.add(txtEdad);

        JLabel lblSalud = new JLabel("Estado de Salud:");
        panelDatos.add(lblSalud);
        txtSalud = new JTextField();
        txtSalud.setEditable(false);
        panelDatos.add(txtSalud);

        JLabel lblConcepto = new JLabel("Concepto:");
        panelDatos.add(lblConcepto);
        txtConcepto = new JTextField();
        txtConcepto.setEditable(false);
        panelDatos.add(txtConcepto);

        JLabel lblDia = new JLabel( "Dia:" );
        panelDatos.add( lblDia );
        txtDiain = new JTextField( );
        txtDiain.setEditable(false);
        panelDatos.add( txtDiain );

        JLabel lblMes = new JLabel( "Mes:" );
        panelDatos.add( lblMes );
        txtMesin = new JTextField( );
        txtMesin.setEditable(false);
        panelDatos.add( txtMesin );

        JLabel lblAnio = new JLabel( "Anio:" );
        panelDatos.add( lblAnio );
        txtAnioin = new JTextField( );
        txtAnioin.setEditable(false);
        panelDatos.add( txtAnioin );

        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new FlowLayout(FlowLayout.CENTER));
        add(panelBotones, BorderLayout.SOUTH);

        btnAdoptarPerro = new JButton(ADOPTAR_PERRO);
        btnAdoptarPerro.setEnabled(false);
        btnAdoptarPerro.setActionCommand(ADOPTAR_PERRO);
        btnAdoptarPerro.addActionListener(this);
        panelBotones.add(btnAdoptarPerro);

        btnIngresarVeterinaria = new JButton(INGRESAR_VETERINARIA);
        btnIngresarVeterinaria.setEnabled(false);
        btnIngresarVeterinaria.setActionCommand(INGRESAR_VETERINARIA);
        btnIngresarVeterinaria.addActionListener(this);
        panelBotones.add(btnIngresarVeterinaria);
    }
    public void actualizar(Animal pAnimal) {
        if (pAnimal != null) {
            txtNombre.setText(pAnimal.darNombreani());
            txtRaza.setText(pAnimal.darRazaani());
            txtPeso.setText(Integer.toString(pAnimal.darPesoani()));
            txtEdad.setText(Integer.toString(pAnimal.darEdadani()));

            Animal.Tipo Salud = pAnimal.darSalud();
            if (Salud != null) {
                switch (Salud) {
                    case SANO:
                        txtSalud.setText("SANO");
                        break;
                    case ENFERMO:
                        txtSalud.setText("ENFERMO");
                        break;
                }
            }
            txtConcepto.setText(pAnimal.darConcepto());
            txtDiain.setText(Integer.toString(pAnimal.darDiain()));
            txtMesin.setText(Integer.toString(pAnimal.darMesin()));
            txtAnioin.setText(Integer.toString(pAnimal.darAnioin()));

            if (btnAdoptarPerro != null) {
                btnAdoptarPerro.setEnabled(true);
            }
            if (btnIngresarVeterinaria != null) {
                btnIngresarVeterinaria.setEnabled(true);
            }
        } else {
        }
    }
    public void actionPerformed( ActionEvent pEvento )
    {
        String actionCommand = pEvento.getActionCommand( );

        if( ADOPTAR_PERRO.equals( actionCommand ) )
        {
            principal.adoptarPerro( );
        }
    }
    public boolean hayPerroSeleccionado( )
    {
        return listaPerros.getSelectedIndex( ) != -1;
    }
    public int darPosicionPerroSeleccionado( )
    {
        return listaPerros.getSelectedIndex( );
    }

    public void cambiarPerros( ArrayList<Animal> pAnimals){
        listaPerros.removeAll( );
        listaPerros.setListData( pAnimals.toArray() );
        if(!pAnimals.isEmpty()){
            listaPerros.setSelectedIndex( 0 );
            btnAdoptarPerro.setEnabled( true );
        }
        else{
            btnAdoptarPerro.setEnabled( false );
        }
    }
}