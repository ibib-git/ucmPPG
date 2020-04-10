package be.technobel.ucmppg.controllers;

import be.technobel.ucmppg.bl.dto.projet.workflow.OrdreEtapeDTO;
import be.technobel.ucmppg.bl.service.projet.ChangerOrdreEtapeService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Api(value = "API pour les opérations CRUD sur les étapes du workflow")
@RestController
@CrossOrigin
@RequestMapping("/etape")
public class EtapeWorkflowController {

    @Autowired
    ChangerOrdreEtapeService changerOrdreEtapeService;

    @PatchMapping("{idEtape}/ordre")
    public ResponseEntity<Boolean> changerOrdreEtape (@PathVariable Long idEtape, @RequestBody OrdreEtapeDTO ordreEtapeDTO)
    {
        return ResponseEntity.ok(this.changerOrdreEtapeService.execute(ordreEtapeDTO.getIdUtilisateur(),idEtape,ordreEtapeDTO.getNvOrdre()));
    }
}
