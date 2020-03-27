package be.technobel.ucmppg;

import be.technobel.ucmppg.DAL.Models.UtilisateurEntity;
import be.technobel.ucmppg.dto.UserDTODetails;
import be.technobel.ucmppg.dto.UserDTORegister;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.ConstraintViolationException;

public class DTOMapperTest {

    UtilisateurEntity utilisateurEntityWithAllParamNoProjets;

    @Before
    public void init () throws Exception
    {
        utilisateurEntityWithAllParamNoProjets = new UtilisateurEntity((long) 5, "sith@empireGalactique.st","Order#66","DarkVador","Skylwalker","Anakin","+66456123789","https://vignette.wikia.nocookie.net/lemondededisney/images/3/3d/Dark-vador-1024x768.jpeg/revision/latest?cb=20171030110748&path-prefix=fr","Que la force soit avec vous",null);
    }

    // region UserDTODetails
    @Test
    public void userDTODetails_UtilisateurEntityWithAllParamNoProjets_EqualsTrue()
    {
        UserDTODetails userDTOExpected = new UserDTODetails((long) 5, "sith@empireGalactique.st","Order#66","Skylwalker","Anakin","DarkVador","+66456123789","Que la force soit avec vous","https://vignette.wikia.nocookie.net/lemondededisney/images/3/3d/Dark-vador-1024x768.jpeg/revision/latest?cb=20171030110748&path-prefix=fr");

        UserDTODetails userDTODetailsFromEntity = new UserDTODetails(utilisateurEntityWithAllParamNoProjets);

        Assert.assertEquals(userDTOExpected,userDTODetailsFromEntity);
    }

    @Test
    public void userDTODetails_UtilisateurEntityWithOnlyRequiredParam_EqualsTrue()
    {
        UserDTODetails userDTOExpected = new UserDTODetails(null, "sith@empireGalactique.st","Order#66",null,null,"DarkVador",null,null,null);
        UtilisateurEntity utilisateurEntityWithOnlyRequiredParam = new UtilisateurEntity();
        utilisateurEntityWithOnlyRequiredParam.setEmail("sith@empireGalactique.st");
        utilisateurEntityWithOnlyRequiredParam.setMotDePasse("order#66");
        utilisateurEntityWithOnlyRequiredParam.setPseudo_Utilisateur("DarkVador");

        UserDTODetails userDTODetailsFromEntity = new UserDTODetails(utilisateurEntityWithOnlyRequiredParam);

        Assert.assertEquals(userDTOExpected,userDTODetailsFromEntity);
    }
    // endregion

    // region UserDTORegister
    @Test
    public void utilisateurEntity_UserDTORegister_EqualsTrue()
    {
        UserDTORegister userDTORegister = new UserDTORegister( "sith@empireGalactique.st","Order#66","Skylwalker","Anakin","DarkVador","+66456123789","Que la force soit avec vous","https://vignette.wikia.nocookie.net/lemondededisney/images/3/3d/Dark-vador-1024x768.jpeg/revision/latest?cb=20171030110748&path-prefix=fr");

        UtilisateurEntity utilisateurEntityFromDTORegister = new UtilisateurEntity(userDTORegister);

        //Simule l'auto génération de l'ID en DB
        utilisateurEntityFromDTORegister.setId_Utilisateur((long)5);
        Assert.assertEquals(utilisateurEntityWithAllParamNoProjets,utilisateurEntityFromDTORegister);
    }

    @Test (expected = ConstraintViolationException.class)
    public void utilisateurEntity_UserDTORegisterWithPasswordNotMatchRegex_ConstraintViolationExeption()
    {
        UserDTORegister userDTORegister = new UserDTORegister( "sith@empireGalactique.st","order","Skylwalker","Anakin","DarkVador","+66456123789","Que la force soit avec vous","https://vignette.wikia.nocookie.net/lemondededisney/images/3/3d/Dark-vador-1024x768.jpeg/revision/latest?cb=20171030110748&path-prefix=fr");

        UtilisateurEntity utilisateurEntityFromDTORegister = new UtilisateurEntity(userDTORegister);
    }

    @Test (expected = ConstraintViolationException.class)
    public void utilisateurEntity_UserDTORegisterWithEmailNotMatchRegexEmail_ConstraintViolationExeption()
    {
        UserDTORegister userDTORegister = new UserDTORegister( "sith","Order#66","Skylwalker","Anakin","DarkVador","+66456123789","Que la force soit avec vous","https://vignette.wikia.nocookie.net/lemondededisney/images/3/3d/Dark-vador-1024x768.jpeg/revision/latest?cb=20171030110748&path-prefix=fr");

        UtilisateurEntity utilisateurEntityFromDTORegister = new UtilisateurEntity(userDTORegister);
    }

    @Test (expected = ConstraintViolationException.class)
    public void utilisateurEntity_UserDTORegisterWithNomAndPrenomLenght1_ConstraintViolationExeption()
    {
        UserDTORegister userDTORegister = new UserDTORegister( "sith","Order#66","Skylwalker","A","D","+66456123789","Que la force soit avec vous","https://vignette.wikia.nocookie.net/lemondededisney/images/3/3d/Dark-vador-1024x768.jpeg/revision/latest?cb=20171030110748&path-prefix=fr");

        UtilisateurEntity utilisateurEntityFromDTORegister = new UtilisateurEntity(userDTORegister);
    }
    // endregion

}
