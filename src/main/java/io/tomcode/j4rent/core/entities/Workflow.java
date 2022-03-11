package io.tomcode.j4rent.core.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.jpa.domain.AbstractAuditable;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.UUID;

@Data //Set -Get
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "Workflow")
@Table(name = "Workflow")
public class Workflow extends BaseEntity {
    // Foreign key
    @ManyToOne @JoinColumn(name = "status_id", nullable = false)
    private Status status_id;

    @ManyToOne @JoinColumn(name = "pending_post_id", nullable = false)
    private PendingPost pending_post_id;

    @CreatedBy
    @ManyToOne @JoinColumn(name = "create_by_id", nullable = false)
    private Account create_by_id;
}