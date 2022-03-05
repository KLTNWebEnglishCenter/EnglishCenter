package Web.EnglishCenter.service.impl;

import Web.EnglishCenter.entity.user.Authentication;
import Web.EnglishCenter.repo.AuthenticationRepo;
import Web.EnglishCenter.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class AuthenticationServiceImpl implements AuthenticationService {

    @Autowired
    private AuthenticationRepo authenticationRepo;

    @Override
    public Authentication save(Authentication authentication){
        return authenticationRepo.save(authentication);
    }

    @Override
    public void delete(Authentication authentication){
        authenticationRepo.delete(authentication);
    }

    @Override
    public List<Authentication> findAll(){
        return authenticationRepo.findAll();
    }

    @Override
    public Authentication findById(int id){
        return authenticationRepo.findById(id).get();
    }
}
