package kr.co.koscom.oppf.cmm.service;

import java.io.Serializable;

/**
* @Project  : 코스콤 오픈플렛폼
* @FileName : ImageVO.java
* @Comment  : 이미지 VO
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
@SuppressWarnings("serial")
public class ImageVO extends ComDefaultListVO implements Serializable {

	private String imageId = "";           //이미지ID
	private String imageCoursUrl = "";     //이미지경로URL
	private String imageNm = "";           //이미지명
	private String imageClCode = "";       //이미지분류코드
	private String imageFileNm = "";       //이미지파일명
	private String imageWidthValue = "";   //이미지가로값
	private String imageVrticlValue = "";  //이미지세로값
	private String imageCpcty = "";        //이미지용량
	private String imageCoursDivCode = ""; //이미지경로구분코드
	
	public String getImageId() {
		return imageId;
	}
	public void setImageId(String imageId) {
		this.imageId = imageId;
	}
	public String getImageCoursUrl() {
		return imageCoursUrl;
	}
	public void setImageCoursUrl(String imageCoursUrl) {
		this.imageCoursUrl = imageCoursUrl;
	}
	public String getImageNm() {
		return imageNm;
	}
	public void setImageNm(String imageNm) {
		this.imageNm = imageNm;
	}
	public String getImageClCode() {
		return imageClCode;
	}
	public void setImageClCode(String imageClCode) {
		this.imageClCode = imageClCode;
	}
	public String getImageFileNm() {
		return imageFileNm;
	}
	public void setImageFileNm(String imageFileNm) {
		this.imageFileNm = imageFileNm;
	}
	public String getImageWidthValue() {
		return imageWidthValue;
	}
	public void setImageWidthValue(String imageWidthValue) {
		this.imageWidthValue = imageWidthValue;
	}
	public String getImageVrticlValue() {
		return imageVrticlValue;
	}
	public void setImageVrticlValue(String imageVrticlValue) {
		this.imageVrticlValue = imageVrticlValue;
	}
	public String getImageCpcty() {
		return imageCpcty;
	}
	public void setImageCpcty(String imageCpcty) {
		this.imageCpcty = imageCpcty;
	}
	public String getImageCoursDivCode() {
		return imageCoursDivCode;
	}
	public void setImageCoursDivCode(String imageCoursDivCode) {
		this.imageCoursDivCode = imageCoursDivCode;
	}
}
