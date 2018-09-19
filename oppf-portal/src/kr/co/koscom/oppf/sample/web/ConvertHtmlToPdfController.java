package kr.co.koscom.oppf.sample.web;

import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.html.simpleparser.HTMLWorker;
import com.itextpdf.text.html.simpleparser.StyleSheet;
import com.itextpdf.text.pdf.PdfWriter;
import kr.co.koscom.oppf.cmm.util.OppfStringUtil;
import kr.co.koscom.oppf.sample.DefaultFontProvider;
import kr.co.koscom.oppf.sample.service.SampleVO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.*;
import java.util.HashMap;
import java.util.List;

// HTML 소스를 이용하여 PDF파일을 만듦
@SuppressWarnings("deprecation")
@Controller
public class ConvertHtmlToPdfController {
	
	@RequestMapping(value = "/sample/samplePdfJsp.do")
    private String test(@ModelAttribute("sampleVO") SampleVO sampleVO, ModelMap model) {
        //System.out.println("약관동의 시작 스텝");
        model.addAttribute("sampleVO", sampleVO);
        return "sample/samplePdfJsp";
    }   
    
    @RequestMapping(value = "/sample/samplePdfJsp2.do")
    private String samplePdfJsp2() {
        //System.out.println("약관동의 완료 스텝");       
        return "sample/samplePdfJsp2";
    }
    
	@RequestMapping(value="/sample/test2.ajax",method = {RequestMethod.POST})
	public String main2(@ModelAttribute("sampleVO") SampleVO sampleVO, ModelMap model) throws Exception{
	//public void main2(SampleVO sampleVO) throws Exception {
		String file = "d:\\약관동의_3.pdf";

		PdfWriter pdfWriter = null;
		
		//System.out.println(sampleVO.getSearchName());

		try {
			//System.out.println(file);

			// create a new document
			Document document = new Document();

			// get Instance of the PDFWriter
			pdfWriter = PdfWriter.getInstance(document, new FileOutputStream(file));

			document.setPageSize(PageSize.A4);

			document.open();

			HTMLWorker htmlWorker = new HTMLWorker(document);

			HashMap<String, Object> interfaceProps = new HashMap<String, Object>();

			StyleSheet styles = new StyleSheet();

			DefaultFontProvider dfp = new DefaultFontProvider("c://malgun.ttf");			
			interfaceProps.put(HTMLWorker.FONT_PROVIDER, dfp); // 폰트 파일 설정 (한글 나오게 하기 위해 설정 필요함)
			
			// 최종 내용 출력을 위한 변수
	        String content = "";
	        content = toPdf();
			
			StringBuffer sb = new StringBuffer();
			content = toHtml(sampleVO, content);
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
			model.addAttribute("rsMsg","성공");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "jsonView";
	}
	
	
	// 특정 경로에 있는 html 파일(약관동의 템플릿)을 읽어옵니다
	public static String toPdf() {
		// 버퍼 생성
        BufferedReader br = null;        
         
        // Input 스트림 생성
        InputStreamReader isr = null;    
         
        // File Input 스트림 생성
        FileInputStream fis = null;        
 
        // File 경로
        //File file = new File("temp/target.txt");
        File file = new File("D:/eclipse_oppf/workspace/oppf-portal/WebContent/WEB-INF/view/sample/samplePdf.html");
 
        // 버퍼로 읽어들일 임시 변수
        String temp = "";
         
        // 최종 내용 출력을 위한 변수
        String content = "";
         
        try {             
            // 파일을 읽어들여 File Input 스트림 객체 생성
            fis = new FileInputStream(file);
             
            // File Input 스트림 객체를 이용해 Input 스트림 객체를 생서하는데 인코딩을 UTF-8로 지정
            isr = new InputStreamReader(fis, "UTF-8");
             
            // Input 스트림 객체를 이용하여 버퍼를 생성
            br = new BufferedReader(isr);
         
            // 버퍼를 한줄한줄 읽어들여 내용 추출
            while( (temp = br.readLine()) != null) {
                content += temp + "\n";
            }
             
            //System.out.println("================== 파일 내용 출력 ==================");
            //System.out.println(content);
             
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                isr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }  
        
        return content;
	}
	
	public static String toHtml(@ModelAttribute("sampleVO") SampleVO sampleVO, String content) {
//		String varName = "유제량";
//		String varGender = "남";
//		String varEngName = "Ryu Je Ryang";
//		String varDay = "20160421";
		String varName = sampleVO.getSearchId();
		String varGender = sampleVO.getSearchName();
		String varEngName = sampleVO.getSearchState();
		String varDay = sampleVO.getSearchCountry();
		
		String contentHtml = OppfStringUtil.replace(content, "++varName++", varName); //파라미터로 넘어온 한글이름
		contentHtml = OppfStringUtil.replace(contentHtml, "++varGender++", varGender); //파라미터로 넘어온 성별
		contentHtml = OppfStringUtil.replace(contentHtml, "++varEngName++", varEngName); //파라미터로 넘어온 영문이름
		contentHtml = OppfStringUtil.replace(contentHtml, "++varDay++", varDay); //파라미터로 넘어온 동의날짜
		
		return contentHtml;
	}
	
}
