/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ccfco.wgyweb.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author ccfco
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User implements UserDetails,Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Basic(optional = false)
    @Column(name = "username")
    private String username;
    @Basic(optional = false)
    @Column(name = "password")
    private String password;
    @Basic(optional = false)
    @Column(name = "name")
    private String name;
    @Column(name = "email")
    private String email;
    @Column(name = "phone")
    private String phone;
    @JoinTable(name = "user_authority", joinColumns = {
        @JoinColumn(name = "user_id", referencedColumnName = "id")}, inverseJoinColumns = {
        @JoinColumn(name = "authority_id", referencedColumnName = "id")})
    @ManyToMany(cascade = CascadeType.DETACH,fetch = FetchType.EAGER)
    private List<Authority> authorities;

    @OneToMany(mappedBy = "publisher")
    private Collection<Lianjie> createLianjies;
    @OneToMany(mappedBy = "updateUser")
    private Collection<Lianjie> updateLianjies;
    @OneToMany(mappedBy = "publisher")
    private Collection<Jiuye> createJiuyes;
    @OneToMany(mappedBy = "updateUser")
    private Collection<Jiuye> updateJiuyes;
    @OneToMany(mappedBy = "publisher")
    private Collection<Dangjian> createDangjians;
    @OneToMany(mappedBy = "updateUser")
    private Collection<Dangjian> updateDangjians;
    @OneToMany(mappedBy = "publisher")
    private Collection<Biyesheng> createBiyeshengs;
    @OneToMany(mappedBy = "updateUser")
    private Collection<Biyesheng> updateBiyeshengs;
    @OneToMany(mappedBy = "publisher")
    private Collection<Xiazai> createXiazais;
    @OneToMany(mappedBy = "updateUser")
    private Collection<Xiazai> updateXiazais;
    @OneToMany(mappedBy = "publisher")
    private Collection<Dongtai> createDongtais;
    @OneToMany(mappedBy = "updateUser")
    private Collection<Dongtai> updateDongtais;
    @OneToMany(mappedBy = "publisher")
    private Collection<Tupian> createTupians;
    @OneToMany(mappedBy = "updateUser")
    private Collection<Tupian> updateTupians;
    @OneToMany(mappedBy = "publisher")
    private Collection<Jiaoyan> createJiaoyans;
    @OneToMany(mappedBy = "updateUser")
    private Collection<Jiaoyan> updateJiaoyans;
    @OneToMany(mappedBy = "publisher")
    private Collection<Tongzhi> createTongzhis;
    @OneToMany(mappedBy = "updateUser")
    private Collection<Tongzhi> updateTongzhis;
    @OneToMany(mappedBy = "publisher")
    private Collection<Gaikuang> createGaikuangs;
    @OneToMany(mappedBy = "updateUser")
    private Collection<Gaikuang> updateGaikuangs;
    @OneToMany(mappedBy = "publisher")
    private Collection<Hezuo> createHezuos;
    @OneToMany(mappedBy = "updateUser")
    private Collection<Hezuo> updateHezuos;
    @OneToMany(mappedBy = "publisher")
    private Collection<Tuanxue> createTuanxues;
    @OneToMany(mappedBy = "updateUser")
    private Collection<Tuanxue> updateTuanxues;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        //  需将 List<Authority> 转成 List<SimpleGrantedAuthority>，否则前端拿不到角色列表名称
        List<SimpleGrantedAuthority> simpleAuthorities = new ArrayList<SimpleGrantedAuthority>();
        for(GrantedAuthority authority : this.authorities){
            simpleAuthorities.add(new SimpleGrantedAuthority(authority.getAuthority()));
        }
        return simpleAuthorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
    public void setEncodePassword(String password) {
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodePasswd = encoder.encode(password);
        this.password = encodePasswd;
    }
}
