package appnewssite.demo.entity.template;

import appnewssite.demo.entity.User;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;

import javax.persistence.*;
import java.sql.Timestamp;

@MappedSuperclass
@Data
public abstract class AbstractEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(updatable = false,nullable = false)
    @CreationTimestamp
    private Timestamp createdAt;


    @Column(nullable = false)
    @CreationTimestamp
    private Timestamp updatedAt;


    @JoinColumn(updatable = false)
    @CreatedBy
    private Long createdBy;

    @LastModifiedBy
    private Long updatedBy;

}
