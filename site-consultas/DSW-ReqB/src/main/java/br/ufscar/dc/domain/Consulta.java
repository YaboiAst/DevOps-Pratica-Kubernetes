package br.ufscar.dc.domain;

import br.ufscar.dc.validation.ValidConsulta;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@SuppressWarnings("serial")
@Entity
@ValidConsulta
@Table(name = "Consulta")
public class Consulta extends AbstractEntity<Long> {

    @NotNull
    @ManyToOne
    @JoinColumn(name = "paciente_id")
    private Paciente paciente;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "medico_id")
    private Medico medico;

    @NotNull(message = "{NotNull.consulta.data}")
    @DateTimeFormat(pattern = "dd/mm/yyyy HH:mm", iso = DateTimeFormat.ISO.DATE_TIME)
    @Column(nullable = false, length = 60, unique = true)
    private String dataHora;

    public Paciente getPaciente() {
        return paciente;
    }
    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Medico getMedico() {
        return medico;
    }
    public void setMedico(Medico medico) {
        this.medico = medico;
    }

    public String getDataHora() {
        return dataHora;
    }
    public void setDataHora(String dataHora) {
        this.dataHora = dataHora;
    }
}
