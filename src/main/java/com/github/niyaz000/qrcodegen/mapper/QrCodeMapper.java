package com.github.niyaz000.qrcodegen.mapper;

import com.github.niyaz000.qrcodegen.constant.QrDefaults;
import com.github.niyaz000.qrcodegen.dto.QrCodeDto;
import com.github.niyaz000.qrcodegen.model.QrCode;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface QrCodeMapper {

  QrCodeDto mapQrCodeToQrCodeDto(QrCode qrCode);

  @Mapping(target = "status", ignore = true)
  @Mapping(target = "url", ignore = true)
  @Mapping(target = "width", source = "width", defaultValue = QrDefaults.DEFAULT_WIDTH)
  @Mapping(target = "height", source = "height", defaultValue = QrDefaults.DEFAULT_HEIGHT)

  @Mapping(target = "foreGroundColor", source = "foreGroundColor",
          defaultValue = QrDefaults.QR_CODE_DEFAULT_FG_COLOR)

  @Mapping(target = "backGroundColor", source = "backGroundColor",
          defaultValue = QrDefaults.QR_CODE_DEFAULT_BG_COLOR)

  @Mapping(target = "type", source = "type",
          defaultValue = QrDefaults.QR_IMG_TYPE)
  QrCode mapQrCodeDtoToQrCode(QrCodeDto qrCodeDto);

}
