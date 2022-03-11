package io.tomcode.j4rent.core.entities;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity(name = "Document")
@Table(name = "Document")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Document extends BaseEntity<Account> {

    @Column(name = "document_code")
    private String documentCode;

    @Type(type = "jsonb")
    @Column(name="data", columnDefinition = "jsonb")
    private JsonNode data;
}
