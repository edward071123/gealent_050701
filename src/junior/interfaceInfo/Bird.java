package junior.interfaceInfo;

public class Bird implements IFlyable, IFireable {

    public void line() {
        System.out.println("鳥有直線飛行的技能");
    }

    public void circle() {
        System.out.println("鳥有盤旋飛行的技能");
    }

    public void fire() {
        System.out.println("鳥有噴火的技能");
    }

}
