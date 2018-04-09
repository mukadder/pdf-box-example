import java.io.File;
import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

public class AddMultipleLines {
   public static void main(String args[]) throws IOException {
	 //Loading an existing document
	      File file = new File("my_doc.pdf");
	      PDDocument document = PDDocument.load(file);
	       
	      //Retrieving the pages of the document 
	      PDPage page = document.getPage(1);
	      PDPageContentStream contentStream = new PDPageContentStream(document, page);
	      
	      //Begin the Content stream 
	      contentStream.beginText(); 
	       
	      //Setting the font to the Content stream  
	      contentStream.setFont(PDType1Font.TIMES_ROMAN, 12);

	      //Setting the position for the line 
	      contentStream.newLineAtOffset(0, 0);

      //Setting the leading
      contentStream.setLeading(14.5f);

      //Setting the position for the line
      contentStream.newLineAtOffset(25, 725);

      String text1 = "This is an example of adding text to a page in the pdf document. we can add as many lines";
      String text2 = "as we want like this using the ShowText()  method of the ContentStream class";

      //Adding text in the form of string
      contentStream. showText(text1);
      contentStream.newLine();
      contentStream. showText(text2);
      //Ending the content stream
      contentStream.endText();

      System.out.println("Content added");

      //Closing the content stream
      contentStream.close();

      //Saving the document
      document.save(new File("newtou.pdf"));
            
      //Closing the document
      document.close();
   }
}
