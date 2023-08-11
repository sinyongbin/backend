package sin.backend.service;
// 직관성 있게 보여질 수 있게 써준다 1대신에 NO_ID로 쓰는게 훨씬 더 알아보기 편하기 때문에 (return NO_ID;)
public class MemberLoginConst {// 상수처리해서 여러군데서 사용할 수 있게 하는게 훨씬 좋은 처리이다
    public static final int NO_ID = 1;//Controller, view 에서도 사용될 수 있기 때문에 package가달라져서 나중을 생각해서 public로 설정해 두는것이좋다(지금은 service와 msg.jsp에서 사용)
                                     // static final(하나의 상수, 변하지 X)을 만들어서 가볍게한다
                                     // ID가 없다는 의미
    public static final int NO_PWD = 2;// PWD가 아니다는 의미
    public static final int YES_ID_PWD = 3;// ID, PWD가 맞다는 의미
}
