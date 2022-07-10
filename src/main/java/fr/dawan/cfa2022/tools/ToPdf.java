package fr.dawan.cfa2022.tools;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;


import org.jsoup.Jsoup;
import org.jsoup.helper.W3CDom;
import org.jsoup.nodes.Document;

import com.openhtmltopdf.pdfboxout.PdfRendererBuilder;

public class ToPdf {

	public static void convertHtmlToPdf(String htmlContent,  String outputPdf) throws Exception {
		
	//création du outputStream pour le stockage
		OutputStream os = new BufferedOutputStream(new FileOutputStream(outputPdf));
		
		//objet permettant de builder le pdf
		PdfRendererBuilder builder = new PdfRendererBuilder();
		builder.withUri(outputPdf);
		builder.toStream(os);
		
		//parsing du html reçu
		Document documentHtml = Jsoup.parse(htmlContent, "UTF-8");
		builder.withW3cDocument(new W3CDom().fromJsoup(documentHtml), "/");
		builder.run();

		os.close();	
	}
}
