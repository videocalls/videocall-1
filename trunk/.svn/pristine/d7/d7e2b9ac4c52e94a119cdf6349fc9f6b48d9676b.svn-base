package kr.co.koscom.oppfm.sample.model;

import kr.co.koscom.oppfm.cmm.model.CommonVO;
import kr.co.koscom.oppfm.cmm.model.ValidationCreate;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;


/**
 * SampleReq
 * <p>
 * Created by chungyeol.kim on 2017-05-12.
 */
@Data
public class SampleReq extends CommonVO {
    private static final long serialVersionUID = 8411186945557749762L;

//    @NotNull(message = "{NotNull.validation.message}", groups = {ValidationCreate.class})
//    private Long sampleNumber;

//    @NotEmpty(message = "{NotBlank.validation.message}", groups = {ValidationCreate.class})
    @NotBlank(message = "{NotBlank.validation.message}", groups = {ValidationCreate.class})
    private String sampleName;

    @NotNull(message = "{NotBlank.validation.message}", groups = {ValidationCreate.class})
    private Integer number;
}
