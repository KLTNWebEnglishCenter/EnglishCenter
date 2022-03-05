package Web.EnglishCenter.service;

import Web.EnglishCenter.entity.user.Authentication;

import java.util.List;

public interface AuthenticationService {
    public Authentication save(Authentication authentication);
    public void delete(Authentication authentication);
    public List<Authentication> findAll();
    public Authentication findById(int id);
}
