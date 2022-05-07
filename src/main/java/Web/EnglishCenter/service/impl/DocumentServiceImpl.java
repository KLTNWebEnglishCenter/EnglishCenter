package Web.EnglishCenter.service.impl;

import Web.EnglishCenter.entity.Document;
import Web.EnglishCenter.repo.DocumentRepo;
import Web.EnglishCenter.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class DocumentServiceImpl implements DocumentService {

    @Autowired
    private DocumentRepo documentRepo;

    @Override
    public Document save(Document document) {
        return documentRepo.save(document);
    }

    @Override
    public void delete(Document document) {
        documentRepo.delete(document);
    }

    @Override
    public List<Document> findAll() {
        return documentRepo.findAll();
    }

    @Override
    public Document findById(int id) {
        return documentRepo.findById(id).get();
    }

    @Override
    public List<Document> findByTeacherId(int teacherId) {
        return documentRepo.findByTeacherId(teacherId);
    }

    @Override
    public Document findByIdOfSpecifyTeacher(int id, int teacherId) {
        return documentRepo.findByIdOfSpecifyTeacher(id,teacherId);
    }

    @Override
    public List<Document> findByName(String name,int teacherId) {
        return documentRepo.findByName(name,teacherId);
    }
}
