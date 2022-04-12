package io.tomcode.j4rent.core.entities;

import com.fasterxml.jackson.databind.JsonNode;
import io.tomcode.j4rent.core.entities.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.util.UUID;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class OTP extends BaseEntity {

    private int otp;

    @Column(name = "document_id")
    private UUID documentId;
}
