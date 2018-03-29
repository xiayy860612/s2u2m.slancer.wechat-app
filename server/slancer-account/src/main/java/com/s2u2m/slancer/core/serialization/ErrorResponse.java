package com.s2u2m.slancer.core.serialization;

import lombok.Getter;
import lombok.Setter;

/**
 * The type Error response.
 *
 * @author Amos Xia
 */
@Getter
@Setter
public class ErrorResponse extends BaseResponse {
    private String errMsg = "";
}
