package RefugioAnimal.Metodos;

public class Donacion{
    private String nombredon;
    private int valor;

    public Donacion( String pNombredon, int pValor )
    {
        nombredon = pNombredon;
        valor = pValor;
    }
    public String darNombredon(){return nombredon;}
    public String toString( )
    {
        String donacion= nombredon+" / "+valor+" Kg";
        return donacion;
    }
}
