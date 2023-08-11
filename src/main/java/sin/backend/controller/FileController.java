package sin.backend.controller;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import sin.backend.domain.FileUp;
import sin.backend.service.FileService;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.charset.StandardCharsets;
import java.util.List;

@RequestMapping("file")// views에서 폴더를 file로 만들 것이다
@RequiredArgsConstructor// 해당 클래스의 필드들을 사용하여 생성자를 생성해주는 역할
@Controller
public class FileController {
    private final FileService fileService;// 멤버변수 ( @RequireArgsConstructor이 생성자에서 초기화를 자동으로해준다 )

    //(1) 파일 업로드
    @GetMapping("upload.do")
    public String form() {
        return "file/form";
    }
    @PostMapping("upload.do")// 목적지가 upload.do이다 (form.jsp를 보면 form action="upload.do" 이 적혀있음)
    public String uploadFile(@RequestParam("file") MultipartFile file, @RequestParam("files") List<MultipartFile> files)
        //이 파라미터값과 form.jsp의 name="files"가 다를때는 @RequestParam("files")이런식으로 써준다(지금은 같아서생략되어 있는데 그냥 써줬다)
        throws IOException {

        long fileSize = file.getSize();
        pln("@fileSize: " + fileSize);// 바이트가 나온다

        fileService.saveFile(file);// 단일 파일

        for(MultipartFile mfile: files) {// file로적어주면 지역변수끼리 충돌이 나서 mfile로 적어줌
            fileService.saveFile(mfile);
        }
        return "redirect:list.do";// index.do는 웰컴페이지
    }

    //(2) 파일 다운로드
    @GetMapping("list.do")
    public String list(Model model){
        List<FileUp> fileUps = fileService.getFileUpAll();
        model.addAttribute("fileUps", fileUps);
        return "file/list";
    }

    //첨부파일 다운로드
    @GetMapping("/attach/{file_id}")
    public ResponseEntity<Resource> downloadAttach(@PathVariable Long file_id) throws MalformedURLException {// MalformedURLException 예외는 URL이 잘못된 형식으로 구성되었을 때 발생
        FileUp fileup = fileService.getFileUp(file_id);
        UrlResource resource = new UrlResource("file:" + fileup.getSavedpath());
        String encodedFileName = org.springframework.web.util.UriUtils.encode(fileup.getOrgnm(), StandardCharsets.UTF_8);

        // 파일 다운로드 대화상자가 뜨도록 하는 헤더를 설정해주는 것
        // Content-Disposition 헤더에 attachment; filename="업로드 파일명" 값을 준다.
        String contentDisposition = "attachment; filename=\"" + encodedFileName + "\"";

        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,contentDisposition).body(resource);
    }

    //이미지 출력
    @GetMapping("/images/{file_id}")
    @ResponseBody
    public org.springframework.core.io.Resource downloadImage(@PathVariable("file_id") Long file_id, Model model)
            throws IOException{
        FileUp fileup = fileService.getFileUp(file_id);
        return new UrlResource("file:" + fileup.getSavedpath());
    }

    void pln(String str){
        System.out.println(str);
    }
}












