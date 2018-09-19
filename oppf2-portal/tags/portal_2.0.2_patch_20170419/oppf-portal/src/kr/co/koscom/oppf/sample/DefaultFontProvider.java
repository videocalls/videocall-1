package kr.co.koscom.oppf.sample;

import java.io.IOException;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactoryImp;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.BaseFont;

// ConvertHtmlToPdfController.java에서 사용할 클래스임
public class DefaultFontProvider extends FontFactoryImp {
	private String _default;
	private Document document;
	private Font defaultFont;

	public DefaultFontProvider(String def) {
		_default = def;
	}

	// I believe this is the correct override, but there are quite a few others.
	public Font getFont(String fontname, String encoding, boolean embedded, float size, int style, BaseColor color) {
		try {
			return new Font(BaseFont.createFont(_default, BaseFont.IDENTITY_H, BaseFont.EMBEDDED), 9, style,
					BaseColor.BLACK);
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public Font PdfSample() {
		try {
			document = new Document(PageSize.A4, 50, 50, 50, 50);
			BaseFont bf = BaseFont.createFont("HYGoThic-Medium", "UniKS-UCS2-H", BaseFont.NOT_EMBEDDED);
			defaultFont = new Font(bf, 10, Font.NORMAL, BaseColor.BLACK);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
