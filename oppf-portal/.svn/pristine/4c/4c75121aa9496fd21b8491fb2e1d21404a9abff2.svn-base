package kr.co.koscom.oppf.cmm.web;

import kr.co.koscom.oppf.cmm.service.CmmFileService;
import kr.co.koscom.oppf.cmm.service.CmmFileVO;
import kr.co.koscom.oppf.cmm.service.OppfProperties;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.rowset.serial.SerialBlob;
import java.io.*;
import java.util.Iterator;
import java.util.Map;


/**
* @Project  : 코스콤 오픈플렛폼
* @FileName : CommonLoginController.java
* @Comment  : 파일업로드 Controller
* @author   : 이환덕
* @since    : 2016.04.29
* @version  : 1.0
* @see
*
* << 개정이력(Modification Information) >>
* 수정일                수정자        수정내용
* ----------- ------  ----------
* 2016.04.29  이환덕        최초생성
*
*/
@Slf4j
@Controller
public class CmmFileController {
	
	@Resource(name = "CmmFileService")
    private CmmFileService cmmFileService;
	
	public CmmFileController(){
	    
	}
	
    /**
     * @Method Name        : cmmUpload
     * @Method description : 공통 파일업로드 처리 - .
     * @param              : 
     * @return             : String
     * @throws             : Exception
     */
	@RequestMapping(value="/spt/cmm/cmmFileUpload.do",method = {RequestMethod.POST, RequestMethod.GET})
	private String cmmFileUpload(final MultipartHttpServletRequest multiRequest, HttpServletRequest request, HttpServletResponse response, ModelMap model)throws Exception{
		
	    String fileStorePath = OppfProperties.getProperty("Globals.fileStorePath");
		log.debug("-------------------------------------------------------------");
		log.debug("fileStorePath : {}", fileStorePath);
		log.debug("-------------------------------------------------------------");
		
		final Map<String, MultipartFile> files = multiRequest.getFileMap();
		
		InputStream is = null;
		OutputStream os = null;
		
		Iterator<String> iterator = multiRequest.getFileNames();
		MultipartFile multipartFile = null;
        String originalFileName = null;
        String originalFileExtension = null;
        String storedFileName = null;
		
		if(!files.isEmpty()){
		    //System.out.println("!files.isEmpty");
		    File savePath = new File(fileStorePath);
		    if(savePath.exists() == false){
		        savePath.mkdirs();
		    }
		    
		}
		
        model.addAttribute("uploadResult", "Y");
        return "jsonView";
	}
	
	public static final int BUFF_SIZE = 2048;
	
    /**
     * 첨부파일을 서버에 저장한다.
     *
     * @param file
     * @param newName
     * @param stordFilePath
     * @throws Exception
     */
    protected void writeUploadedFile(MultipartFile file, String newName, String stordFilePath) throws Exception {
        InputStream stream = null;
        OutputStream bos = null;
        String stordFilePathReal = (stordFilePath==null?"":stordFilePath).replaceAll("..","");
        try{
            stream = file.getInputStream();
            File cFile = new File(stordFilePathReal);
    
            if(!cFile.isDirectory()){
                boolean _flag = cFile.mkdirs();
                if(!_flag){
                    throw new IOException("Directory creation Failed ");
                }
            }
    
            bos = new FileOutputStream(stordFilePathReal + File.separator + newName);
    
            int bytesRead = 0;
            byte[] buffer = new byte[BUFF_SIZE];
    
            while ((bytesRead = stream.read(buffer, 0, BUFF_SIZE)) != -1) {
                bos.write(buffer, 0, bytesRead);
            }
        }catch(FileNotFoundException fnfe){
            log.debug("fnfe: {}", fnfe);
        }catch(IOException ioe){
            log.debug("ioe: {}", ioe);
        }catch(Exception e){
            log.debug("e: {}", e);
        }finally{
            if(bos != null){
                try{
                    bos.close();
                }catch(Exception ignore){
                    log.debug("IGNORED : {}", ignore.getMessage());
                }
            }
            if(stream != null) {
                try{
                    stream.close();
                }catch(Exception ignore){
                    log.debug("IGNORED : {}", ignore.getMessage());
                }
            }
        }
    }
    
