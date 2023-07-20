package RefugioAnimal.Interfaz;
import RefugioAnimal.Metodos.Donacion;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class ListaDonacion extends JPanel implements ListSelectionListener{
    private final static String REGISTRARDO = "Registrar Donacion";
    private JScrollPane scrollDonador;
    private JList donacions;
    private JButton btnDonar;
    private InterfazRefugio principal;
    public ListaDonacion(InterfazRefugio pPrincipal )
    {
        principal = pPrincipal;
        setLayout( new BorderLayout( ) );
        setBorder( new TitledBorder( "Donaciones" ) );
        setPreferredSize( new Dimension( 300, 0 ) );

        donacions = new JList( );

        scrollDonador = new JScrollPane(donacions);
        donacions.setSelectionMode( ListSelectionModel.SINGLE_SELECTION );
        donacions.addListSelectionListener( this );
        scrollDonador.setVerticalScrollBarPolicy( javax.swing.JScrollPane.VERTICAL_SCROLLBAR_ALWAYS );
        scrollDonador.setHorizontalScrollBarPolicy( javax.swing.JScrollPane.HORIZONTAL_SCROLLBAR_NEVER );
        add(scrollDonador, BorderLayout.CENTER );
    }
    public String darDonadorSeleccionado( )
    {
        String cadena = "";
        if( donacions.getSelectedValue( ) != null )
        {
            Donacion actual = (Donacion) donacions.getSelectedValue( );
            cadena = actual.darNombredon( );
        }
        return cadena;
    }
    public void refrescar( ArrayList<Donacion> pLista )
    {
        donacions.setListData( pLista.toArray( ) );
        if(!pLista.isEmpty( ))
        {
            donacions.setSelectedIndex( 0 );
        }
    }
    public void cambiarActual(Donacion pDonacion)
    {
        donacions.setSelectedValue( pDonacion, true );
    }
    public void valueChanged( ListSelectionEvent pEvento )
    {
        if( donacions.getSelectedValue( ) != null )
        {
            principal.actualizar();
        }
    }
}
