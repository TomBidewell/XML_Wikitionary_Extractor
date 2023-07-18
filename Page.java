import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;


public class Page {
    public String title;
    public String content;

    public Page(String title, String content) {
        this.title = title;
        this.content = content;
    }

     public void toXML(String outputFilename) throws ParserConfigurationException, TransformerException {
        DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
        Document document = documentBuilder.newDocument();
        Element root = document.createElement("mediawiki");
        document.appendChild(root);
        Element page = document.createElement("page");
        Element title = document.createElement("title");
        title.appendChild(document.createTextNode(this.title));
        Element text = document.createElement("text");
        text.appendChild(document.createTextNode(this.content));
        root.appendChild(page);
        page.appendChild(title);
        page.appendChild(text);
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource domSource = new DOMSource(document);
        StreamResult result = new StreamResult(new File(outputFilename));
        transformer.transform(domSource, result);
    }
}
