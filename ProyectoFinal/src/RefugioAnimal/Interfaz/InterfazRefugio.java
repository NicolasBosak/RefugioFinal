package RefugioAnimal.Interfaz;
import RefugioAnimal.Metodos.Animal;
import RefugioAnimal.Metodos.Animal.Tipo;
import RefugioAnimal.Metodos.Donacion;
import RefugioAnimal.Metodos.Usuario;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;

public class InterfazRefugio extends JFrame{
    private IUsuarios iUsuarios;
    private IPerros iPerro;
    private ListaUsuarios listaUsuarios;
    private ListaPerros listaPerros;
    private ListaDonacion listaDonacion;
    private Usuario usuarios;
    private IRegistroAdopcion registroAdopcion;

    public InterfazRefugio() throws Exception {
        setTitle("Refugio");
        setSize(1270, 650);
        setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);

        usuarios = new Usuario();

        setLayout(new BorderLayout());

        JPanel panelCentro = new JPanel();
        panelCentro.setLayout(new BorderLayout());
        add(panelCentro, BorderLayout.CENTER);

        iUsuarios = new IUsuarios(this);
        panelCentro.add(iUsuarios, BorderLayout.WEST);

        JPanel panelUsuariosDonacion = new JPanel();
        panelUsuariosDonacion.setLayout(new GridLayout(2, 1));
        panelCentro.add(panelUsuariosDonacion, BorderLayout.CENTER);

        listaUsuarios = new ListaUsuarios(this);
        panelUsuariosDonacion.add(listaUsuarios);

        listaDonacion = new ListaDonacion(this);
        panelUsuariosDonacion.add(listaDonacion);

        JPanel panelPerros = new JPanel();
        panelPerros.setLayout(new BorderLayout());
        panelCentro.add(panelPerros, BorderLayout.EAST);

        iPerro = new IPerros(this);
        panelPerros.add(iPerro, BorderLayout.WEST);

        listaPerros = new ListaPerros(this);
        panelPerros.add(listaPerros, BorderLayout.CENTER);

        setLocationRelativeTo(null);
        setResizable(false);
    }

    public void registrarUsuario(String pNombre, String pCedula, int pDiana, int pMesna, int pAniona)
    {
        try
        {
            usuarios.registrarUsuario(pNombre, pCedula, pDiana, pMesna, pAniona);
            listaUsuarios.refrescar( usuarios.darUsuarios( ) );

            actualizar( );
            JOptionPane.showMessageDialog( this, "El usuario ha sido ingresado", "Registrar Usuario", JOptionPane.INFORMATION_MESSAGE );
        }
        catch( Exception e )
        {
            e.printStackTrace( );
            JOptionPane.showMessageDialog( this, e.getMessage( ), "Registrar Usuario", JOptionPane.ERROR_MESSAGE );
        }
    }
    public void registrarPerro(String pNombreani, String pRazaani, int pPesoani, int pEdadani, Tipo pTipo, String pConcepto, int pDiain, int pMesin, int pAnioin)
    {
        try
        {
            String cedula = listaUsuarios.darCedulaUsuarioSeleccionado( );
            usuarios.registrarPerro( cedula, pNombreani, pRazaani, pEdadani, pPesoani, pTipo, pConcepto, pDiain, pMesin, pAnioin);
            listaPerros.refrescar(usuarios.darAnimals());
            actualizar( );
            JOptionPane.showMessageDialog( this, "Perro Registrado", "Registrar Perro", JOptionPane.INFORMATION_MESSAGE );

        }
        catch( Exception e )
        {
            JOptionPane.showMessageDialog( this, e.getMessage( ), "Registrar Perro", JOptionPane.ERROR_MESSAGE );
        }
    }
    public void adoptarPerro( )
    {
        try
        {
            String cedula = listaPerros.darCNombrePerroSeleccionado( );
            int estadoSalud = iPerro.darPosicionPerroSeleccionado( );

            usuarios.adoptarPerroUsuario( cedula, estadoSalud );
            actualizar( );
        }
        catch( Exception e )
        {
            JOptionPane.showMessageDialog( this, e.getMessage( ), "Error", JOptionPane.ERROR_MESSAGE );
        }
    }
    public void registrarDonacion(String pNombredon, int pValor)
    {
        try
        {
            String cedula = listaUsuarios.darCedulaUsuarioSeleccionado( );
            usuarios.registrarDonacion( cedula, pNombredon, pValor);
            listaDonacion.refrescar(usuarios.darDonacions());
            actualizar( );
            JOptionPane.showMessageDialog( this, "Donacion Registrada", "Registrar Donacion", JOptionPane.INFORMATION_MESSAGE );

        }
        catch( Exception e )
        {
            JOptionPane.showMessageDialog( this, e.getMessage( ), "Registrar Donacion", JOptionPane.ERROR_MESSAGE );
        }
    }
    public ArrayList<Animal> darPerrosUsuario( )
    {
        return usuarios.darPerrosUsuario(listaUsuarios.darCedulaUsuarioSeleccionado( ));
    }
    public ArrayList<Donacion> darDonacionUsuario( )
    {
        return usuarios.darDonacionUsuario(listaUsuarios.darCedulaUsuarioSeleccionado( ));
    }
    public void actualizar( )
    {
        String cedula = listaUsuarios.darCedulaUsuarioSeleccionado( );
        iUsuarios.actualizar( usuarios.buscarUsuario(cedula));
        String nombre = listaPerros.darCNombrePerroSeleccionado( );
        iPerro.actualizar(usuarios.buscarPerro(nombre));
    }


    public static void main( String[] pArgs )
    {
        try
        {
            UIManager.setLookAndFeel( UIManager.getCrossPlatformLookAndFeelClassName( ) );

            InterfazRefugio interfaz = new InterfazRefugio( );
            interfaz.setVisible( true );
        }
        catch( Exception e )
        {
            e.printStackTrace( );
        }
    }

}
