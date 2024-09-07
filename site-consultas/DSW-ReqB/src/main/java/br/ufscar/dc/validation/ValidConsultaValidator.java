package br.ufscar.dc.validation;

import br.ufscar.dc.dao.IConsultaDAO;
import br.ufscar.dc.dao.IMedicoDAO;
import br.ufscar.dc.domain.Consulta;
import br.ufscar.dc.domain.Medico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDateTime;
import java.util.List;

@Component
public class ValidConsultaValidator implements ConstraintValidator<ValidConsulta, Consulta> {

    @Autowired
    private IConsultaDAO dao;

    @Autowired
    private IMedicoDAO medicoDAO;

    @Override
    public boolean isValid(Consulta consulta, ConstraintValidatorContext constraintValidatorContext) {
        if (dao != null){
            List<Consulta> cs = dao.getConsultaByPaciente(consulta.getPaciente());
            if (cs != null) {
                if (cs.contains(consulta)) return false;

                if (medicoDAO.findById(consulta.getMedico().getId().longValue()) == null) return false;
                if (dao.getConsultaByDate(consulta.getDataHora()) != null) return false;

                return true;
//                ((consulta.getDataHora().matches() == 0
//                        || consulta.getDataHora().getMinute() == 30)
//                        && consulta.getDataHora().getSecond() == 0);
            }
            else return false;
        }
        else return true;
    }
}