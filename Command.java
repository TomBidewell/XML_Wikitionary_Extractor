import org.apache.commons.cli.*;

import java.util.ArrayList;

public class Command {

    public ArrayList<String> getCommands(String[] args){
        Options options = new Options();
        options.addOption("f", "file", true, "XML dump file");
        options.addOption("for", "word", true, "Word we're looking for");
        options.addOption("what", "what", true, "What we're looking for");
        options.addOption("into", "into", true, "Language to translate into");

        CommandLineParser parser = new DefaultParser();
        ArrayList<String> coms = new ArrayList<>();
        try {

            CommandLine cmd = parser.parse(options, args);

            String filename = cmd.getOptionValue("file");
            String word = cmd.getOptionValue("word");
            String what = cmd.getOptionValue("what");
            String into = cmd.getOptionValue("into");

            if (filename == null) {
                throw new IllegalArgumentException("Missing XML dump file argument");
            }
            if (word == null) {
                throw new IllegalArgumentException("Missing word argument");
            }
            if (what == null) {
                throw new IllegalArgumentException("Missing what argument");
            }
            coms.add(filename);
            coms.add(word);
            coms.add(what);
            if (into != null){
                coms.add(into);
            }else{
                coms.add("");
            }
            return coms;

        } catch (ParseException e) {
            coms.add("Error parsing command line arguments: " + e.getMessage());
        }catch (IllegalArgumentException e) {
            coms.add("Error: " + e.getMessage());
            HelpFormatter formatter = new HelpFormatter();
            formatter.printHelp("PageExtractor", options);
        }
        return coms;

    }

}
