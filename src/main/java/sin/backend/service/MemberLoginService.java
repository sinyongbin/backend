package sin.backend.service;

import sin.backend.domain.MemberLogin;

public abstract class MemberLoginService {
    // Id, Pwd를 받아서 DB에 있는지 check
    public abstract int check(String email, String pwd);// check(MemberLogin)과 같다, 경우의 수가 많기 때문에 boolean타입 대신에 int타입으로 받는다
    public abstract MemberLogin getLogin(String email);// dto를 타입으로 받는다, 앞에 repository인터페이스에 적힌것과 메서드명만 다르다
}
