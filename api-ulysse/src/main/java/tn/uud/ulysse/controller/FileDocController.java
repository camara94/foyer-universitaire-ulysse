package tn.uud.ulysse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import tn.uud.ulysse.models.Activite;
import tn.uud.ulysse.models.Chambre;
import tn.uud.ulysse.models.Club;
import tn.uud.ulysse.models.FileDoc;
import tn.uud.ulysse.repositry.ActiviteRepository;
import tn.uud.ulysse.repositry.ChambreRepository;
import tn.uud.ulysse.repositry.ClubRepository;
import tn.uud.ulysse.repositry.FileDocRepository;
import tn.uud.ulysse.storage.StorageService;


import java.util.stream.Collectors;

@CrossOrigin("*")
@RestController
public class FileDocController {
	
	@Autowired
	private ChambreRepository chambreRepository;
	
	@Autowired
	private FileDocRepository fileDocRepository;
	
	@Autowired
	private ClubRepository clubRepository;

	@Autowired
    private StorageService storageService;
	
	@Autowired
	private ActiviteRepository activiteRepository;
    

    

    @GetMapping( value = "/files/{id}" )
    public FileDoc getFile(@PathVariable Long id) {
        FileDoc doc = this.fileDocRepository.getFileDocById(id);
        return doc;
    }

    @GetMapping("/download/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> downloadFile(@PathVariable String filename) {

        Resource resource = storageService.loadAsResource(filename);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }

    @PostMapping("/upload-file/{idChambre}")
    public FileDoc uploadFile(@PathVariable Long idChambre, @RequestParam("file") MultipartFile file ) {
        String name = storageService.store(file).substring(0, storageService.store(file).lastIndexOf("."));
        Chambre chambre = this.chambreRepository.getChambreById(idChambre);
        
        String uri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/resource/")
                .path(name)
                .toUriString();
        FileDoc fileDoc = new FileDoc(name, uri, file.getContentType(), file.getSize());
        
		FileDoc fileDoc1 = this.fileDocRepository.save(fileDoc);
		
		chambre.setFileDoc(fileDoc1);
		this.chambreRepository.save( chambre );
		
		return fileDoc1;
    }
    
    @PostMapping("/logo-club/{idClub}")
    public FileDoc uploadLogo(@PathVariable Long idClub, @RequestParam("file") MultipartFile file ) {
        String name = storageService.store(file).substring(0, storageService.store(file).lastIndexOf("."));
        Club club = this.clubRepository.getClubById(idClub);
        
        String uri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/resource/")
                .path(name)
                .toUriString();
        FileDoc fileDoc = new FileDoc(name, uri, file.getContentType(), file.getSize());
        
		FileDoc fileDoc1 = this.fileDocRepository.save(fileDoc);
		
		club.setLogo( fileDoc1 );
		this.clubRepository.save( club );
		
		return fileDoc1;
    }
    
    @PostMapping("/activite-doc/{idActivite}")
    public FileDoc uploadImageActivite(@PathVariable Long idActivite, @RequestParam("file") MultipartFile file ) {
        String name = storageService.store(file).substring(0, storageService.store(file).lastIndexOf("."));
        Activite activite = this.activiteRepository.getActiviteById( idActivite );
        
        String uri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/resource/")
                .path(name)
                .toUriString();
        FileDoc fileDoc = new FileDoc(name, uri, file.getContentType(), file.getSize());
        
		FileDoc fileDoc1 = this.fileDocRepository.save(fileDoc);
		
		activite.setFileDoc(fileDoc1);
		this.activiteRepository.save( activite );
		
		return fileDoc1;
    }

}