package f4dedDevelopment.Anime.Controller;


import f4dedDevelopment.Anime.Dal.Anime;
import f4dedDevelopment.Anime.Dal.Genre;
import f4dedDevelopment.Anime.Logic.AnimeManager;
import f4dedDevelopment.Anime.Models.Anime.AddAnime;
import io.quarkus.test.Mock;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@QuarkusTest
public class AnimeControllerTest {
    @Inject AnimeController animeController;
    @InjectMock AnimeManager animeManager;

    @Test
    public void GetAnimeByName_Successful(){
        //Arrange
        String AnimeName = "Attack on Titan";
        Anime expected = new Anime();
        Mockito.when(animeManager.GetAnimeByName(AnimeName)).thenReturn(expected);

        //Act
        Anime actual = animeController.GetAnime(AnimeName);

        //Assert
        Assertions.assertEquals(expected,actual);
    }

    @Test
    public void GetAnimeByName_EmptyString(){
        //Arrange
        String AnimeName = "";

        //Act
        Anime actual = animeController.GetAnime(AnimeName);

        //Assert
        Assertions.assertNull(actual);
    }

    @Test
    public void GetAnimeByName_InputNull(){
        //Act
        Anime actual = animeController.GetAnime(null);

        //Assert
        Assertions.assertNull(actual);
    }

    @Test
    public void GetAllAnimes_Successful(){
        //Arrange
        List<Anime> expected = new ArrayList<>();
        Mockito.when(animeManager.GetAllAnimes()).thenReturn(expected);
        //Act
        List<Anime> actual = animeController.GetAllAnimes();
        //Assert
        Assertions.assertEquals(expected,actual);
    }

    @Test
    public void GetNewestAnimes_Successful(){
        //Arrange
        List<Anime> expected = new ArrayList<>();
        Mockito.when(animeManager.GetNewest()).thenReturn(expected);

        //Act
        List<Anime> actual = animeController.GetNewestAnimes();

        //Assert
        Assertions.assertEquals(expected,actual);
    }

    ///Not implemented yet
//    @Test
//    public void GetRecommendedAnimes_Successful(){
//        //Arrange
//        String userID = "b54d4ac0-b5c0-42ad-a563-1c8a45e9a7af";
//        List<Anime> expected = new ArrayList<>();
//        Mockito.when(animeManager.GetRecommended(userID)).thenReturn(expected);
//
//        //Act
//        List<Anime> actual = animeController.GetRecommendedAnimes(userID);
//
//        //Assert
//        Assertions.assertEquals(expected,actual);
//    }
//
//    @Test
//    public void GetRecommendedAnimes_InputNull(){
//        //Act
//        List<Anime> actual = animeController.GetRecommendedAnimes(null);
//
//        //Assert
//        Assertions.assertNull(actual);
//    }

    @Test
    public void CreateAnime_Successful(){
        //Arrange
        AddAnime addAnime = new AddAnime();
        addAnime.setDescription("test description");
        addAnime.setImgSrc("new Img");
        addAnime.setJapaneseName("De tesuto");
        addAnime.setName("test");
        Anime expected = new Anime();

        Mockito.when(animeManager.CreateAnime(addAnime)).thenReturn(expected);
        System.out.println(expected.getId());
        //Act
        Anime actual = animeController.AddAnime(addAnime);

        //Assert
        Assertions.assertEquals(expected,actual);
    }
}
