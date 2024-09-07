package br.ufscar.dc.domain;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@SuppressWarnings("serial")
@Entity
@Table(name = "Usuario")
@Inheritance(strategy = InheritanceType.JOINED)
public class Usuario extends AbstractEntity<Long> {

    @NotBlank(message = "{NotBlank.usuario.nome}")
    @Size(max = 60)
    @Column(nullable = false, length = 60, unique = true)
    private String nome;

    @NotNull(message = "{NotNull.usuario.email}")
    @Column(nullable = false, length = 60)
    private String email;

    @NotNull(message = "{NotNull.usuario.senha}")
    @Column(nullable = false, length = 60)
    private String senha;

    @NotBlank
    @Column(nullable = false, length = 20)
    private String role;

    @Column(nullable = false)
    private boolean enabled;

    public Usuario(){
        this.setRole("ROLE_ADMIN");
        this.setEnabled(true);
    }

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }
    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    }

    public boolean isEnabled() {
        return enabled;
    }
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
