import java.io.File;
import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

public class AddingContent {
   public static void main (String args[]) throws IOException {

      //Loading an existing document
      File file = new File("fdpModification1.pdf");
      PDDocument document = PDDocument.load(file);

      //Retrieving the pages of the document
      PDPage page = document.getPage(0);
      PDPageContentStream contentStream = new PDPageContentStream(document, page,true,false);
    //Begin the Content stream
      contentStream.beginText();
      contentStream.moveTextPositionByAmount(55, 420);
      contentStream.setFont(PDType1Font.HELVETICA, 12);
      contentStream.drawString("Normal text and ");
      contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
      contentStream.drawString("bold text");
      contentStream.moveTextPositionByAmount(0, -20);
      contentStream.setFont(PDType1Font.HELVETICA_OBLIQUE, 12);
      contentStream.drawString("Italic text and ");
      contentStream.setFont(PDType1Font.HELVETICA_BOLD_OBLIQUE, 12);
      contentStream.drawString("bold italic text");
      contentStream.endText();

      //Begin the Content stream
      contentStream.beginText();

      //Setting the font to the Content stream
      contentStream.setFont(PDType1Font.TIMES_ROMAN, 12);

      //Setting the position for the line
      contentStream.newLineAtOffset(0,10);

      String text = "This is the sample document and we are adding  loda content to it.";

      //Adding text in the form of string
      contentStream.showText(text);

      //Ending the content stream
      contentStream.endText();

      System.out.println("Content added");

      //Closing the content stream
      contentStream.close();

      //Saving the document
      document.save(new File("new.pdf"));

      //Closing the document
      document.close();
   }
}