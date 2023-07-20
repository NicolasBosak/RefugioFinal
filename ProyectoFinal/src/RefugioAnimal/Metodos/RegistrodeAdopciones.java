package RefugioAnimal.Metodos;
import java.util.ArrayList;
public class RegistrodeAdopciones{
    private ArrayList<Usuario> usuarios;
    private ArrayList<Animal> animalesDejados;

    public void RegistroAdopciones() {
        usuarios = new ArrayList<>();
        animalesDejados = new ArrayList<>();
    }
    public void agregarUsuario(Usuario usuario) {
        usuarios.add(usuario);
    }
    public void agregarAnimalDejado(Animal animal) {
        animalesDejados.add(animal);
    }
    public void mostrarAdopcionesRealizadas() {
        System.out.println("---- Adopciones Realizadas ----");
        if (usuarios.isEmpty()) {
            System.out.println("No hay adopciones registradas.");
        } else {
            for (Usuario usuario : usuarios) {
                System.out.println("Usuario: " + usuario.darNombre());
                ArrayList<Animal> perrosAdoptados = usuario.darAnimals();
                if (perrosAdoptados.isEmpty()) {
                    System.out.println("- No ha realizado adopciones -");
                } else {
                    for (Animal perro : perrosAdoptados) {
                        System.out.println("- " + perro.darNombreani());
                    }
                }
                System.out.println("------------------------");
            }
        }
        System.out.println();
    }
    public void mostrarAnimalesDejadosEnRefugio() {

        if (animalesDejados.isEmpty()) {
        } else {
            for (Animal animal : animalesDejados) {
                System.out.println("- " + animal.darNombreani());
            }
        }
        System.out.println();
    }
}
