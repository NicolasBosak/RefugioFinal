package RefugioAnimal.Interfaz;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
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

public class RegistrarUsuario extends JDialog implements ActionListener{
    private static final String REGISTRAR = "REGISTRAR";
    private JTextField txtNombre;
    private JTextField txtCedula;
    private JTextField txtDiana;
    private JTextField txtMesna;
    private JTextField txtAniona;
    private JButton botonRegistrar;
    private InterfazRefugio principal;

    public RegistrarUsuario(InterfazRefugio pPrincipal){

        principal = pPrincipal;
        setTitle( "Registrar nuevo usuario" );
        setSize( 350, 325 );
        setLocationRelativeTo( principal );

        JPanel panelGeneral = new JPanel( );
        panelGeneral.setBorder( new CompoundBorder( new TitledBorder( "Datos usuario" ), new EmptyBorder( 3, 3, 3, 3 ) ) );
        panelGeneral.setLayout( new BorderLayout( ) );
        add( panelGeneral );

        JPanel panelDatos = new JPanel( );
        panelDatos.setLayout( new GridLayout( 8, 4, 6, 6 ) );
        panelGeneral.add( panelDatos, BorderLayout.CENTER );

        JLabel etiquetaNombre = new JLabel( "Nombre Completo:" );
        panelDatos.add( etiquetaNombre );
        txtNombre = new JTextField( );
        panelDatos.add( txtNombre );
        ((AbstractDocument) txtNombre.getDocument()).setDocumentFilter(new LetterDocumentFilter());

        JLabel etiquetaCedula = new JLabel( "Cedula:" );
        panelDatos.add( etiquetaCedula );
        txtCedula = new JTextField( );
        panelDatos.add( txtCedula );
        ((AbstractDocument) txtCedula.getDocument()).setDocumentFilter(new NumberDocumentFilter());

        JLabel etiquetaAniona = new JLabel( "Dia:" );
        panelDatos.add( etiquetaAniona );
        txtAniona = new JTextField( );
        panelDatos.add(txtAniona);
        ((AbstractDocument)txtAniona.getDocument()).setDocumentFilter(new NumberDocumentFilter());

        JLabel etiquetaMesna = new JLabel( "Mes:" );
        panelDatos.add( etiquetaMesna );
        txtMesna = new JTextField( );
        panelDatos.add( txtMesna );
        ((AbstractDocument) txtMesna.getDocument()).setDocumentFilter(new NumberDocumentFilter());

        JLabel etiquetaDiana = new JLabel( "Anio:" );
        panelDatos.add( etiquetaDiana );
        txtDiana = new JTextField( );
        panelDatos.add( txtDiana );
        ((AbstractDocument) txtDiana.getDocument()).setDocumentFilter(new NumberDocumentFilter());

        botonRegistrar = new JButton( "Registrar" );
        botonRegistrar.setActionCommand( REGISTRAR );
        botonRegistrar.addActionListener( this );
        panelGeneral.add( botonRegistrar, BorderLayout.SOUTH );
    }

    public void actionPerformed(ActionEvent pEvento){
        String actionCommand = pEvento.getActionCommand( );

        if( REGISTRAR.equals( actionCommand ) )
        {
            String strNombre = txtNombre.getText( );
            String strCedula = txtCedula.getText( );
            if((strNombre == null) || strNombre.isEmpty() || (strCedula == null) || strCedula.isEmpty())
            {
                JOptionPane.showMessageDialog( this, "Debe ingresar todos los datos.", "Registrar Usuario", JOptionPane.ERROR_MESSAGE );
            }
            else {
                int numDiana = Integer.parseInt(txtDiana.getText());
                int numMesna = Integer.parseInt(txtMesna.getText());
                int numAniona = Integer.parseInt(txtAniona.getText());
                principal.registrarUsuario(strNombre, strCedula, numDiana, numMesna, numAniona);
                dispose();
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
