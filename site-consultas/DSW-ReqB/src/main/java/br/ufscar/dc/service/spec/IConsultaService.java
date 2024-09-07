package br.ufscar.dc.service.spec;

import br.ufscar.dc.domain.Consulta;
import br.ufscar.dc.domain.Medico;
import br.ufscar.dc.domain.Paciente;

import java.util.List;

public interface IConsultaService {
    Consulta buscarPorId(Long id);
    List<Consulta> buscarPorTodos();
    void salvar(Consulta consulta);
    void excluir(Long id);
    List<Consulta> buscarPorPaciente(Paciente id);
}
