package br.ufscar.dc.validation;

import br.ufscar.dc.dao.IMedicoDAO;
import br.ufscar.dc.dao.IPacienteDAO;
import br.ufscar.dc.domain.Medico;
import br.ufscar.dc.domain.Paciente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
public class UniqueCRMValidator implements ConstraintValidator<UniqueCRM, String> {

    @Autowired
    private IMedicoDAO dao;

    @Override
    public boolean isValid(String CRM, ConstraintValidatorContext constraintValidatorContext) {
        if (dao != null){
            Medico m = dao.getMedicoByCRM(CRM);
            return m == null;
        }
        else {
            return true;
        }
    }
}
