package br.ufscar.dc.service.spec;

import br.ufscar.dc.domain.Medico;
import br.ufscar.dc.domain.Paciente;

import java.util.List;

public interface IMedicoService {
    Medico buscarPorId(Long id);
    List<Medico> buscarPorTodos();
    void salvar(Medico medico);
    void excluir(Long id);

    List<Medico> buscarPorEspecialidade(String especialidade);
    List<String> buscarEspecialidades();
}
