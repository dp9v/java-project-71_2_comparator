package hexlet.code;

import picocli.CommandLine;

import java.io.File;
import java.util.concurrent.Callable;

@CommandLine.Command(name = "app", mixinStandardHelpOptions = true, version = "app.0.1")
public class App implements Callable<Integer> {

    @CommandLine.Parameters(index = "0", paramLabel = "filepath1", description = "path to first file")
    private File filepath1;
    @CommandLine.Parameters(index = "1", paramLabel = "filepath2", description = "path to second file")
    private File filepath2;

    @CommandLine.Option(
        names = {"-f", "--format"},
        paramLabel = "format",
        defaultValue = "stylish",
        description = "output format [default: stylish]"
    )
    private String format;

    @Override
    public Integer call() throws Exception {
        var formatterType = FormatterTypes.valueOf(format.toUpperCase());
        Differ.compare(filepath1, filepath2, formatterType);
        return 0;
    }

    public static void main(String[] args) {
        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);
    }
}
