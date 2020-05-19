package be.technobel.ucmppg.unitTest;
import be.technobel.ucmppg.Exception.ErrorServiceException;
import be.technobel.ucmppg.bl.dto.projet.ProjetDTO;
import be.technobel.ucmppg.bl.service.droits.TokenVerificationDroitService;
import be.technobel.ucmppg.bl.service.projet.ChangerOrdreEtapeService;
import be.technobel.ucmppg.configuration.Constantes;
import be.technobel.ucmppg.dal.entities.*;
import be.technobel.ucmppg.dal.repositories.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ChangerOrdreEtapeWokflowUnitTest {

    @Mock
    private UtilisateurRepository mockUtilisateurRepo;
    @Mock
    private ProjetRepository mockProjetRepo;
    @Mock
    private TokenVerificationDroitService mockVerificationDroitService;

    private ChangerOrdreEtapeService changerOrdreEtapeService;


    @Before
    public void init ()
    {
        UtilisateurEntity utilisateurCreateurEntity = new UtilisateurEntity(1L,"empereur@empire.un","Ordre66!","empereur",null,null,null,null,null,null,null);
        UtilisateurEntity utilisateurMembreDroitEntity = new UtilisateurEntity(2L,"sith@empire.un","Ordre66!","DarkVador",null,null,null,null,null,null,null);
        UtilisateurEntity utilisateurMembreSansDroitEntity = new UtilisateurEntity(3L,"CT-7567@empire.un","Clone7567!!","Rex",null,null,null,null,null,null,null);

       // etapes workflow
        Set<EtapeWorkflowEntity> etapeWorkflowEntitySet = new HashSet<>();
        EtapeWorkflowEntity etapeWorkflowEntity1 = new EtapeWorkflowEntity(1L,"ToDo",null,true,1,null,null,null);
        EtapeWorkflowEntity etapeWorkflowEntity2 = new EtapeWorkflowEntity(2L,"Doing",null,true,2,null,null,null);
        EtapeWorkflowEntity etapeWorkflowEntity3 = new EtapeWorkflowEntity(3L,"Test",null,true,3,null,null,null);
        EtapeWorkflowEntity etapeWorkflowEntity4 = new EtapeWorkflowEntity(4L,"Terminé",null,true,4,null,null,null);

        etapeWorkflowEntitySet.add(etapeWorkflowEntity1);
        etapeWorkflowEntitySet.add(etapeWorkflowEntity2);
        etapeWorkflowEntitySet.add(etapeWorkflowEntity3);
        etapeWorkflowEntitySet.add(etapeWorkflowEntity4);

        // droits du projet
        Set<DroitProjetEntity> droitProjetOkEntitySet = new HashSet<>();
        DroitProjetEntity droitProjetEntity = new DroitProjetEntity(1L, Constantes.DROIT_CHANGER_ORDRE_ETAPE);
        droitProjetOkEntitySet.add(droitProjetEntity);
        Set<DroitProjetEntity> droitProjetVideEntitySet = new HashSet<>();

        // roles du projet
        Set<RoleProjetEntity> roleProjetEntitySet = new HashSet<>();
        RoleProjetEntity roleSithEntity = new RoleProjetEntity(1L,"sith",droitProjetOkEntitySet);
        RoleProjetEntity roleStormEntity = new RoleProjetEntity(2L,"stormtrooper",droitProjetVideEntitySet);
        roleProjetEntitySet.add(roleSithEntity);
        roleProjetEntitySet.add(roleStormEntity);

        ProjetEntity projetEntity = new ProjetEntity(1L,"EtoileDeLaMort","le but est d'instaurer la peur dans la galaxie",utilisateurCreateurEntity,null,etapeWorkflowEntitySet,roleProjetEntitySet);

        // membre du projet (participations)
        Set<ParticipationEntity> participationEntitySet = new HashSet<>();
        ParticipationEntity participationEntity = new ParticipationEntity(1L,true,roleSithEntity,utilisateurMembreDroitEntity,projetEntity);
        participationEntitySet.add(participationEntity);
        projetEntity.setMembresDuProjet(participationEntitySet);

        when(mockUtilisateurRepo.findById(2L)).thenReturn(Optional.of(utilisateurMembreDroitEntity));
        when(mockUtilisateurRepo.findById(3L)).thenReturn(Optional.of(utilisateurMembreSansDroitEntity));

        when(mockProjetRepo.findByIdProjet(1L)).thenReturn(Optional.of(projetEntity));
        when(mockProjetRepo.getProjetByEtapeWorkflows(1L)).thenReturn(Optional.of(projetEntity));
        when(mockProjetRepo.getProjetByEtapeWorkflows(5L)).thenReturn(Optional.empty());

        when(mockVerificationDroitService.verificationDroitUtilisateurService(2L,Constantes.DROIT_CHANGER_ORDRE_ETAPE,1L)).thenReturn(true);
        when(mockVerificationDroitService.verificationDroitUtilisateurService(3L,Constantes.DROIT_CHANGER_ORDRE_ETAPE,1L)).thenReturn(false);

        this.changerOrdreEtapeService = new ChangerOrdreEtapeService(this.mockProjetRepo,this.mockVerificationDroitService);
    }

    @Test (expected = ErrorServiceException.class)
    public void changerOrdreEtapeService_UtilisateurSansDroitEtProjetCorrect_ErrorException() throws ErrorServiceException {

        ProjetDTO projetDTO = this.changerOrdreEtapeService.execute(3L,1L,2);
    }

    @Test (expected = ErrorServiceException.class)
    public void changerOrdreEtapeService_UtilisateurOkEtProjetSansEtape_ErrorException() throws ErrorServiceException {

        ProjetDTO projetDTO = this.changerOrdreEtapeService.execute(2L,5L,2);
    }

}
