package br.ufscar.dc.service.impl;

import br.ufscar.dc.dao.IMedicoDAO;
import br.ufscar.dc.dao.IPacienteDAO;
import br.ufscar.dc.domain.Medico;
import br.ufscar.dc.domain.Paciente;
import br.ufscar.dc.service.spec.IMedicoService;
import br.ufscar.dc.service.spec.IPacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Service
@Transactional(readOnly = false)
public class MedicoService implements IMedicoService {

    @Autowired
    IMedicoDAO dao;

    @Override
    @Transactional(readOnly = true)
    public Medico buscarPorId(Long id) {
        return dao.findById(id.longValue());
    }

    @Override
    @Transactional(readOnly = true)
    public List<Medico> buscarPorTodos() {
        return dao.findAll();
    }

    @Override
    public void salvar(Medico medico) {
        dao.save(medico);
    }

    @Override
    public void excluir(Long id) {
        dao.deleteById(id);
    }

    @Override
    public List<Medico> buscarPorEspecialidade(String especialidade) {
        return dao.getMedicoByEspecialidade(especialidade);
    }

    @Override
    public List<String> buscarEspecialidades() { return dao.getEspecialidades(); }
}
