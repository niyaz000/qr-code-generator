package com.github.niyaz000.qrcodegen.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.niyaz000.qrcodegen.constant.QrImageType;
import com.github.niyaz000.qrcodegen.constant.Status;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class QrCodeDto {

  @lombok.Data
  @Builder
  @NoArgsConstructor
  public static class Meta {
    private String name;
    private int width;
    private int height;
    private QrImageType type = QrImageType.JPG;

    @JsonProperty("foreground_color")
    private int foreGroundColor;

    @JsonProperty("background_color")
    private int backGroundColor;
  }

  @lombok.Data
  @Builder
  @NoArgsConstructor
  public static class Data {
    private byte[] data;
  }

  private Meta meta;
  private Data data;

  private Status status;
  private String url;

}
