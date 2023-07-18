import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {
    public static int npages = 0;

    public static void main(String[] args) throws IOException {

        Path wiki_path = Paths.get("wiki.bin");
        if(Files.exists(wiki_path)){
            OutputRepeat o_r = new OutputRepeat();
            o_r.output_repeat(args);
        }else{
            FirstEverUse f_e = new FirstEverUse();
            f_e.output_first(args);
            //System.out.println(npages);
        }

    }
}

