package br.ufscar.dc.domain;

import br.ufscar.dc.validation.UniqueCPF;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@SuppressWarnings("serial")
@Entity
@Table(name = "Paciente")
public class Paciente extends Usuario {

    @UniqueCPF
    @NotBlank(message = "{NotBlank.paciente.CPF}")
    @Size(max = 60)
    @Column(nullable = false, length = 14, unique = true)
    private String cpf;

    @NotNull(message = "{NotNull.paciente.telefone}")
    @Column(nullable = false, length = 11)
    private String telefone;

    @NotNull(message = "{NotNull.paciente.sexo}")
    @Column(nullable = false)
    private Character sexo;

    @NotNull(message = "{NotNull.paciente.dataNacimento}")
    @DateTimeFormat(pattern = "dd/mm/yyyy", iso = DateTimeFormat.ISO.DATE)
    @Column(nullable = false)
    private String dataNascimento;

    @OneToMany(mappedBy = "paciente")
    private List<Consulta> consultas;

    public Paciente(){
        super();
        this.setRole("ROLE_PACIENTE");
    }

    public String getCpf() {
        return cpf;
    }
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getTelefone() {
        return telefone;
    }
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public Character getSexo() {
        return sexo;
    }
    public void setSexo(Character sexo) {
        this.sexo = sexo;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }
    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }
}
