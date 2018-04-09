import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

/**
 * Example program to verify the setup of pdfbox libraries
 */
public final class ExtractPdfText
{

	/**
	 * Print text present in the document
	 * @throws URISyntaxException 
	 */
	public static void main( String[] args ) throws IOException, URISyntaxException
	{
		String fileName = "example.pdf"; // provide the path to pdf file
		PDDocument document = null;
        File file = new File(ExtractPdfText.class.getResource(fileName).toURI());

		try
		{
			document = PDDocument.load( file);
			PDFTextStripper stripper = new PDFTextStripper();
			String pdfText = stripper.getText(document).toString();
			System.out.println( "Text in the area:" + pdfText);
		}
		finally
		{
			if( document != null )
			{
				document.close();
			}
		}
	}
}