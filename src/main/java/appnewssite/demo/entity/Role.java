package appnewssite.demo.entity;

import appnewssite.demo.entity.enums.Huquq;
import appnewssite.demo.entity.template.AbstractEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Role extends AbstractEntity {

    @Column(nullable = false,unique = true)
    private String name;//ADMIN,USER, BOSHQALAR

    @Enumerated(value = EnumType.STRING)
    @ElementCollection(fetch = FetchType.LAZY)
    private List<Huquq> huquqList;

    @Column(length = 600)
    private String description;


}
