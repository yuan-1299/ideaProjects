import HiddenMember.HiddenClass;

import java.lang.reflect.Field;
import java.lang.reflect.Member;
import java.lang.reflect.Method;

public class HiddenFinder {
    public static void hiddenHunter(Object o, String methodName)  throws  Exception{
        Method method = o.getClass().getDeclaredMethod(methodName);

        method.setAccessible(true);
        method.invoke(o);
    }

    public static void hiddenMemberHunter(Object o, String memberName) throws Exception {

        Field member = o.getClass().getDeclaredField(memberName);
        member.setAccessible(true);
        member.setInt(o,4);
        System.out.println(member.get(o));

    }

    public static void main(String[] args) throws Exception {
        HiddenClass h = new HiddenClass();
        hiddenHunter(h,"hiddenMethod");
        hiddenMemberHunter(h,"hiddenInt");
    }
}
