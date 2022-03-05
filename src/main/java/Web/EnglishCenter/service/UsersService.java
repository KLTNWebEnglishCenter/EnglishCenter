package Web.EnglishCenter.service;


import Web.EnglishCenter.entity.user.Users;

import java.util.List;

public interface UsersService {
    public Users save(Users users);
    public void delete(Users users);
    public List<Users> findAll();
    public Users findById(int id);
    public Users findByUsername(String username);
}
