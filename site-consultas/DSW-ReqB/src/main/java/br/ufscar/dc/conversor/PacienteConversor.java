package br.ufscar.dc.conversor;

import br.ufscar.dc.domain.Paciente;
import br.ufscar.dc.service.spec.IPacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class PacienteConversor implements Converter<String, Paciente> {

    @Autowired
    private IPacienteService service;


    @Override
    public Paciente convert(String s) {
        if (s.isEmpty()) return null;

        Long id = Long.valueOf(s);
        return service.buscarPorId(id);
    }
}
