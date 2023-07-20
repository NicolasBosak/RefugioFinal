package RefugioAnimal.Interfaz;
import RefugioAnimal.Metodos.Usuario;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class ListaUsuarios extends JPanel implements ListSelectionListener, ActionListener{
    private final static String REGISTRAR = "Registrar Usuario";
    private JScrollPane scrollUsuario;
    private JList usuarios;
    private JButton btnRegistrar;
    private InterfazRefugio principal;

    public ListaUsuarios(InterfazRefugio pPrincipal )
    {
        principal = pPrincipal;
        setLayout( new BorderLayout( ) );
        setBorder( new TitledBorder( "Usuarios" ) );
        setPreferredSize( new Dimension( 300, 0 ) );

        usuarios = new JList( );

        scrollUsuario = new JScrollPane(usuarios);
        usuarios.setSelectionMode( ListSelectionModel.SINGLE_SELECTION );
        usuarios.addListSelectionListener( this );
        scrollUsuario.setVerticalScrollBarPolicy( javax.swing.JScrollPane.VERTICAL_SCROLLBAR_ALWAYS );
        scrollUsuario.setHorizontalScrollBarPolicy( javax.swing.JScrollPane.HORIZONTAL_SCROLLBAR_NEVER );
        add(scrollUsuario, BorderLayout.CENTER );

        btnRegistrar = new JButton( REGISTRAR );
        btnRegistrar.setActionCommand( REGISTRAR );
        btnRegistrar.addActionListener( this );
        add( btnRegistrar, BorderLayout.SOUTH );
    }
    public String darCedulaUsuarioSeleccionado( )
    {
        String cadena = "";
        if( usuarios.getSelectedValue( ) != null )
        {
            Usuario actual = (Usuario)usuarios.getSelectedValue( );
            cadena = actual.darCedula( );
        }
        return cadena;
    }
    public void refrescar( ArrayList<Usuario> pLista )
    {
        usuarios.setListData( pLista.toArray( ) );
        if(!pLista.isEmpty( ))
        {
            usuarios.setSelectedIndex( 0 );
        }
    }
    public void cambiarActual(Usuario pUsuario)
    {
        usuarios.setSelectedValue( pUsuario, true );
    }
    public void valueChanged( ListSelectionEvent pEvento )
    {
        if( usuarios.getSelectedValue( ) != null )
        {
            principal.actualizar();
        }
    }
    public void actionPerformed( ActionEvent pEvento )
    {
        String comando = pEvento.getActionCommand( );
        if( comando.equals( REGISTRAR ) )
        {
            RegistrarUsuario dialogo = new RegistrarUsuario(principal);
            dialogo.setVisible( true );
        }
    }
}
