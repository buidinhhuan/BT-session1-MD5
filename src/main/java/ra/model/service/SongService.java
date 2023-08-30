package ra.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
 import ra.model.dto.SongDtoForm;
 import ra.model.entity.Song;
 import ra.model.repository.ISongRepository;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
public class SongService implements  ISongService{
    private String pathImage = "/Users/buidinhhuan/Documents/BOX-MD5/BT-session1/src/main/webapp/upload/";
    @Autowired
    private ISongRepository songRepository;
     @Override
    public List<Song> findAll() {
        return songRepository.findAll();
    }

    @Override
    public Song findByID(Long id) {
        return songRepository.findByID(id);
    }

    @Override
    public void save(SongDtoForm s) {
            // xử lí chuyển đổi
            // upload file
            String filename = null;
            if(!(s.getMP3().isEmpty())){
                filename = s.getMP3().getOriginalFilename();
                try {
                    FileCopyUtils.copy(s.getMP3().getBytes(),new File(pathImage+filename));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

            // chuyển từ dto thành entity
            Song product = new Song(s.getId(),s.getName(),filename,s.getCategory());
            songRepository.save(product);


    }

    @Override
    public void delete(Long id) {
        songRepository.delete(id);
    }
}
