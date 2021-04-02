package f4dedDevelopment.Anime.Dal;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;


@Entity
public class Anime extends PanacheEntityBase implements Serializable {

    @Id private String id;
    private String Name;
    private String Description;
    private String ImgSrc;
    private String JapaneseName;
    private Integer episodes;

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
}
