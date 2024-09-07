package br.ufscar.dc.security;

import br.ufscar.dc.dao.IUsuarioDAO;
import br.ufscar.dc.domain.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UsuarioDetailServiceImpl implements UserDetailsService {

    @Autowired
    IUsuarioDAO dao;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Usuario usuario = dao.getUserByUsername(s);

        if (usuario == null){
            throw new UsernameNotFoundException("Could not find user");
        }

        return new UsuarioDetails(usuario);
    }
}
