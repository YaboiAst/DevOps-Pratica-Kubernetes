package br.ufscar.dc.validation;

import br.ufscar.dc.dao.IPacienteDAO;
import br.ufscar.dc.domain.Paciente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
public class UniqueCPFValidator implements ConstraintValidator<UniqueCPF, String> {

    @Autowired
    private IPacienteDAO dao;

    @Override
    public boolean isValid(String CPF, ConstraintValidatorContext constraintValidatorContext) {
        if (dao != null){
            Paciente p = dao.getPacienteByCPF(CPF);
            return p == null;
        }
        else {
            return true;
        }
    }
}
