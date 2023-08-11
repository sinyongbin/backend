package sin.backend.service;

import org.springframework.web.multipart.MultipartFile;
import sin.backend.domain.FileUp;

import java.io.IOException;
import java.util.List;

public interface FileService {
    //(1) 파일 업로드
    long saveFile(MultipartFile mf) throws IOException;

    //(2) 파일 다운로드
    List<FileUp> getFileUpAll();

    FileUp getFileUp(long file_id);

    //(3) 파일 삭제
    void remove(long id);


}
