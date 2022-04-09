package Web.EnglishCenter.service;

import Web.EnglishCenter.entity.course.Category;

import java.util.List;

public interface CategoryService {
    public Category save(Category category);
    public void delete(Category category);
    public List<Category> findAll();
    public Category findById(int id);
    public Category findCategoryByCourseId(int courseId);
}
