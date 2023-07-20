package RefugioAnimal.Metodos;
import java.time.LocalDate;
import java.time.Period;
import RefugioAnimal.Metodos.Animal.Tipo;

import java.util.ArrayList;
public class Usuario {
    private String nombre;
    private String cedula;
    private int diana;
    private int mesna;
    private int aniona;
    private int calcedad;
    private ArrayList<Animal> animals;
    private ArrayList<Donacion> donacions;
    private ArrayList<Usuario> usuarios;

    public Usuario(String pNombre, String pCedula, int pDiana, int pMesna, int pAniona) throws Exception {
        if(!validarCedula(pCedula)){
            throw new IllegalArgumentException("La cedula no es valida");
        }
        cedula = pCedula;
        nombre = pNombre;
        diana = pDiana;
        mesna = pMesna;
        aniona = pAniona;

        LocalDate fechaNacimiento = LocalDate.of(diana, mesna, aniona);
        LocalDate fechaActual = LocalDate.now();
        Period periodo = Period.between(fechaNacimiento, fechaActual);
        calcedad = periodo.getYears();
        if (fechaNacimiento.isAfter(fechaActual)){
            throw new Exception("El usuario aun no existe en el mundo");
        }
        if (calcedad < 18){
            throw new Exception("El usuario no es mayor de edad");
        }
        animals = new ArrayList<Animal>();
        donacions = new ArrayList<Donacion>();
    }

    public Usuario() throws Exception {
        usuarios=new ArrayList<Usuario>();
        animals=new ArrayList<Animal>();
        donacions=new ArrayList<Donacion>();
    }

    public String darNombre( )
    {
        return nombre;
    }
    public String darCedula( )
    {
        return cedula;
    }
    public int darCalcEdad(){return calcedad;}
    public ArrayList<Usuario> darUsuarios(){return usuarios;}
    public ArrayList<Animal> darAnimals(){return animals;}
    public ArrayList<Donacion> darDonacions(){return donacions;}

    public boolean validarCedula (String cedula)throws Exception{
        if (cedula.length() != 10 || cedula.length() > 11){
            throw new Exception("La cedula no tiene los digitos correspondientes");
        }
        int provincia= Integer.parseInt(cedula.substring(0,2));
        if (provincia<1 || provincia >24){
            throw new Exception("La cedula no conrresponde a ninguna provincia intente nuevamente");
        }
        int tercerDigito = Integer.parseInt(cedula.substring(2, 3));
        if (tercerDigito < 0 || tercerDigito > 5) {
            return false;
        }
        int verificador = Integer.parseInt(cedula.substring(9));
        int suma = 0;
        for (int i = 0; i < 9; i++) {
            int digito = Character.getNumericValue(cedula.charAt(i));
            if (i % 2 == 0) {
                digito *= 2;
                if (digito > 9) {
                    digito -= 9;
                }
            }
            suma += digito;
        }
        int residuo = suma % 10;
        int digitoVerificadorCalculado = residuo == 0 ? 0 : 10 - residuo;
        if (verificador != digitoVerificadorCalculado) {
            return false;
        }
        return true;
    }

    public void registrarDonacion( String pNombre, int pValor ) throws Exception{
        int almacen=7000;
        if( pValor >= almacen )
        {
            throw new Exception( "Ya no se pueden registrar mas donaciones." );
        }
        else
        {
            Donacion nuevaDonacion = new Donacion( pNombre, pValor );
            donacions.add( nuevaDonacion );
        }
    }
    public void registrarPerro(String pNombreani, String pRazaani, int pPesoani, int pEdadani, Tipo pTipo, String pConcepto, int pDiain, int pMesin, int pAnioin) throws Exception
    {
        int cantidadRegistros = animals.size();
        if(cantidadRegistros==20){
            throw new Exception( "Ya no se pueden registrar mas perros." );
        }
        else{
            Animal nuevoAnimal = new Animal(pNombreani, pRazaani, pPesoani, pEdadani, pTipo, pConcepto, pDiain, pMesin, pAnioin);
            animals.add( nuevoAnimal );
        }
    }
    public void adoptarPerro( int pEstadoSalud ) throws Exception{
        Animal animal = animals.get( pEstadoSalud );
        if(animal.darAdopcion( ) == "ENFERMO"){
            throw new Exception( "No se puede adoptar un perro de estado enfermo." );
        }
        else{
            animals.remove( pEstadoSalud );
        }
    }
    public String toString( )
    {
        String usuario = nombre+" / "+cedula+" / "+calcedad;
        return usuario;
    }
    public boolean verificarCedula (String cedula){
        return this.cedula.equals(cedula);
    }

    public Usuario buscarUsuario(String pCedulaUsuario){
        Usuario elUsuario = null;

        boolean encontre = false;
        int numUsuarios = usuarios.size( );
        for( int i = 0; i < numUsuarios && !encontre; i++ )
        {
            Usuario u = usuarios.get( i );
            if( u.darCedula( ).equals( pCedulaUsuario ) )
            {
                elUsuario = u;
                encontre = true;
            }
        }
        return elUsuario;
    }
    public Animal buscarPerro(String pNombrePerro){
        Animal elAnimal = null;
        boolean encontre = false;
        int numPerros = animals.size( );
        for(int i = 0; i < numPerros && !encontre; i++){
            Animal a = animals.get( i );
            if(a.darNombreani().equals(pNombrePerro)){
                elAnimal = a;
                encontre = true;
            }
        }
        return elAnimal;
    }
    public void registrarUsuario(String pNombre, String pCedula, int pDiana, int pMesna, int pAniona) throws Exception{
        Usuario u = buscarUsuario( pCedula );
        if( u == null ){
            Usuario nuevoUsuario = new Usuario(pNombre, pCedula, pDiana, pMesna, pAniona);
            usuarios.add( nuevoUsuario );
        }
        else{
            throw new Exception( "El usuario ya existe." );
        }
    }
    public void registrarDonacion( String pCedulaUsuario, String pNombreUsuario, int pValor ) throws Exception
    {
            Usuario u = buscarUsuario( pCedulaUsuario );
            u.registrarDonacion( pNombreUsuario, pValor );
            Donacion nuevaDonacion = new Donacion(pNombreUsuario, pValor);
            donacions.add(nuevaDonacion);
    }
    public void registrarPerro(String pCedulaUsuario, String pNombreani, String pRazaani, int pPesoani, int pEdadani, Tipo pTipo, String pConcepto, int pDiain, int pMesin, int pAnioin)throws Exception
    {
        Usuario u = buscarUsuario( pCedulaUsuario );
        u.registrarPerro(pNombreani, pRazaani, pPesoani, pEdadani, pTipo, pConcepto, pDiain, pMesin, pAnioin);
        Animal nuevoAnimal = new Animal(pNombreani,pRazaani,pPesoani,pEdadani,pTipo,pConcepto, pDiain, pMesin, pAnioin);
        animals.add(nuevoAnimal);
    }
    public void adoptarPerroUsuario( String pNombreani, int pEstadoSalud ) throws Exception
    {
        Usuario u = buscarUsuario( pNombreani );
        u.adoptarPerro( pEstadoSalud );
    }
    public ArrayList<Animal> darPerrosUsuario(String pCedulaUsuario )
    {
        return buscarUsuario( pCedulaUsuario ).darAnimals();
    }
    public ArrayList<Donacion> darDonacionUsuario(String pCedulaUsuario )
    {
        return buscarUsuario( pCedulaUsuario ).darDonacions();
    }

}
