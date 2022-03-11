package io.tomcode.j4rent.core.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.AbstractAuditable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.UUID;
@Data //Set -Get
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
@Entity(name = "Member")
@Table(name = "Member")
public class Member extends BaseEntity  {
    @Column(name = "account_id")
    UUID account_id;

    @Column (name = "thread_id")
    UUID thread_id;
}