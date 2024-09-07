package br.ufscar.dc.dao;

import br.ufscar.dc.domain.Medico;
import br.ufscar.dc.domain.Paciente;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

@SuppressWarnings("unchecked")
public interface IMedicoDAO extends CrudRepository<Medico, Long> {

        Medico findById(long id);
        List<Medico> findAll();
        Medico save(Medico medico);
        void deleteById(Long id);

        @Query("SELECT m FROM Medico m WHERE m.crm = :crm")
        public Medico getMedicoByCRM(@Param("crm") String crm);

        @Query("SELECT m FROM Medico m WHERE m.especialidade = :especialidade")
        public List<Medico> getMedicoByEspecialidade(@Param("especialidade") String especialidade);

        @Query("SELECT DISTINCT especialidade FROM Medico m")
        public List<String> getEspecialidades();
}
