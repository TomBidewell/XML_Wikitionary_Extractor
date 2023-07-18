//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//


import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import javax.xml.stream.XMLStreamException;
import org.apache.commons.compress.compressors.bzip2.BZip2CompressorInputStream;

public class PageExtractor implements Iterable<Page> {
    private InputStream in;
    private PageIterator it;

    public PageExtractor(String filename) throws IOException, XMLStreamException {
        if (filename.endsWith("bz2")) {
            this.in = new BZip2CompressorInputStream(new FileInputStream(filename));
        } else {
            this.in = new FileInputStream(filename);
        }

        this.it = new PageIterator(this.in);
    }

    public Iterator<Page> iterator() {
        return this.it;
    }
}
