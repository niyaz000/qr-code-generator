package com.github.niyaz000.qrcodegen.mapper;

import com.github.niyaz000.qrcodegen.dto.QrCodeDto;
import com.github.niyaz000.qrcodegen.model.QrCode;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface QrCodeMapper {

  @Mapping(source = "data.data", target = "qrCode.data")
  QrCodeDto mapQrCodeToQrCodeDto(QrCode qrCode);

  QrCode mapQrCodeDtoToQrCode(QrCodeDto qrCodeDto);

}
