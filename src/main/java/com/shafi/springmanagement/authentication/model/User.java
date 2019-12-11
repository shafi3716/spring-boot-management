package com.shafi.springmanagement.authentication.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.shafi.springmanagement.config.auditoring.Auditable;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "users")
@Where(clause = "deleted = 0")
public class User extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @NotNull
    @Column(name = "user_name", length = 254, unique = true)
    private String userName;

    @NotNull
    @JsonIgnore
    @Column(nullable = false)
    private String password;

    @Column(name = "active")
    private boolean active;

    @Column(name = "deleted")
    private Integer deleted = 0;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void setDeleted() {
        this.deleted = 1;
    }
}
