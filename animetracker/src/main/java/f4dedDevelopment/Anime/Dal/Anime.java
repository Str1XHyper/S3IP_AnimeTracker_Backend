package f4dedDevelopment.Anime.Dal;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;


@Entity
public class Anime extends PanacheEntityBase implements Serializable {

    @Id private String id;
    private String Name;
    private String Description;
    private String ImgSrc;
    private String JapaneseName;
    private Integer episodes;
    private LocalDate releaseDate;
    @ElementCollection(targetClass = Genre.class)
    @CollectionTable(name = "AnimeGenres", joinColumns = @JoinColumn(name = "AnimeID"))
    @Column(name = "GenreID")
    @JsonbTransient
    private Set<Genre> genres;

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void Save() {
        persist();
    }

    public String getImgSrc() {
        return ImgSrc;
    }

    public void setImgSrc(String imgSrc) {
        ImgSrc = imgSrc;
    }

    public String getJapaneseName() {
        return JapaneseName;
    }

    public void setJapaneseName(String japaneseName) {
        JapaneseName = japaneseName;
    }

    public Integer getEpisodes() {
        return episodes;
    }

    public void setEpisodes(Integer episodes) {
        this.episodes = episodes;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Set<Genre> getGenres() {
        return genres;
    }

    public void setGenres(Set<Genre> genres) {
        this.genres = genres;
    }
}
