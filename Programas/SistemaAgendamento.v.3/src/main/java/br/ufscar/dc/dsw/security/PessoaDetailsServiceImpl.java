package br.ufscar.dc.dsw.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import br.ufscar.dc.dsw.dao.IPessoaDAO;
import br.ufscar.dc.dsw.domain.Pessoa;
 
public class PessoaDetailsServiceImpl implements UserDetailsService {
 
    @Autowired
    private IPessoaDAO dao;
     
    @Override
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {
        Pessoa pessoa = dao.getUserByEmail(email);
         
        if (pessoa == null) {
            throw new UsernameNotFoundException("Could not find user");
        }
         
        return new PessoaDetails(pessoa);
    }
}