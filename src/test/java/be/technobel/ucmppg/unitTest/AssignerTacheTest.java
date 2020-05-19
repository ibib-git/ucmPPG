package be.technobel.ucmppg.unitTest;

import be.technobel.ucmppg.Exception.ErrorServiceException;
import be.technobel.ucmppg.bl.dto.projet.ProjetDTO;
import be.technobel.ucmppg.bl.service.droits.TokenVerificationDroitService;
import be.technobel.ucmppg.bl.service.projet.AssignerTacheService;
import be.technobel.ucmppg.configuration.Constantes;
import be.technobel.ucmppg.dal.entities.ProjetEntity;
import be.technobel.ucmppg.dal.entities.TacheEntity;
import be.technobel.ucmppg.dal.entities.UniteDeTempsEnum;
import be.technobel.ucmppg.dal.entities.UtilisateurEntity;
import be.technobel.ucmppg.dal.repositories.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.HashSet;
import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AssignerTacheTest {

    @Mock
    private UtilisateurRepository mockUtilisateurRepo;
    @Mock
    private ProjetRepository mockProjetRepo;
    @Mock
    private TacheRepository mockTacheRepo;
    @Mock
    private EtapeWorkflowRepository mockEtapeRepo;
    @Mock
    private HistoriqueTacheRepository mockHistoriqueRepo;
    @Mock
    private TokenVerificationDroitService mockVerificationDroitService;

    private AssignerTacheService assignerTacheService;

    @Before
    public void init (){
        /*
        when(mockVerificationDroitService.verificationDroitUtilisateurService(1L, Constantes.DROIT_SUPPRIMER_COLLABORATEUR_TACHE,1L)).thenReturn(true);
        when(mockVerificationDroitService.verificationDroitUtilisateurService(1L, Constantes.DROIT_ASSIGNER_TACHE,1L)).thenReturn(true);
        when(mockVerificationDroitService.verificationDroitUtilisateurService(1L, Constantes.DROIT_PRENDRE_TACHE,1L)).thenReturn(true);


        when(mockTacheRepo.findById(1L)).thenReturn(null);
        when(mockTacheRepo.getIdTacheParent(1L)).thenReturn(null);
        when(mockProjetRepo.getIdProjetEntityByTacheEnfant(1L)).thenReturn(null);
        when(mockProjetRepo.getIdProjetEntityByTacheParent(1L)).thenReturn(null);

        when(mockUtilisateurRepo.findById(1L)).thenReturn(null);

        when(mockEtapeRepo.getEtapeByIdTache(1L)).thenReturn(null);
        when(mockEtapeRepo.getEtapeByIdTacheEnfant(1L)).thenReturn(null);
*/
    }

    @Test
    public void assignerTache_TacheEnfantEtToutesPermissions_ok () throws ErrorServiceException {
        when(mockVerificationDroitService.verificationDroitUtilisateurService(1L, Constantes.DROIT_SUPPRIMER_COLLABORATEUR_TACHE,1L)).thenReturn(true);
        when(mockVerificationDroitService.verificationDroitUtilisateurService(1L, Constantes.DROIT_ASSIGNER_TACHE,1L)).thenReturn(true);
        when(mockVerificationDroitService.verificationDroitUtilisateurService(1L, Constantes.DROIT_PRENDRE_TACHE,1L)).thenReturn(true);


        UtilisateurEntity utilisateur1 = new UtilisateurEntity(1L,"momo@gmail","Test=1234","Momo",null,null,null,null,null,null,null);
        TacheEntity tacheEntity = new TacheEntity(1L,"tache1","tache sans parent, enfant ou precedent",null,null,1, UniteDeTempsEnum.HEURE,new HashSet<>(),utilisateur1);
        when(mockTacheRepo.findById(1L)).thenReturn(Optional.of(tacheEntity));

        when(mockProjetRepo.findById(1L)).thenReturn(Optional.of(new ProjetEntity()));

        AssignerTacheService assignerTacheService = new AssignerTacheService(mockTacheRepo,mockUtilisateurRepo,mockProjetRepo, mockHistoriqueRepo,mockEtapeRepo,mockVerificationDroitService);
        ProjetDTO projetDTO = assignerTacheService.assignation(1L,1L,1L);
        //verify(mockHistoriqueRepo).findById(1L);
        System.out.println(projetDTO);
        //Assert.assertNotNull(projetDTO);
        //TODO DAMIEN: achever les tests avec mockito pour les servies assigner taches & valider etape workflow
    }


}
