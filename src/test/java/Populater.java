import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentCatalog;
import org.apache.pdfbox.pdmodel.interactive.form.PDAcroForm;
import org.apache.pdfbox.pdmodel.interactive.form.PDField;

public class Populater {

	private static PDDocument _pdfDocument;

	public static void main(String[] args) {
		String fileName = "fdpModification.pdf";
		String originalPdf = "fdpModification.pdf";
		String targetPdf = "new.pdf";

		try {
			populateAndCopy(originalPdf, targetPdf);
		} catch (IOException | COSVisitorException e) {
			e.printStackTrace();
		}

		System.out.println("Complete");
	}

	private static void populateAndCopy(String originalPdf, String targetPdf) throws IOException, COSVisitorException {
		String fileName = "fdpModification.pdf";
		_pdfDocument = PDDocument.load(new File(fileName));
		printFields();
		_pdfDocument.getNumberOfPages();
		//printFields();  //Uncomment to see the fields in this document in console

		setField("PTEFederalAwardNo", "SomeFieldValue");
		_pdfDocument.save(targetPdf);
		_pdfDocument.close();
	}

    public static void setField(String name, String value ) throws IOException {
        PDDocumentCatalog docCatalog = _pdfDocument.getDocumentCatalog();
        PDAcroForm acroForm = docCatalog.getAcroForm();
        PDField field = acroForm.getField( name );
        if( field != null ) {
            field.setValue(value);
        }
        else {
            System.err.println( "No field found with name:" + name );
        }
    }

    @SuppressWarnings("rawtypes")
	public static void printFields() throws IOException {
        PDDocumentCatalog docCatalog = _pdfDocument.getDocumentCatalog();
        PDAcroForm acroForm = docCatalog.getAcroForm();
        List fields = acroForm.getFields();
        Iterator fieldsIter = fields.iterator();

        System.out.println(new Integer(fields.size()).toString() + " top-level fields were found on the form");

        while( fieldsIter.hasNext()) {
            PDField field = (PDField)fieldsIter.next();
               processField(field, "|--", field.getPartialName());
        }
    }

    @SuppressWarnings("rawtypes")
	private static void processField(PDField field, String sLevel, String sParent) throws IOException
    {
        List kids = field.getWidgets();
        if(kids != null) {
            Iterator kidsIter = kids.iterator();
            if(!sParent.equals(field.getPartialName())) {
               sParent = sParent + "." + field.getPartialName();
            }

            System.out.println(sLevel + sParent);

            while(kidsIter.hasNext()) {
               Object pdfObj = kidsIter.next();
               if(pdfObj instanceof PDField) {
                   PDField kid = (PDField)pdfObj;
                   processField(kid, "|  " + sLevel, sParent);
               }
            }
         }
         else {
             String outputString = sLevel + sParent + "." + field.getPartialName() + ",  type=" + field.getClass().getName();
             System.out.println(outputString);
         }
    }
}