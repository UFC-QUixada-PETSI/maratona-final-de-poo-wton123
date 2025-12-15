import java.util.Scanner;
import java.util.ArrayList;

// ==================================================================================
// PASSO 1: CLASSE MÃE (MIDIA)
// ==================================================================================
class Midia {
    // TODO: Atributos privados (titulo, duracao, tocando)
    protected String titulo; // Use protected ou private com getters se preferir
    protected boolean tocando;

    public Midia(String titulo) {
        // TODO: Inicialize titulo e tocando = false
        this.titulo = titulo;
        this.tocando = false;
    }

    public String tocar() {
        // TODO: 
        // Se já estiver tocando, retorne "[titulo] ja esta reproduzindo."
        if (tocando) {
            return this.titulo;
        } else {
            this.tocando = true;
            return ("Reproduzindo midia:" +  this.titulo);
        }
        // Se não, mude tocando para true e retorne "Reproduzindo midia: [titulo]"
    }
}

// ==================================================================================
// PASSO 2: SUBCLASSES (FILME E MUSICA)
// ==================================================================================

class Filme extends Midia {
    // Atributo extra: diretor
    String diretor;
    // TODO: Construtor (chame super)
    public Filme (String titulo, String diretor) {
        super(titulo);
        this.diretor = diretor;
    }
    // TODO: Sobrescreva o método tocar().
    @Override
    public String tocar() {
    // Mude tocando = true.
    this.tocando = true;
    // Retorne "Exibindo filme: [titulo] do diretor [diretor]"
    return ("Exibindo filme: " + this.titulo + " do diretor " + this.diretor);
    }
}

class Musica extends Midia {
    // Atributo extra: artista
    String artista;
    // TODO: Construtor (chame super)
    public Musica (String titulo, String artista) {
        super(titulo);
        this.artista = artista;
    }
    // TODO: Sobrescreva o método tocar().
      @Override
    public String tocar() {
    this.tocando = true;
    return ("Tocando musica: " + this.titulo +  " - " + this.artista);
    }
    // Mude tocando = true.
    // Retorne "Tocando musica: [titulo] - [artista]"
}


// ==================================================================================
// PASSO 3: GERENCIADOR (PLATAFORMA)
// ==================================================================================
class Plataforma {
    private ArrayList<Midia> playlist = new ArrayList<>();

    public void adicionar(Midia m) {
        playlist.add(m);
    }

    public void reproduzirTudo() {
        System.out.println("--- PLAYLIST ---");
        // TODO: Percorra a lista e chame o método tocar() de cada elemento.
        int tamanho = playlist.size();
        for (Midia b:playlist) {
            System.out.println(playlist.tocar());
        // Imprima o resultado de cada chamada.
        }
    }
}

// ==================================================================================
// MAIN
// ==================================================================================
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Plataforma cineDev = new Plataforma();
        // Para testes unitários manuais do passo 1
        Midia midiaTemp = null; 

        while (scanner.hasNextLine()) {
            String linha = scanner.nextLine().trim();
            if (linha.isEmpty()) continue;
            System.out.println("$" + linha);
            String[] tokens = linha.split(" ");
            String cmd = tokens[0].toLowerCase();

            try {
                switch (cmd) {
                    case "end": scanner.close(); return;
                    case "init": cineDev = new Plataforma(); break;

                    case "midia": // PASSO 1
                        midiaTemp = new Midia(tokens[1]);
                        cineDev.adicionar (midiaTemp);
                        break;
                    
                    case "tocar_midia": // PASSO 1
                        if (midiaTemp != null) System.out.println(midiaTemp.tocar());
                        break;

                    case "filme": // PASSO 2
                        // Ex: filme Matrix Wachowski
                        // TODO: Descomente
                        cineDev.adicionar(new Filme(tokens[1], tokens[2]));
                        break;

                    case "musica": // PASSO 2
                        // Ex: musica HeyJude Beatles
                        // TODO: Descomente
                        cineDev.adicionar(new Musica(tokens[1], tokens[2]));
                        break;

                    case "playlist": // PASSO 3
                        cineDev.reproduzirTudo();
                        break;
                }
            } catch (Exception e) { System.out.println("Erro: " + e.getMessage()); }
        }
    }
}