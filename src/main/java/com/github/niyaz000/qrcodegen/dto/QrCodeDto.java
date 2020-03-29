package com.github.niyaz000.qrcodegen.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.niyaz000.qrcodegen.constant.QrImageType;
import com.github.niyaz000.qrcodegen.constant.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class QrCodeDto {

  private String name;

  private int width;

  private int height;

  private QrImageType type;

  @JsonProperty("fore_ground_color")
  private int foreGroundColor;

  @JsonProperty("back_ground_color")
  private int backGroundColor;

  private byte[] data;

  private Status status;

  private String url;

}