    /**
     * @Method Name        : appImgDown
     * @Method description : app img를 down 한다. -> img src에 삽입
     * @param              : 
     * @return             : String
     * @throws             : Exception
     */
	@RequestMapping(value="/cmm/appImg/{appId}.do",method = {RequestMethod.POST, RequestMethod.GET})
	private String appImg(@PathVariable("appId") String appId, HttpServletResponse response)throws Exception{
		CmmFileVO paramVO = new CmmFileVO();
		paramVO.setAppId(appId);
		
		CmmFileVO data = cmmFileService.selectAppImg(paramVO);
		
		response.setHeader("Content-Disposition", "inline;filename=\"" + data.getAppName() + "\"");
		OutputStream outputStream = response.getOutputStream();
		//response.setContentType(board.getContentType());

		SerialBlob blob = new SerialBlob(data.getAppIcon());

		IOUtils.copy(blob.getBinaryStream(), outputStream);

		outputStream.flush();
		outputStream.close();
		
	    return "jsonView";
	}
	
	/**
     * @Method Name        : companyCiImgDown
     * @Method description : app img를 down 한다. -> img src에 삽입
     * @param              : 
     * @return             : String
     * @throws             : Exception
     */
	@RequestMapping(value="/cmm/companyCi/{companyProfileRegNo}.do",method = {RequestMethod.POST, RequestMethod.GET})
	private String companyCi(@PathVariable("companyProfileRegNo") String companyProfileRegNo, HttpServletResponse response)throws Exception{
		CmmFileVO paramVO = new CmmFileVO();
		paramVO.setCompanyProfileRegNo(companyProfileRegNo);
		
		CmmFileVO data = cmmFileService.selectCompanyCi(paramVO);
		
		response.setHeader("Content-Disposition", "inline;filename=\"" + data.getCompanyName() + "\"");
		OutputStream outputStream = response.getOutputStream();
		//response.setContentType(board.getContentType());

		SerialBlob blob = new SerialBlob(data.getCompanyCi());

		IOUtils.copy(blob.getBinaryStream(), outputStream);

		outputStream.flush();
		outputStream.close();
		
	    return "jsonView";
	}
	
	/**
     * @Method Name        : notiFileDown
     * @Method description : 공지사항 첨부파일 다운
     * @param              : 
     * @return             : String
     * @throws             : Exception
     */
	@RequestMapping(value="/cmm/noti/notiFileDown.do",method = {RequestMethod.POST, RequestMethod.GET})
	private String notiFileDown(@RequestParam("fileId") String fileId, @RequestParam("fileSeq") String fileSeq, HttpServletResponse response)throws Exception{
		CmmFileVO paramVO = new CmmFileVO();
		paramVO.setFileId(fileId);
		paramVO.setFileSeq(fileSeq);
		
		CmmFileVO data = cmmFileService.selectNotiFileDown(paramVO);
		
		String filename = java.net.URLEncoder.encode(data.getFileName(),"UTF-8");
		
		if(data != null && data.getFileData() != null){
			//response.setHeader("Content-Disposition", "inline;filename=\"" + filename + "\"");
			response.setHeader("Content-Disposition", "attachment;filename="+filename+";");
			response.setHeader("Content-Description", "JSP Generated Data");   
		    response.setHeader("Pragma", "no-cache");
		    response.setHeader("Cache-Control", "no-cache");
			
			OutputStream outputStream = response.getOutputStream();
			//response.setContentType(board.getContentType());
	
			SerialBlob blob = new SerialBlob(data.getFileData());
	
			IOUtils.copy(blob.getBinaryStream(), outputStream);
	
			outputStream.flush();
			outputStream.close();
		}
		
	    return "jsonView";
	}

}
