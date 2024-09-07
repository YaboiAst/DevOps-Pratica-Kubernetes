package br.ufscar.dc;

import br.ufscar.dc.dao.IConsultaDAO;
import br.ufscar.dc.dao.IMedicoDAO;
import br.ufscar.dc.dao.IPacienteDAO;
import br.ufscar.dc.dao.IUsuarioDAO;
import br.ufscar.dc.domain.Usuario;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class ConsultasApplication {

    public static void main(String[] args){
        SpringApplication.run(ConsultasApplication.class, args);
    }

    @Bean
    public CommandLineRunner demo(IUsuarioDAO usuario, BCryptPasswordEncoder encoder){
        return (args) -> {
            if (!usuario.findAll().isEmpty()) return;

            System.out.println("\n\n\n\nBanco vazio");
            System.out.println("Populando... \n\n\n\n");

            // Inject data here
            Usuario u1 = new Usuario();
            u1.setNome("admin");
            u1.setEmail("admin@gmail.com");
            u1.setSenha(encoder.encode("admin"));
            u1.setRole("ROLE_ADMIN");
            u1.setEnabled(true);
            usuario.save(u1);
        };
    }
}
