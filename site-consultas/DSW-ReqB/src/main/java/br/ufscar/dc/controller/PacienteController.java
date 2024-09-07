package br.ufscar.dc.controller;

import br.ufscar.dc.dao.IPacienteDAO;
import br.ufscar.dc.domain.Paciente;
import br.ufscar.dc.service.spec.IPacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.naming.Binding;
import javax.validation.Valid;

@Controller
@RequestMapping("/pacientes")
public class PacienteController {

    @Autowired
    private IPacienteService pacienteService;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @GetMapping("/listar")
    public String listar(ModelMap model){
        model.addAttribute("pacientes", pacienteService.buscarPorTodos());
        return "paciente/lista";
    }

    @GetMapping("/cadastrar")
    public String cadastrar(Paciente paciente) { return "paciente/cadastro"; }

    @PostMapping("/salvar")
    public String salvar(@Valid Paciente paciente, BindingResult result, RedirectAttributes attr){
        if(result.hasErrors()) return "paciente/cadastro";

        System.out.println(paciente.getSenha());

        paciente.setSenha(encoder.encode(paciente.getSenha()));
        pacienteService.salvar(paciente);
        attr.addFlashAttribute("success", "usuario.create.success");
        return "redirect:/pacientes/listar";
    }

    @GetMapping("/editar/{id}")
    public String preEditar(@PathVariable("id") Long id, ModelMap model){
        model.addAttribute("paciente", pacienteService.buscarPorId(id));
        return "paciente/cadastro";
    }

    @PostMapping("/editar")
    public String editar(@Valid Paciente paciente, BindingResult result, RedirectAttributes attr){
        if (result.hasErrors()) return "paciente/cadastro";

        System.out.println(paciente.getSenha());

        pacienteService.salvar(paciente);
        attr.addFlashAttribute("success", "paciente.edit.success");
        return "redirect:/pacientes/listar";
    }

    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable("id") Long id, ModelMap model){
        pacienteService.excluir(id);
        model.addAttribute("success", "paciente.delete.success");
        return listar(model);
    }
}
