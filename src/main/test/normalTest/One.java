package normalTest;

/**
 * ${todo}(这里用一句话描述这个类的作用)
 *
 * @author swq
 * @date 2019-03-01 10:42
 */
public class One {
    public static void main(String args[]){
        foo(1);
    }
    static void foo(int coin) {
        switch (coin) {
            case 1: System.out.printf("Cent\n");
            case 5: System.out.printf("Nicke1");break;
            case 10: System.out.printf("Dime");
            case 25: System.out.printf("Quarter");
        }
    }
}
