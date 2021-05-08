package appnewssite.demo.entity;

import appnewssite.demo.entity.enums.Huquq;
import appnewssite.demo.entity.template.AbstractEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import java.util.*;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity(name = "users")
@AllArgsConstructor
@NoArgsConstructor
public class User extends AbstractEntity implements UserDetails {



    @Column(nullable = false)
    private String fullName;

    @Column(nullable = false,unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    private Role role;

    private boolean enabled;

    private boolean accountNonExpired=true;

    private boolean accountNonLocked=true;

    private boolean credentialsNonExpired=true;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        List<Huquq> huquqList = role.getHuquqList();
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        for (Huquq huquq : huquqList) {
            grantedAuthorities.add(new SimpleGrantedAuthority(huquq.name()));
        }

//        for (Huquq huquq : huquqList) {
//            grantedAuthorities.add(new GrantedAuthority() {
//                @Override
//                public String getAuthority() {
//                    return huquq.name();
//                }
//            });
//        }

        return grantedAuthorities;
    }


    public User(String fullName, String username, String password, Role role, boolean enabled) {
        this.fullName = fullName;
        this.username = username;
        this.password = password;
        this.role = role;
        this.enabled = enabled;
    }
}
