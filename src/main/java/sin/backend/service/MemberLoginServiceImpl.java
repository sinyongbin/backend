package sin.backend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sin.backend.domain.MemberLogin;
import sin.backend.repository.MemberLoginRepository;
import static sin.backend.service.MemberLoginConst.NO_ID;// MemberLoginConst 클래스를 활용하기 위해서 import해준다
import static sin.backend.service.MemberLoginConst.NO_PWD;
import static sin.backend.service.MemberLoginConst.YES_ID_PWD;

@Service
@RequiredArgsConstructor
public class MemberLoginServiceImpl extends MemberLoginService {
    // @Autowired를 해주려면 final을 없애고 final이 있으면 @Autowired를 빼도되고, final이 있으면 생성자가 있어야 하기 때문에 @RequiredArgsConstructor을 적어준다
    private final MemberLoginRepository memberLoginRepository;

    @Override
    public int check(String email, String pwd) {// 로그인을 체킹하는 메서드
        MemberLogin memberLogin = memberLoginRepository.findByEmail(email);
        if(memberLogin == null){
            pln("아이디를 입력해 주세요");
            return NO_ID;//return 1;
        }else {
            String dbPwd = memberLogin.getPwd();
            if(dbPwd != null) dbPwd = dbPwd.trim();// 비밀번호가 null값이 아니라면 비밀번호에 간격을 없애준다

            if(!dbPwd.equals(pwd)){ //client의 pwd와 DB에서 꺼낸 pwd가 같지 않으면 !
                pln("비밀번호가 다릅니다");
                return NO_PWD;// return 2;
            }else{// 같으면 비로소 실행된다
                pln("정상 입력 되었습니다");
                return YES_ID_PWD;// return 3;
            }
        }
    }

    @Override
    public MemberLogin getLogin(String email) {// 이미 로그인한 사용자의 데이터를 뿌려줄 때 사용하는 메서드
        MemberLogin memberLogin = memberLoginRepository.findByEmail(email);
        memberLogin.setPwd("");;// pwd는 Controller파트와 view파트로 넘겨주지 않는것이 중요하다. 그래서 Pwd를 공백으로 처리한다!!!!!!!!

        return memberLogin;// 그래서 Pwd는 공백처리되고 나머지 부분만 memberLogin을 타고 넘어간다(즉, 사용자가 로그인을 하면 이미 있던 사용자의 정보를 알 수 있다)
    }
    void pln(String str) {
        System.out.println(str);
    }
}
