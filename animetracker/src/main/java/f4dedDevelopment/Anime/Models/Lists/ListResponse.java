package f4dedDevelopment.Anime.Models.Lists;

import f4dedDevelopment.Anime.Dal.Anime;

import java.util.List;

public class ListResponse {
    private List<Anime> animes;

    public List<Anime> getAnimes() {
        return animes;
    }

    public void setAnimes(List<Anime> animes) {
        this.animes = animes;
    }
}
