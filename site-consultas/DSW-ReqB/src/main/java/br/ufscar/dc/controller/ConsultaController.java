package br.ufscar.dc.controller;

import br.ufscar.dc.domain.Consulta;
import br.ufscar.dc.domain.Medico;
import br.ufscar.dc.domain.Paciente;
import br.ufscar.dc.domain.Usuario;
import br.ufscar.dc.security.UsuarioDetails;
import br.ufscar.dc.service.spec.IConsultaService;
import br.ufscar.dc.service.spec.IMedicoService;
import br.ufscar.dc.service.spec.IPacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/consultas")
public class ConsultaController {

    @Autowired
    private IConsultaService consultaService;
    @Autowired
    private IMedicoService medicoService;
    @Autowired
    private IPacienteService pacienteService;

    @Autowired
    private BCryptPasswordEncoder encoder;

    private Usuario getUsuario() {
        UsuarioDetails usuarioDetails = (UsuarioDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return usuarioDetails.getUsuario();
    }

    private Paciente getPaciente() { return pacienteService.buscarPorId(getUsuario().getId()); }

    @GetMapping("/cadastrar")
    public String cadastrar(ModelMap model, Consulta consulta){
        consulta.setPaciente(getPaciente());
        model.addAttribute("medicos", medicoService.buscarPorTodos());
        return "consulta/cadastro";
    }

    @GetMapping("/listar")
    public String listar(ModelMap model){
        model.addAttribute("consultas", consultaService.buscarPorPaciente(getPaciente()));
        return "consulta/lista";
    }

    @PostMapping("/salvar")
    public String salvar(@Valid Consulta consulta, BindingResult result, RedirectAttributes attr){
        for (int i = 0; i < result.getErrorCount(); i++) {
            System.out.println(result.getAllErrors().get(i).getDefaultMessage());
        }

        if(result.hasErrors()) return "consulta/cadastro";

        consultaService.salvar(consulta);
        attr.addFlashAttribute("success", "consulta.create.success");
        return "redirect:/consultas/listar";
    }

    @GetMapping("/editar/{id}")
    public String preEditar(@PathVariable("id") Long id, ModelMap model){
        model.addAttribute("consulta", consultaService.buscarPorId(id));
        return "consulta/cadastro";
    }

    @PostMapping("/editar")
    public String editar(@Valid Consulta consulta, BindingResult result, RedirectAttributes attr){
        if (result.hasErrors()) return "consulta/cadastro";

        consultaService.salvar(consulta);
        attr.addFlashAttribute("success", "consulta.edit.success");
        return "redirect:/medicos/listar";
    }

    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable("id") Long id, ModelMap model){
        consultaService.excluir(id);
        model.addAttribute("success", "consulta.delete.success");
        return listar(model);
    }
}
