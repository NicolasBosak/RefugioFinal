package RefugioAnimal.Interfaz;
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

public class RegistrarDonacion extends JDialog implements ActionListener{
    private static final String REGISTRAR_DONACION = "Registrar Donacion";
    private JComboBox cmbUsuarios;
    private JTextField txtNombre;
    private JTextField txtAlimentokg;
    private JButton btnRegistrarDonacion;
    private InterfazRefugio principal;

    public RegistrarDonacion(InterfazRefugio pPrincipal, ArrayList pUsuarios){
        principal = pPrincipal;
        setTitle( "Registrar Donacion" );
        setSize( 350, 250 );
        setLocationRelativeTo( principal );

        JPanel panelGeneral = new JPanel( );
        panelGeneral.setBorder( new CompoundBorder( new TitledBorder( "Datos Donacion" ), new EmptyBorder( 3, 3, 3, 3 ) ) );
        panelGeneral.setLayout( new BorderLayout( ) );
        add( panelGeneral );

        JPanel panelDatos = new JPanel( );
        panelDatos.setLayout( new GridLayout( 4, 2, 3, 3 ) );
        panelGeneral.add( panelDatos, BorderLayout.CENTER );

        JLabel lblNombre = new JLabel( "Alimento:" );
        panelDatos.add( lblNombre );
        txtNombre = new JTextField( );
        panelDatos.add(txtNombre);
        ((AbstractDocument) txtNombre.getDocument()).setDocumentFilter(new LetterDocumentFilter());

        JLabel lblValor = new JLabel( "Cantidad en Kg" );
        panelDatos.add( lblValor );
        txtAlimentokg = new JTextField( );
        panelDatos.add(txtAlimentokg);
        ((AbstractDocument) txtAlimentokg.getDocument()).setDocumentFilter(new NumberDocumentFilter());
        panelDatos.add( new JLabel( ) );
        panelDatos.add( new JLabel( ) );

        btnRegistrarDonacion = new JButton( "Registrar" );
        btnRegistrarDonacion.setActionCommand( REGISTRAR_DONACION );
        btnRegistrarDonacion.addActionListener( this );
        panelGeneral.add( btnRegistrarDonacion, BorderLayout.SOUTH );
    }
    public void actionPerformed( ActionEvent pEvento )
    {
        String actionCommand = pEvento.getActionCommand( );
        if( REGISTRAR_DONACION.equals( actionCommand ) )
        {
            String strValor = txtAlimentokg.getText( );
            String strNombre = txtNombre.getText();
            int valor = 0;
            if(strNombre == null || strNombre.isEmpty( ))
            {
                JOptionPane.showMessageDialog( this, "Debe ingresar todos los datos.", "Registrar consumo", JOptionPane.ERROR_MESSAGE );
            }
            else
            {
                try
                {
                    valor = Integer.parseInt(strValor);
                    if( valor > 0 )
                    {
                        principal.registrarDonacion( strNombre, valor );
                        dispose( );
                    }
                    else
                    {
                        JOptionPane.showMessageDialog( this, "El valor a registrar debe ser mayor a cero.", "Registrar Donacion", JOptionPane.ERROR_MESSAGE );

                    }
                }
                catch( Exception e )
                {
                    JOptionPane.showMessageDialog( this, "El valor a registrar debe ser un valor numerico.", "Registrar Donacion", JOptionPane.ERROR_MESSAGE );
                }
            }
        }
    }
    private static class LetterDocumentFilter extends DocumentFilter {
        @Override
        public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
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
