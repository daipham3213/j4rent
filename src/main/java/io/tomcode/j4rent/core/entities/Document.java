package io.tomcode.j4rent.core.entities;

import com.fasterxml.jackson.databind.JsonNode;
import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "Document")
@Table(name = "document")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
public class Document extends BaseEntity {
    @Column(name = "document_code")
    private String documentCode;

    @Type(type = "jsonb")
    @Column(name = "data", columnDefinition = "jsonb")
    private JsonNode data;

    private boolean isWorkflow;

    private boolean isOTP;

    @OneToMany(mappedBy = "document", orphanRemoval = true)
    private List<Workflow> workflows = new ArrayList<>();
}
