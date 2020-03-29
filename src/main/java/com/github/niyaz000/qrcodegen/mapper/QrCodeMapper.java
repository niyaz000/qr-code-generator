package com.github.niyaz000.qrcodegen.mapper;

import com.github.niyaz000.qrcodegen.dto.QrCodeDto;
import com.github.niyaz000.qrcodegen.model.QrCode;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface QrCodeMapper {

  QrCodeDto mapQrCodeToQrCodeDto(QrCode qrCode);

  @Mapping(target = "status", ignore = true)
  @Mapping(target = "url", ignore = true)
  QrCode mapQrCodeDtoToQrCode(QrCodeDto qrCodeDto);

}
