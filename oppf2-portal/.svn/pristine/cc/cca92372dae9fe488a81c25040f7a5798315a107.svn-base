package kr.co.koscom.oppf.sample.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StringReader;
import java.util.HashMap;
import java.util.List;

import org.apache.pdfbox.cos.COSDocument;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFTextStripper;

import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.html.simpleparser.HTMLWorker;
import com.itextpdf.text.html.simpleparser.StyleSheet;
import com.itextpdf.text.pdf.PdfWriter;

import kr.co.koscom.oppf.cmm.util.OppfStringUtil;
import kr.co.koscom.oppf.sample.DefaultFontProvider;
 
/**
 * This class parses the pdf file.
 * i.e this class returns the text from the pdf file.
 * @author Mubin Shrestha
 */
@SuppressWarnings("deprecation")
public class SamplePdfToJavaController {

	//Constructor
	public SamplePdfToJavaController() {		
	}
	
	//Pdf파일을 읽어와서 Text로 변환
	@SuppressWarnings("static-access")
    public String PdfToJavaConvert(String pdffilePath) throws FileNotFoundException, IOException {
		//약관동의 후 pdf 생성 파일의 경로
		String file = "d:\\약관동의pdf생성파일.pdf";
    	String content = "";
    	PdfWriter pdfWriter = null;
    	
		try {			
			// create a new document
			Document document = new Document();
			// get Instance of the PDFWriter
			pdfWriter = PdfWriter.getInstance(document, new FileOutputStream(file));
			document.setPageSize(PageSize.A4);
			document.open();
			HTMLWorker htmlWorker = new HTMLWorker(document);
			
			/*-------------------------------------*/
	        FileInputStream fi = new FileInputStream(new File(pdffilePath));
	        PDFParser parser = new PDFParser(fi);
	        parser.parse();
	        COSDocument cd = parser.getDocument();
	        PDFTextStripper stripper = new PDFTextStripper();
	        content = stripper.getText(new PDDocument(cd));
	        cd.close();
	        /*-------------------------------------*/
	        content = paramMapping(content);
	        //System.out.println(content);
	        
	        HashMap<String, Object> interfaceProps = new HashMap<String, Object>();
			StyleSheet styles = new StyleSheet();
			DefaultFontProvider dfp = new DefaultFontProvider("c://malgun.ttf");			
			interfaceProps.put(HTMLWorker.FONT_PROVIDER, dfp); // 폰트 파일 설정 (한글 나오게 하기 위해 설정 필요함)
	        StringBuffer sb = new StringBuffer();			
			sb.append(content);			
			//System.out.println(sb.toString());
			StringReader strReader = new StringReader(sb.toString());
			List<Element> objects = htmlWorker.parseToList(strReader, styles, interfaceProps);
			for (int k = 0; k < objects.size(); ++k) {
				document.add((Element) objects.get(k));
			}
	        document.close();
			// close the writer
			pdfWriter.close();
			//System.out.println("파일 생성 성공");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
        return content;
    }
    
    //Text로 변환된 소스의 변수부분에 파라미터값을 대입함 
    public static String paramMapping(String content) {
		String varName = "김민수";
		String varGender = "남";
		String varEngName = "Kim min su";
		String varDay = "20160427";
//		String varName = sampleVO.getSearchId();
//		String varGender = sampleVO.getSearchName();
//		String varEngName = sampleVO.getSearchState();
//		String varDay = sampleVO.getSearchCountry();
		
		String contentHtml = OppfStringUtil.replace(content, "++varName++", varName); //파라미터로 넘어온 한글이름
		contentHtml = OppfStringUtil.replace(contentHtml, "++varGender++", varGender); //파라미터로 넘어온 성별
		contentHtml = OppfStringUtil.replace(contentHtml, "++varEngName++", varEngName); //파라미터로 넘어온 영문이름
		contentHtml = OppfStringUtil.replace(contentHtml, "++varDay++", varDay); //파라미터로 넘어온 동의날짜
		
		return contentHtml;
	}
     
    public static void main(String args[]) throws FileNotFoundException, IOException {
        
    	//템플릿(약관동의 양식) 파일소스 경로
    	String filepath = "d:\\약관동의템플릿.pdf";
    	
        SamplePdfToJavaController samplePdfToJavaController = new SamplePdfToJavaController();
        samplePdfToJavaController.PdfToJavaConvert(filepath);
		 
    }
}
