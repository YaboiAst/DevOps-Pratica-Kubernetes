package br.ufscar.dc.dao;

import br.ufscar.dc.domain.Consulta;
import br.ufscar.dc.domain.Medico;
import br.ufscar.dc.domain.Paciente;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

@SuppressWarnings("unchecked")
public interface IConsultaDAO extends CrudRepository<Consulta, Long> {

        Consulta findById(long id);
        List<Consulta> findAll();
        Consulta save(Consulta consulta);
        void deleteById(Long id);

        @Query("SELECT c FROM Consulta c WHERE c.paciente = :paciente")
        public List<Consulta> getConsultaByPaciente(@Param("paciente") Paciente paciente_id);

        @Query("SELECT c FROM Consulta c WHERE c.medico = :medico")
        public List<Consulta> getConsultaByMedico(@Param("medico") Medico medico_id);

        @Query("SELECT c FROM Consulta c WHERE c.dataHora = :dataHora")
        public Consulta getConsultaByDate(@Param("dataHora") String dataHora);
}
