package br.ufscar.dc.dao;

import br.ufscar.dc.domain.Paciente;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

@SuppressWarnings("unchecked")
public interface IPacienteDAO extends CrudRepository<Paciente, Long> {

        Paciente findById(long id);
        List<Paciente> findAll();
        Paciente save(Paciente paciente);
        void deleteById(Long id);

        @Query("SELECT p FROM Paciente p WHERE p.cpf = :cpf")
        public Paciente getPacienteByCPF(@Param("cpf") String cpf);
}
