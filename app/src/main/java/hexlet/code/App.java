package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Command;

@Command(
        name = "app",
        mixinStandardHelpOptions = true,
        version = "app 1.0",
        description = "Пример консольного приложения с флагами -h и -V"
)
public class App implements Runnable {

    @Override
    public void run() {
        System.out.println("Привет! Используй -h для справки или -V для версии.");
    }

    public static void main(String[] args) {
        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);
        System.out.println("Hello World!");
    }
}
