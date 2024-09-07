package br.ufscar.dc.service.spec;

import br.ufscar.dc.domain.Paciente;

import java.util.List;

public interface IPacienteService {
    Paciente buscarPorId(Long id);
    List<Paciente> buscarPorTodos();
    void salvar(Paciente paciente);
    void excluir(Long id);
}
