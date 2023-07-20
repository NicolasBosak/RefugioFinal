package RefugioAnimal.Interfaz;
import RefugioAnimal.Metodos.Animal;
import RefugioAnimal.Metodos.Animal.Tipo;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

public class RegistrarPerro extends JDialog implements ActionListener{
    private static final String REGISTRAR_PERRO = "Registrar Perro";
    private JTextField txtNombre;
    private JTextField txtRaza;
    private JTextField txtEdad;
    private JTextField txtPeso;
    private JTextField txtConcepto;
    private JTextField txtDiain;
    private JTextField txtMesin;
    private JTextField txtAnioin;
    private JButton btnRegistrarPerro;
    private JComboBox<String> cmbEstadoSalud;
    private static final String ESTADO_SANO = "SANO";
    private static final String ESTADO_ENFERMO = "ENFERMO";
    private InterfazRefugio principal;

    public RegistrarPerro(InterfazRefugio pPrincipal, ArrayList<Animal> animals){

        principal = pPrincipal;
        setTitle( "Registrar Perro" );
        setSize( 350, 375 );
        setLocationRelativeTo( principal );

        JPanel panelGeneral = new JPanel( );
        panelGeneral.setBorder( new CompoundBorder( new TitledBorder( "Datos del nuevo perro" ), new EmptyBorder( 3, 3, 3, 3 ) ) );
        panelGeneral.setLayout( new BorderLayout( ) );
        add( panelGeneral );

        JPanel panelDatos = new JPanel( );
        panelDatos.setLayout( new GridLayout( 10, 4, 6, 6) );
        panelGeneral.add( panelDatos, BorderLayout.CENTER );

        JLabel lblNombre = new JLabel( "Nombre:" );
        panelDatos.add( lblNombre );
        txtNombre = new JTextField( );
        panelDatos.add(txtNombre);
        ((AbstractDocument) txtNombre.getDocument()).setDocumentFilter(new LetterDocumentFilter());

        JLabel lblRaza = new JLabel( "Raza:" );
        panelDatos.add(lblRaza);
        txtRaza = new JTextField( );
        panelDatos.add(txtRaza);
        ((AbstractDocument) txtRaza.getDocument()).setDocumentFilter(new LetterDocumentFilter());

        JLabel lblEdad = new JLabel( "Edad:" );
        panelDatos.add( lblEdad );
        txtEdad = new JTextField( );
        panelDatos.add(txtEdad);
        ((AbstractDocument) txtEdad.getDocument()).setDocumentFilter(new NumberDocumentFilter());

        JLabel lblPeso = new JLabel( "Peso:" );
        panelDatos.add( lblPeso );
        txtPeso = new JTextField( );
        panelDatos.add(txtPeso);
        ((AbstractDocument) txtPeso.getDocument()).setDocumentFilter(new NumberDocumentFilter());


        JLabel etiquetaTipo = new JLabel( "Estado de Salud:" );
        panelDatos.add( etiquetaTipo );
        cmbEstadoSalud = new JComboBox<String>( );
        cmbEstadoSalud.addItem( ESTADO_SANO );
        cmbEstadoSalud.addItem( ESTADO_ENFERMO );
        panelDatos.add( cmbEstadoSalud );

        JLabel lblConcepto = new JLabel( "Concepto:" );
        panelDatos.add( lblConcepto );
        txtConcepto = new JTextField( );
        panelDatos.add( txtConcepto );

        JLabel lblDia = new JLabel( "Dia:" );
        panelDatos.add( lblDia );
        txtDiain = new JTextField( );
        panelDatos.add( txtDiain );
        ((AbstractDocument) txtDiain.getDocument()).setDocumentFilter(new NumberDocumentFilter());

        JLabel lblMes = new JLabel( "Mes:" );
        panelDatos.add( lblMes );
        txtMesin = new JTextField( );
        panelDatos.add( txtMesin );
        ((AbstractDocument) txtMesin.getDocument()).setDocumentFilter(new NumberDocumentFilter());

        JLabel lblAnio = new JLabel( "Anio:" );
        panelDatos.add( lblAnio );
        txtAnioin = new JTextField( );
        panelDatos.add( txtAnioin );
        ((AbstractDocument) txtAnioin.getDocument()).setDocumentFilter(new NumberDocumentFilter());

        panelDatos.add( new JLabel( ) );

        btnRegistrarPerro = new JButton( "Registrar" );
        btnRegistrarPerro.setActionCommand( REGISTRAR_PERRO );
        btnRegistrarPerro.addActionListener( this );
        panelGeneral.add( btnRegistrarPerro, BorderLayout.SOUTH );
    }
    public void actionPerformed(ActionEvent pEvento){
        String actionCommand = pEvento.getActionCommand( );
        if( REGISTRAR_PERRO.equals( actionCommand ) )
        {
            String strRaza = txtRaza.getText( );
            String strConcepto = txtConcepto.getText( );
            String strNombre = txtNombre.getText();
            String strEstadoSalud = ( String )cmbEstadoSalud.getSelectedItem( );
            Tipo salud = null;
            switch( ( strEstadoSalud ) )
            {
                case ( ESTADO_SANO ):
                    salud = Tipo.SANO;
                    break;
                case ( ESTADO_ENFERMO ):
                    salud = Tipo.ENFERMO;
                    break;
            }
            if( strNombre == null || strNombre.isEmpty( ) ||strConcepto == null || strConcepto.isEmpty( ) || strRaza == null || strRaza.isEmpty( ))
            {
                JOptionPane.showMessageDialog( this, "Debe ingresar todos los datos.", "Registrar Perro", JOptionPane.ERROR_MESSAGE );
            }try {
            int numPeso = Integer.parseInt(txtPeso.getText());
            int numEdad = Integer.parseInt(txtEdad.getText());
            int numDia = Integer.parseInt(txtDiain.getText());
            int numMes = Integer.parseInt(txtMesin.getText());
            int numAnio = Integer.parseInt(txtAnioin.getText());
            principal.registrarPerro(strNombre, strRaza, numPeso, numEdad, salud, strConcepto, numDia, numMes, numAnio);
            dispose();
        }
        catch (Exception e){
            JOptionPane.showMessageDialog(this,"El valor de edad o peso ingresado no es un dato numerico");
            }
        }
    }
    private static class LetterDocumentFilter extends DocumentFilter {
        @Override
        public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException{
            for (int i = 0; i < string.length(); i++) {
                if (Character.isLetter(string.charAt(i))) {
                    super.insertString(fb, offset + i, String.valueOf(string.charAt(i)), attr);
                }
            }
        }
        @Override
        public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException{
            for (int i = 0; i < text.length(); i++) {
                if (Character.isLetter(text.charAt(i))) {
                    super.replace(fb, offset, length, String.valueOf(text.charAt(i)), attrs);
                    offset++;
                }
            }
        }
    }
    private static class NumberDocumentFilter extends DocumentFilter {
        @Override
        public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException{
            for (int i = 0; i < string.length(); i++) {
                if (Character.isDigit(string.charAt(i))) {
                    super.insertString(fb, offset + i, String.valueOf(string.charAt(i)), attr);
                }
            }
        }

        @Override
        public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException{
            for (int i = 0; i < text.length(); i++) {
                if (Character.isDigit(text.charAt(i))) {
                    super.replace(fb, offset, length, String.valueOf(text.charAt(i)), attrs);
                    offset++;
                }
            }
        }
    }
}
