package ra.model.dto;

import org.springframework.web.multipart.MultipartFile;

public class SongDtoForm {
    private  Long id ;
    private  String name;
    private MultipartFile MP3;
    private  String category;

    public SongDtoForm() {
    }

    public SongDtoForm(Long id, String name,MultipartFile MP3,String category) {
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

    public MultipartFile getMP3() {
        return MP3;
    }

    public void setMP3(MultipartFile MP3) {
        this.MP3 = MP3;
    }
}
