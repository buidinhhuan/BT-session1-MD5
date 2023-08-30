package ra.model.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Song {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id ;
    private  String name;
    private  String MP3;
    private  String category;

    public Song() {
    }

    public Song(Long id, String name, String MP3, String category) {
        this.id = id;
        this.name = name;
        this.MP3 = MP3;
        this.category = category;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getMP3() {
        return MP3;
    }

    public void setMP3(String MP3) {
        this.MP3 = MP3;
    }
    public  void  copy(Song s){
        this.id=s.getId();
        this.name=s.getName();
        this.MP3=s.getMP3();
        this.category=s.getCategory();
    }
}
