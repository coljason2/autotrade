package com.mxc.trade.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("API KEY")
public class TradeKey {

    @ApiModelProperty("publicKey")
    private String publicKey;

    @ApiModelProperty("privateKey")
    private String privateKey;
}
