package br.ufscar.dc.dao;

import br.ufscar.dc.domain.Usuario;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

@SuppressWarnings("unchecked")
public interface IUsuarioDAO extends CrudRepository<Usuario, Long> {

        Usuario findById(long id);
        List<Usuario> findAll();
        Usuario save(Usuario usuario);
        void deleteById(Long id);

        @Query("SELECT u FROM Usuario u WHERE u.email = :email")
        public Usuario getUserByUsername(@Param("email") String email);
}