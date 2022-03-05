package Web.EnglishCenter.service;


import Web.EnglishCenter.entity.Document;

import java.util.List;

public interface DocumentService {
    public Document save(Document document);
    public void delete(Document document);
    public List<Document> findAll();
    public Document findById(int id);
}
