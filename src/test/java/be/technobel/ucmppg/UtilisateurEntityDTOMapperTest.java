package be.technobel.ucmppg;

import be.technobel.ucmppg.BL.dto.UtilisateurDetailsDTO;
import be.technobel.ucmppg.BL.dto.UtilisateurEnregistrementDTO;
import be.technobel.ucmppg.dal.entities.UtilisateurEntity;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

public class UtilisateurEntityDTOMapperTest {


    UtilisateurEntity utilisateurEntityWithAllParamNoProjets;
    private Validator validator;

    @Before
    public void init ()
    {
        utilisateurEntityWithAllParamNoProjets = new UtilisateurEntity((long) 5, "sith@empireGalactique.st","Order#66","DarkVador","Skylwalker","Anakin","+66456123789","https://vignette.wikia.nocookie.net/lemondededisney/images/3/3d/Dark-vador-1024x768.jpeg/revision/latest?cb=20171030110748&path-prefix=fr","Que la force soit avec vous",null);
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    // region UserDTODetails
    @Test
    public void utilisateurDetailsDTO_UtilisateurEntityAvecTousParamMaisAucunProjets_EqualsTrue()
    {
        UtilisateurDetailsDTO userDTOExpected = new UtilisateurDetailsDTO((long) 5, "sith@empireGalactique.st","Order#66","Skylwalker","Anakin","DarkVador","+66456123789","Que la force soit avec vous","https://vignette.wikia.nocookie.net/lemondededisney/images/3/3d/Dark-vador-1024x768.jpeg/revision/latest?cb=20171030110748&path-prefix=fr");

        UtilisateurDetailsDTO utilisateurDetailsDTOFromEntity = new UtilisateurDetailsDTO(utilisateurEntityWithAllParamNoProjets);

        Assert.assertEquals(userDTOExpected, utilisateurDetailsDTOFromEntity);
    }

    @Test
    public void utilisateurDetailsDTO_UtilisateurEntityAvecChampsRequiredEtResteANull_EqualsTrue()
    {
        UtilisateurDetailsDTO userDTOExpected = new UtilisateurDetailsDTO(null, "sith@empireGalactique.st","Order#66",null,null,"DarkVador",null,null,null);
        UtilisateurEntity utilisateurEntityWithOnlyRequiredParam = new UtilisateurEntity();
        utilisateurEntityWithOnlyRequiredParam.setEmailUtilisateur("sith@empireGalactique.st");
        utilisateurEntityWithOnlyRequiredParam.setMotDePasseUtilisateur("Order#66");
        utilisateurEntityWithOnlyRequiredParam.setPseudoUtilisateur("DarkVador");

        UtilisateurDetailsDTO utilisateurDetailsDTOFromEntity = new UtilisateurDetailsDTO(utilisateurEntityWithOnlyRequiredParam);

        Assert.assertEquals(userDTOExpected, utilisateurDetailsDTOFromEntity);
    }

    @Test
    public void utilisateurDetailsDTO_UtilisateurEntityNull_EqualsTrue()
    {
        UtilisateurDetailsDTO userDTOExpected = new UtilisateurDetailsDTO();
        UtilisateurEntity utilisateurEntityFromPastNull = new UtilisateurEntity();

        UtilisateurDetailsDTO utilisateurDetailsDTOFromEntity = new UtilisateurDetailsDTO(utilisateurEntityFromPastNull);

        Assert.assertEquals(userDTOExpected, utilisateurDetailsDTOFromEntity);
    }
    // endregion

    // region UtilisateurEnregistrementDTO
    @Test
    public void utilisateurEntity_UtilisateurEnregistrementDTOComplet_EqualsTrue()
    {
        UtilisateurEnregistrementDTO utilisateurEnregistrementDTO = new UtilisateurEnregistrementDTO( "sith@empireGalactique.st","Order#66","Skylwalker","Anakin","DarkVador","+66456123789","Que la force soit avec vous","https://vignette.wikia.nocookie.net/lemondededisney/images/3/3d/Dark-vador-1024x768.jpeg/revision/latest?cb=20171030110748&path-prefix=fr");

        UtilisateurEntity utilisateurEntityFromDTORegister = new UtilisateurEntity(utilisateurEnregistrementDTO);

        //Simule l'auto génération de l'ID en DB
        utilisateurEntityFromDTORegister.setIdUtilisateur((long)5);
        Assert.assertEquals(utilisateurEntityWithAllParamNoProjets, utilisateurEntityFromDTORegister);
    }

    @Test ()
    public void utilisateurEntity_UtilisateurEnregistrementDTOAvecMotDePasseNeCorrespondPasRegex_ConstraintViolationIsEmptyPatternFalse()
    {
        UtilisateurEnregistrementDTO utilisateurEnregistrementDTO = new UtilisateurEnregistrementDTO( "sith@empireGalactique.st","order","Skylwalker","Anakin","DarkVador","+66456123789","Que la force soit avec vous","https://vignette.wikia.nocookie.net/lemondededisney/images/3/3d/Dark-vador-1024x768.jpeg/revision/latest?cb=20171030110748&path-prefix=fr");

        UtilisateurEntity utilisateurEntityFromDTORegister = new UtilisateurEntity(utilisateurEnregistrementDTO);
        Set<ConstraintViolation<UtilisateurEntity>> constraintViolationSets = validator.validate(utilisateurEntityFromDTORegister);

        Assert.assertFalse(constraintViolationSets.isEmpty());
    }

    @Test ()
    public void utilisateurEntity_UtilisateurEnregistrementDTOAvecEmailNeCorrespondPasRegex_ConstraintViolationIsEmptyEmailFalse()
    {
        UtilisateurEnregistrementDTO utilisateurEnregistrementDTO = new UtilisateurEnregistrementDTO( "sith","Order#66","Skylwalker","Anakin","DarkVador","+66456123789","Que la force soit avec vous","https://vignette.wikia.nocookie.net/lemondededisney/images/3/3d/Dark-vador-1024x768.jpeg/revision/latest?cb=20171030110748&path-prefix=fr");

        UtilisateurEntity utilisateurEntityFromDTORegister = new UtilisateurEntity(utilisateurEnregistrementDTO);
        Set<ConstraintViolation<UtilisateurEntity>> constraintViolationSets = validator.validate(utilisateurEntityFromDTORegister);

        Assert.assertFalse(constraintViolationSets.isEmpty());
    }

    @Test ()
    public void utilisateurEntity_UtilisateurEnregistrementDTOAvecNomEtPrenomEnDessousLongueurMin_ConstraintViolationIsEmptyMinFalse()
    {
        UtilisateurEnregistrementDTO utilisateurEnregistrementDTO = new UtilisateurEnregistrementDTO( "sith","Order#66","Skylwalker","A","D","+66456123789","Que la force soit avec vous","https://vignette.wikia.nocookie.net/lemondededisney/images/3/3d/Dark-vador-1024x768.jpeg/revision/latest?cb=20171030110748&path-prefix=fr");

        UtilisateurEntity utilisateurEntityFromDTORegister = new UtilisateurEntity(utilisateurEnregistrementDTO);
        Set<ConstraintViolation<UtilisateurEntity>> constraintViolationSets = validator.validate(utilisateurEntityFromDTORegister);

        Assert.assertFalse(constraintViolationSets.isEmpty());
    }

    @Test ()
    public void utilisateurEntity_UtilisateurEnregistrementDTOSansParam_ConstraintViolationIsEmptyRequiredFalse()
    {
        UtilisateurEnregistrementDTO utilisateurEnregistrementDTO = new UtilisateurEnregistrementDTO( );

        UtilisateurEntity utilisateurEntityFromDTORegister = new UtilisateurEntity(utilisateurEnregistrementDTO);
        Set<ConstraintViolation<UtilisateurEntity>> constraintViolationSets = validator.validate(utilisateurEntityFromDTORegister);

        Assert.assertFalse(constraintViolationSets.isEmpty());
    }

    @Test
    public void utilisateurEntity_UtilisateurEnregistrementDTOCompletValide_ConstraintViolationIsEmptyTrue()
    {
        UtilisateurEnregistrementDTO utilisateurEnregistrementDTO = new UtilisateurEnregistrementDTO( "sith@empireGalactique.st","Order#66","Skylwalker","Anakin","DarkVador","+66456123789","Que la force soit avec vous","https://vignette.wikia.nocookie.net/lemondededisney/images/3/3d/Dark-vador-1024x768.jpeg/revision/latest?cb=20171030110748&path-prefix=fr");

        UtilisateurEntity utilisateurEntityFromDTORegister = new UtilisateurEntity(utilisateurEnregistrementDTO);

        //Simule l'auto génération de l'ID en DB
        utilisateurEntityFromDTORegister.setIdUtilisateur((long)5);
        Set<ConstraintViolation<UtilisateurEntity>> constraintViolationSets = validator.validate(utilisateurEntityFromDTORegister);

        Assert.assertTrue(constraintViolationSets.isEmpty());
    }

    @Test
    public void utilisateurEntity_UtilisateurEnregistrementDTOAvecChampsRequiredEtResteANullValide_ConstraintViolationIsEmptyTrue()
    {
        UtilisateurEnregistrementDTO utilisateurEnregistrementDTO = new UtilisateurEnregistrementDTO( "sith@empireGalactique.st","Order#66",null,null,"DarkVador",null,null,null);

        UtilisateurEntity utilisateurEntityFromDTORegister = new UtilisateurEntity(utilisateurEnregistrementDTO);

        //Simule l'auto génération de l'ID en DB
        utilisateurEntityFromDTORegister.setIdUtilisateur((long)5);
        Set<ConstraintViolation<UtilisateurEntity>> constraintViolationSets = validator.validate(utilisateurEntityFromDTORegister);

        Assert.assertTrue(constraintViolationSets.isEmpty());
    }
    // endregion
}
