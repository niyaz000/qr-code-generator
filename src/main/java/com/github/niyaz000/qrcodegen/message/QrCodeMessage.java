package com.github.niyaz000.qrcodegen.message;

import com.github.niyaz000.qrcodegen.constant.Action;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QrCodeMessage {

  private long accountId;
  private long id;
  private String xRequestId;
  private Action action;

}
