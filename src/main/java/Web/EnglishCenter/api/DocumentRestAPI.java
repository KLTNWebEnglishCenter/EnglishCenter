package Web.EnglishCenter.api;

import Web.EnglishCenter.entity.Document;
import Web.EnglishCenter.entity.user.Teacher;
import Web.EnglishCenter.service.DocumentService;
import Web.EnglishCenter.service.impl.AmazonClient;
import Web.EnglishCenter.utils.ConvertDTOHelper;
import Web.EnglishCenter.utils.JwtHelper;
import Web.EnglishCenter.utils.UsersType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/")
@Slf4j
public class DocumentRestAPI {

    @Autowired
    private AmazonClient amazonClient;
    @Autowired
    private DocumentService documentService;
    @Autowired
    private JwtHelper jwtHelper;

    private ConvertDTOHelper convertDTOHelper=new ConvertDTOHelper();

    @GetMapping("/documents")
    public ResponseEntity<List<Document>> getAll(HttpServletRequest request){
        //get teacher info from access token
        Teacher teacher= (Teacher) jwtHelper.getUserFromRequest(request,UsersType.TEACHER);
        List<Document> documents=documentService.findByTeacherId(teacher.getId());
        List<Document> trimDocuments=new ArrayList<>();
        if(documents.size()>0){
            trimDocuments.addAll(convertDTOHelper.trimListDocument(documents));
        }
        return ResponseEntity.ok().body(trimDocuments);
    }

    @PostMapping("/document/uploadFile")
    public ResponseEntity<Document> uploadFile(@RequestPart(value = "file") MultipartFile file, @RequestParam String name, @RequestParam String description, HttpServletRequest request) {

        //get teacher info from access token
        Teacher teacher= (Teacher) jwtHelper.getUserFromRequest(request, UsersType.TEACHER);

        String link=amazonClient.uploadFile(file);

        Document document=documentService.save(new Document(name,description,link,teacher));

        return ResponseEntity.ok().body(document);
    }

    @DeleteMapping("/document/deleteFile/{documentId}")
    public void deleteFile(@PathVariable int documentId){
        log.info(documentId+"");
        Document document=documentService.findById(documentId);
        documentService.delete(document);
        amazonClient.deleteBucketObjects(document.getName());
    }

    @PostMapping("/document/search")
    public ResponseEntity<List<Document>> searchDocument(HttpServletRequest request,@RequestParam String id,@RequestParam String name){
        //get teacher info from access token
        Teacher teacher= (Teacher) jwtHelper.getUserFromRequest(request, UsersType.TEACHER);

        List<Document> documents;
        List<Document> trimDocuments=new ArrayList<>();

        if(id.equals("")&&name.equals("")){
            documents=documentService.findByTeacherId(teacher.getId());
            if(documents.size()>0){
                trimDocuments.addAll(convertDTOHelper.trimListDocument(documents));
            }
        }else if(!id.equals("")&&name.equals("")){
            try {
                int parseId=Integer.parseInt(id);
                Document document=documentService.findByIdOfSpecifyTeacher(parseId, teacher.getId());
                if(document!=null){
                    trimDocuments.add(convertDTOHelper.trimDocument(document));
                }
            }catch (Exception exception){
                log.info("Error: "+exception.getMessage());
            }
        }else if(id.equals("")&&!name.equals("")){
            documents=documentService.findByName(name,teacher.getId());
            if(documents.size()>0){
                trimDocuments.addAll(convertDTOHelper.trimListDocument(documents));
            }
        }else{
            try {
                int parseId=Integer.parseInt(id);
                Document document=documentService.findByIdOfSpecifyTeacher(parseId, teacher.getId());
                if(document!=null){
                    trimDocuments.add(convertDTOHelper.trimDocument(document));
                }else{
                    documents=documentService.findByName(name,teacher.getId());
                    if(documents.size()>0){
                        trimDocuments.addAll(convertDTOHelper.trimListDocument(documents));
                    }
                }
            }catch (Exception exception){
                log.info("Error: "+exception.getMessage());
                documents=documentService.findByName(name,teacher.getId());
                if(documents.size()>0){
                    trimDocuments.addAll(convertDTOHelper.trimListDocument(documents));
                }
            }
        }

        return ResponseEntity.ok().body(trimDocuments);
    }
}
