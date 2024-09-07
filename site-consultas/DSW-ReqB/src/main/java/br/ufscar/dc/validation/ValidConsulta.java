package br.ufscar.dc.validation;


import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = ValidConsultaValidator.class)
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidConsulta {
    String message() default "Appointment already scheduled for this date";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}