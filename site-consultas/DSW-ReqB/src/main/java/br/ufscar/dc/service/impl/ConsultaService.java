package br.ufscar.dc.service.impl;

import br.ufscar.dc.dao.IConsultaDAO;
import br.ufscar.dc.dao.IPacienteDAO;
import br.ufscar.dc.domain.Consulta;
import br.ufscar.dc.domain.Paciente;
import br.ufscar.dc.service.spec.IConsultaService;
import br.ufscar.dc.service.spec.IPacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Service
@Transactional(readOnly = false)
public class ConsultaService implements IConsultaService {

    @Autowired
    IConsultaDAO dao;

    @Override
    @Transactional(readOnly = true)
    public Consulta buscarPorId(Long id) {
        return dao.findById(id.longValue());
    }

    @Override
    @Transactional(readOnly = true)
    public List<Consulta> buscarPorTodos() {
        return dao.findAll();
    }

    @Override
    public void salvar(Consulta consulta) { dao.save(consulta); }

    @Override
    public void excluir(Long id) {
        dao.deleteById(id);
    }

    @Override
    public List<Consulta> buscarPorPaciente(Paciente id) { return dao.getConsultaByPaciente(id); }
}
