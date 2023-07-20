package RefugioAnimal.Metodos;

import java.time.LocalDate;
import java.time.Period;

public class Animal{
    private String nombreani;
    private String razaani;
    private int pesoani;
    private int edadani;
    private String concepto;
    private String adopcion;
    private Tipo salud;
    private int diain;
    private int mesin;
    private int anioin;
    public enum Tipo{SANO,ENFERMO}
    public final static String ADOPTABLE="SANO";
    public final static String NOADOPTABLE="ENFERMO";

    public Animal(String pNombreani, String pRazaani, int pPesoani, int pEdadani, Tipo pTipo, String pConcepto, int pDiain, int pMesin, int pAnioin) throws Exception {
        nombreani=pNombreani;
        razaani=pRazaani;
        pesoani=pPesoani;
        edadani=pEdadani;
        concepto=pConcepto;
        salud=pTipo;
        switch( salud )
        {
            case SANO:
                adopcion = ADOPTABLE;
                break;
            default:
                adopcion = NOADOPTABLE;
        }
        diain=pDiain;
        mesin=pMesin;
        anioin=pAnioin;

        validarFechaIngreso();
    }
    public String darAdopcion(){return adopcion;}
    public String darNombreani(){return nombreani;}
    public String darRazaani(){return razaani;}
    public int darPesoani(){return pesoani;}
    public int darEdadani(){return edadani;}
    public Tipo darSalud(){return salud;}
    public String darConcepto(){return concepto;}
    public int darDiain(){return diain;}
    public int darMesin(){return mesin;}
    public int darAnioin(){return anioin;}

    public String toString( )
    {
        String animal = nombreani+" / "+razaani+" / "+pesoani+" anios / "+edadani+" Kg / "+salud+" / "+concepto+" / "+diain+"-"+mesin+"-"+anioin;
        return animal;
    }
    public void validarFechaIngreso () throws Exception {
        if (diain < 1 || diain > 31 || mesin < 1 || mesin > 12 || anioin < 0) {
            throw new Exception("La fecha de ingreso es inválida");
        }

        try {
            LocalDate fechaIngreso = LocalDate.of(anioin, mesin, diain);
            LocalDate fechaHoy = LocalDate.now();

            if (fechaIngreso.isAfter(fechaHoy)) {
                throw new Exception("La fecha de ingreso no puede ser posterior al año actual");
            }
        } catch (Exception e) {
            throw new Exception("La fecha de ingreso es invalida");
        }
    }
}
