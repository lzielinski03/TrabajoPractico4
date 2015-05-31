package view;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by leonardo on 03/05/2015.
 */
public class IOMenu extends IOManager {

    public String getTeamPath() {
        println();
        println("Copie el archivo de equipos en la siguiente ruta: " + System.getProperty("user.dir") + "\\files\\");
        println("No omita la extencion del archivo ejemplo: \"equipos.txt\"");
        println("El archivo debe tener un equipo por linea y contener minimo tres(3) equipos.");
        String path;
        do {
            path = getString("Ingresar nombre del archivo : ");
        } while (path.trim().isEmpty());
        return path;
    }

    public int getDaysBetweenGame() {
        return getInt("Ingresar la cantidad de dias entre cada partido.");
    }


/*
    public void println(String message) {
        super.println(message);
    }*/

    public enum OptionMenu {
        HELP(0, "/help", "Menu de ayuda"),
        ADD(1, "/add", "Permite ingresar una nueva entrada en el diario."),
        DELETE(2, "/delete", "Permite borrar la línea con el identificador #n siendo n un entero."),
        ENTRIES(3, "/entries", "Muestra todas las entradas del diario."),
        EXIT(4, "/exit", "Salir.");

        private int value;
        private String content;
        private List<String> args;
        private String description;

        private OptionMenu(int value, String content, String description) {
            this.value = value;
            this.content = content;
            this.description = description;
            this.args = new ArrayList<String>();
        }

        public List<String> getArgs() {
            return args;
        }

        @Override
        public String toString() {
            return this.content;
        }
    }







    // test broke | throw?
    public OptionMenu getOptionMenu() {
        println();
        String[] inputText = getString("Ingresar comando: ").split("\\s+");
        for (OptionMenu x : OptionMenu.values()) {
            if (x.content.equalsIgnoreCase(inputText[0].toLowerCase())) {
                return handleArguments(x, inputText);
            }
        }
        println();
        return OptionMenu.HELP;
    }

    // throw args check | void
    public OptionMenu handleArguments(OptionMenu optionMenu, String[] args) {
        switch (optionMenu) {
            case ADD:
                optionMenu.args.clear();
                optionMenu.args.add(getNewEntry());
                break;
            case DELETE:
                if (args.length > 1) {
                    if (args[1].matches("\\#[0-9]+")) {
                        optionMenu.args.clear();
                        optionMenu.args.add(args[1].substring(1, args[1].length()));
                    } else {
                        println("Argumento invalido.");
                        return OptionMenu.HELP;
                    }
                } else if (args[0].matches("\\/delete")) {
                    println("Falta argumento.");
                    return OptionMenu.HELP;
                }
                break;
        }
        return optionMenu;
    }

    // throw emptyException
    public String getNewEntry() {
        return getString("Ingresar entrada: ");
    }

    // throw empty exception
    public void printDiary(List<String> diary) {
        println();
        for (String line : diary) {
            println(line);
        }
    }

    public void printHelp() {
        println("Menu de ayuda");
        for (OptionMenu x : OptionMenu.values()) {
            println(x.value + " | " + x.content + "\t\t\t" + x.description);
        }
        println();
    }

    public void showMenu() {
        println("Inscripcion");
        println("");
        println("Inscripcion");
        println("Inscripcion");
    }
}