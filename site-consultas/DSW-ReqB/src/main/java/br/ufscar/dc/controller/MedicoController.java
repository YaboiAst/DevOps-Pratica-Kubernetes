package br.ufscar.dc.controller;

import br.ufscar.dc.domain.Medico;
import br.ufscar.dc.domain.Paciente;
import br.ufscar.dc.service.spec.IMedicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/medicos")
public class MedicoController {

    @Autowired
    private IMedicoService medicoService;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @GetMapping("/listar")
    public String listar(ModelMap model){
        model.addAttribute("medicos", medicoService.buscarPorTodos());
        model.addAttribute("especialidades", medicoService.buscarEspecialidades());
        return "medico/lista";
    }

    @GetMapping("/listar/{especialdade}")
    public String listarPorEspecialidade(@PathVariable("especialidade") String especialidade, ModelMap model){
        model.addAttribute("medicos", medicoService.buscarPorEspecialidade(especialidade));
        return "medico/lista";
    }

    @GetMapping("/cadastrar")
    public String cadastrar(Medico medico){
        return "medico/cadastro";
    }

    @PostMapping("/salvar")
    public String salvar(@Valid Medico medico, BindingResult result, RedirectAttributes attr){
        if(result.hasErrors()) return "medico/cadastro";

        System.out.println(medico.getSenha());

        medico.setSenha(encoder.encode(medico.getSenha()));
        medicoService.salvar(medico);
        attr.addFlashAttribute("success", "medico.create.success");
        return "redirect:/medicos/listar";
    }

    @GetMapping("/editar/{id}")
    public String preEditar(@PathVariable("id") Long id, ModelMap model){
        model.addAttribute("medico", medicoService.buscarPorId(id));
        return "medico/cadastro";
    }

    @PostMapping("/editar")
    public String editar(@Valid Medico medico, BindingResult result, RedirectAttributes attr){
        if (result.hasErrors()) return "medico/cadastro";

        System.out.println(medico.getSenha());

        medicoService.salvar(medico);
        attr.addFlashAttribute("success", "medico.edit.success");
        return "redirect:/medicos/listar";
    }

    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable("id") Long id, ModelMap model){
        medicoService.excluir(id);
        model.addAttribute("success", "medico.delete.success");
        return listar(model);
    }
}
