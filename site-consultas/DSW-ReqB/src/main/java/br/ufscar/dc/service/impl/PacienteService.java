package br.ufscar.dc.service.impl;

import br.ufscar.dc.dao.IPacienteDAO;
import br.ufscar.dc.domain.Paciente;
import br.ufscar.dc.service.spec.IPacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Service
@Transactional(readOnly = false)
public class PacienteService implements IPacienteService {

    @Autowired
    IPacienteDAO dao;

    @Override
    @Transactional(readOnly = true)
    public Paciente buscarPorId(Long id) {
        return dao.findById(id.longValue());
    }

    @Override
    @Transactional(readOnly = true)
    public List<Paciente> buscarPorTodos() {
        return dao.findAll();
    }

    @Override
    public void salvar(Paciente paciente) {
        dao.save(paciente);
    }

    @Override
    public void excluir(Long id) {
        dao.deleteById(id);
    }
}
