package com.github.niyaz000.qrcodegen.model;

import com.github.niyaz000.qrcodegen.constant.QrImageType;
import com.github.niyaz000.qrcodegen.constant.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@Entity(name = "qr_code")
@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class QrCode {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotBlank(message = "field cannot be blank")
  private String name;

  private Integer width;

  private Integer height;

  private Integer foreGroundColor;

  private Integer backGroundColor;

  private byte[] data;

  @Enumerated(value = EnumType.ORDINAL)
  private Status status = Status.CREATED;

  @Enumerated(value = EnumType.ORDINAL)
  private QrImageType type = QrImageType.JPG;

  private String url;

  @LastModifiedDate
  private Date updatedAt;

  @CreatedDate
  private Date createdAt;

}
