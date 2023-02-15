package hexlet.code;

import picocli.CommandLine;

import java.util.concurrent.Callable;

@CommandLine.Command(name = "app", mixinStandardHelpOptions = true, version = "app.0.1")
public final class App implements Callable<Integer> {

    @CommandLine.Parameters(index = "0", paramLabel = "filepath1", description = "path to first file")
    private String filepath1;

    @CommandLine.Parameters(index = "1", paramLabel = "filepath2", description = "path to second file")
    private String filepath2;

    @CommandLine.Option(
        names = {"-f", "--format"},
        paramLabel = "format",
        defaultValue = "stylish",
        description = "output format [default: stylish]"
    )
    private String format;

    @Override
    public Integer call() throws Exception {
        System.out.println(
            Differ.generate(filepath1, filepath2, format)
        );
        return 0;
    }

    public static void main(String[] args) {
        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);
    }
}
