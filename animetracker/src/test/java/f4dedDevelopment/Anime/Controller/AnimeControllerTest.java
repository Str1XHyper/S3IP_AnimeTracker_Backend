package f4dedDevelopment.Anime.Controller;


import f4dedDevelopment.Anime.Dal.Anime;
import f4dedDevelopment.Anime.Logic.AnimeManager;
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
    public void GetAnimeByName(){
        //Arrange
        String AnimeName = "Attack on Titan";
        Anime expected = null;
        Mockito.when(animeManager.GetAnimeByName(AnimeName)).thenReturn(expected);

        //Act
        Anime actual = animeController.GetAnime(AnimeName);

        //Assert
        Assertions.assertEquals(expected,actual);
    }

    @Test
    public void GetAllAnimes(){
        //Arrange
        List<Anime> expected = new ArrayList<>();
        Mockito.when(animeManager.GetAllAnimes()).thenReturn(expected);
        //Act
        List<Anime> actual = animeController.GetAllAnimes();
        //Assert
        Assertions.assertEquals(expected,actual);
    }
}
