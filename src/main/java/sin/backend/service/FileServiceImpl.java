package sin.backend.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import sin.backend.domain.FileUp;
import sin.backend.repository.FileRepository;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;


@RequiredArgsConstructor
@Service// 구현 클래스는 반드시 등록을 해야한다
public class FileServiceImpl implements FileService {

    @org.springframework.beans.factory.annotation.Value("${file.dir}")// application.properties의 file.dir=C:/BIN/SpringBoot/store/에서 file.dir을 쓴다
    private String fileDir;

    private final FileRepository fileRepository;// @RequiredArgsConstructor을 해주면 자동으로 빨간줄이 사라진다 (자동으로 생성자에서 초기화 해줌)

    //(1) 파일 업로드
    @Override
    public long saveFile(MultipartFile mf) throws IOException {
        if(mf.isEmpty()){//만약에 mf가 비어있으면 null을 리턴해라
            return -1;// 레퍼퍼런스 Long으로 하면 return null;이 가능하다
        }
        String origName = mf.getOriginalFilename();// 업로드된 파일의 원래 이름 추출
        String uuid = UUID.randomUUID().toString();// 파일 이름으로 쓸 uuid 생성
        String extension = origName.substring(origName.lastIndexOf(".")); // .을 찾아서 이 index값을 뽑아낸다(.까지 포함), 확장자 추출(ex: .png)
        String savedName = uuid + extension;// uuid와 확장자 결합
        String savedPath = fileDir + savedName;// 저장될 파일의 물리적 경로(C:/BIN/SpringBoot/store/ 로 보낸다)

        FileUp fileUp = FileUp.builder() // domain의 @Builder을 사용해서 하나하나를 연속으로 셋팅한다, 이렇게 되면 fileUp는 Client에게서 넘어온 파일정보이다
                .orgnm(origName)// (1) 원래 파일 이름 설정
                .savednm(savedName)// (2) 저장된 파일 이름 설정
                .savedpath(savedPath)// (3) 파일의 물리적 경로 설정 ---> 이렇게 3개를 셋팅해야 DB에 3개의 정보가 들어간다
                .build();// 객체 생성
        // builder()와 build() 메소드는 Lombok 라이브러리가 자동으로 생성한 코드로, 객체를 생성하고 필드 값을 설정하기 위해 사용!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!


        mf.transferTo(new File(savedPath));// 파일 객체를 넣어준다, 실제로 로컬에 uuid를 파일명으로 저장, 즉 물리적 경로를 따와서 파일을 업로딩 해준다!!!!!!!!!
        FileUp savedFile = fileRepository.save(fileUp);// 파일 정보를 DB에 저장(insert)하는 것이다, save는 repository의 insert문이다
                                                        // fileUp말고 savedFile라는 새로운 변수를 한개 더 만들었다

        return savedFile.getId();// savedFile에 Id값이 넘어간것을 리턴해준다
    }

    //(2) 파일 다운로드
    @Override
    public List<FileUp> getFileUpAll() {
        List<FileUp> fileUps = fileRepository.findAll();// 모든 파일 정보 조회

        return fileUps;
    }

    @Override
    public FileUp getFileUp(long file_id) {// pk를 가지고 특정한 파일을 꺼내는 메서드
        FileUp fileUp = fileRepository.findById(file_id).orElse(null);// 주어진 ID로 파일 정보 조회

        return fileUp;
    }

    //(3) 파일 삭제
    @Transactional//중요..반드시!! 둘중 하나라도 완성이 안되면 rollback해서 아예 실행이 안된것처럼 만든다
    @Override
    public void remove(long id) {
        // 1) 파일에서 삭제
        FileUp fileUp = fileRepository.findById(id).orElse(null);

        if(fileUp != null) {
            String savedpath = fileUp.getSavedpath();
            File f = new File(savedpath);
            if (f.exists()) {
                f.delete();
            }
        }


        // 2)저장소에서도 삭제 (DB에서 삭제)
        fileRepository.deleteById(id);

    }
}
