package RefugioAnimal.Interfaz;

import RefugioAnimal.Metodos.Donacion;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class IDonacion extends JPanel implements ActionListener {

    private static final String REGITRAR_DONACION = "Registrar Donacion";
    private JScrollPane scrollDesplazamiento;
    private JList listaDonanciones;
    private JButton btnregistrarPerro;
    private InterfazRefugio principal;
    public IDonacion(InterfazRefugio pPrincipal ){
        principal = pPrincipal;
        setLayout( new BorderLayout( ) );
        setBorder( new TitledBorder( "Donaciones" ) );

        scrollDesplazamiento = new JScrollPane( );

        listaDonanciones = new JList( );
        listaDonanciones.setSelectionMode( javax.swing.ListSelectionModel.SINGLE_SELECTION );

        scrollDesplazamiento.setVerticalScrollBarPolicy( javax.swing.JScrollPane.VERTICAL_SCROLLBAR_ALWAYS );
        scrollDesplazamiento.setViewportView( listaDonanciones );
        add( scrollDesplazamiento, BorderLayout.CENTER );

        btnregistrarPerro = new JButton( REGITRAR_DONACION);
        btnregistrarPerro.setActionCommand( REGITRAR_DONACION );
        btnregistrarPerro.addActionListener( this );
        btnregistrarPerro.setEnabled( false );
        add( btnregistrarPerro, BorderLayout.SOUTH );
    }
    public void actionPerformed( ActionEvent pEvento )
    {
        String actionCommand = pEvento.getActionCommand( );

        if( REGITRAR_DONACION.equals( actionCommand ) )
        {
            principal.adoptarPerro( );
        }
    }
    public boolean hayDonacionSeleccionada( )
    {
        return listaDonanciones.getSelectedIndex( ) != -1;
    }
    public int darPosicionDonacionSeleccionada( )
    {
        return listaDonanciones.getSelectedIndex( );
    }

    public void cambiarDonacion( ArrayList<Donacion> pDonacions )
    {
        listaDonanciones.removeAll( );
        listaDonanciones.setListData( pDonacions.toArray( ) );
        if(!pDonacions.isEmpty( ))
        {
            listaDonanciones.setSelectedIndex( 0 );
            btnregistrarPerro.setEnabled( true );
        }
        else
        {
            btnregistrarPerro.setEnabled( false );
        }
    }
}
