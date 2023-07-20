package RefugioAnimal.Interfaz;
import RefugioAnimal.Metodos.Animal;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.util.ArrayList;

public class ListaPerros extends JPanel implements ListSelectionListener{
    private final static String ADOPTAR = "Adoptar Perro";
    private JScrollPane scrollANIMALES;
    private JList perros;
    private InterfazRefugio principal;
    public ListaPerros(InterfazRefugio pPrincipal ){
        principal = pPrincipal;
        setLayout( new BorderLayout( ) );
        setBorder( new TitledBorder( "Perros" ) );
        setPreferredSize( new Dimension( 300, 0 ) );

        perros = new JList( );

        scrollANIMALES = new JScrollPane(perros);
        perros.setSelectionMode( ListSelectionModel.SINGLE_SELECTION );
        perros.addListSelectionListener( this );
        scrollANIMALES.setVerticalScrollBarPolicy( javax.swing.JScrollPane.VERTICAL_SCROLLBAR_ALWAYS );
        scrollANIMALES.setHorizontalScrollBarPolicy( javax.swing.JScrollPane.HORIZONTAL_SCROLLBAR_NEVER );
        add(scrollANIMALES, BorderLayout.CENTER );
    }
    public String darCNombrePerroSeleccionado( )
    {
        String cadena = "";
        if( perros.getSelectedValue( ) != null )
        {
            Animal actual = (Animal)perros.getSelectedValue( );
            cadena = actual.darNombreani( );
        }
        return cadena;
    }
    public void refrescar( ArrayList<Animal> pLista )
    {
        perros.setListData( pLista.toArray( ) );
        if(!pLista.isEmpty( ))
        {
            perros.setSelectedIndex( 0 );
        }
    }
    public void cambiarActual(Animal pAnimal)
    {
        perros.setSelectedValue( pAnimal, true );
    }
    public void valueChanged( ListSelectionEvent pEvento )
    {
        if( perros.getSelectedValue( ) != null )
        {
            principal.actualizar();
        }
    }
}
