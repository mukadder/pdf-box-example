import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import org.apache.pdfbox.util.PDFTextStripper;

import static org.apache.pdfbox.pdmodel.PDDocument.load;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class PdfBoxDummyTest {

    private File pdf;

    @BeforeSuite
    public void setUp() {
        ClassLoader classLoader = getClass().getClassLoader();
        pdf = new File(classLoader.getResource("example.pdf").getFile());

        assertThat(pdf.getName(), equalTo("example.pdf"));
    }

    @Test
    public void dummyTest() throws IOException {
        PDFTextStripper stripper = new PDFTextStripper();

        String content = stripper.getText(load(pdf));

        assertThat(content.contains("Pâte brisée"), is(true));
    }
}
