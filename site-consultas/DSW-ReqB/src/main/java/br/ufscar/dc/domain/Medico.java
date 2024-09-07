package br.ufscar.dc.domain;

import br.ufscar.dc.validation.UniqueCRM;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@SuppressWarnings("serial")
@Entity
@Table(name = "Medico")
public class Medico extends Usuario {

    @UniqueCRM
    @NotBlank(message = "{NotBlank.medico.CRM}")
    @Size(max = 60)
    @Column(nullable = false, length = 60)
    private String crm;

    @NotNull(message = "{NotNull.medico.especialidade}")
    @Column(nullable = false, length = 60)
    private String especialidade;

    @OneToMany(mappedBy = "paciente")
    private List<Consulta> consultas;

    public Medico(){
        super();
        this.setRole("ROLE_MEDICO");
    }

    public String getCrm() {
        return crm;
    }
    public void setCrm(String crm) {
        this.crm = crm;
    }

    public String getEspecialidade() {
        return especialidade;
    }
    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }
}
