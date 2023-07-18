//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import java.io.InputStream;
import java.util.Iterator;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

public class PageIterator implements Iterator<Page> {
    private XMLStreamReader reader;

    public PageIterator(InputStream stream) throws XMLStreamException {
        XMLInputFactory inFactory = XMLInputFactory.newFactory();
        this.reader = inFactory.createXMLStreamReader(stream);
    }

    public boolean hasNext() {
        try {
            while(true) {
                if (this.reader.hasNext()) {
                    int event = this.reader.next();
                    if (event != 1 || !this.reader.getLocalName().equals("title")) {
                        continue;
                    }

                    return true;
                }

                return false;
            }
        } catch (XMLStreamException var2) {
            throw new RuntimeException("error when parsing XML content : " + var2.getMessage());
        }
    }

    public Page next() {
        String tagContent = "";
        String title = "";
        String content = "";

        try {
            while(this.reader.hasNext()) {
                int event = this.reader.next();
                if (event == 4) {
                    tagContent = tagContent + this.reader.getText();
                }

                if (event == 2) {
                    if (this.reader.getLocalName().equals("title")) {
                        title = tagContent;
                    } else if (this.reader.getLocalName().equals("text")) {
                        content = tagContent;
                    } else if (this.reader.getLocalName().equals("page")) {
                        return new Page(title, content);
                    }

                    tagContent = "";
                }
            }
        } catch (XMLStreamException var5) {
        }

        throw new RuntimeException("Error when parsing XML content");
    }
}
