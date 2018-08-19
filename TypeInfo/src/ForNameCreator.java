import java.util.ArrayList;
import java.util.List;

public class ForNameCreator {
    private static List<Class<? extends Pet>> types = new ArrayList<>();
    private static String[] typeNames = {
            "typeinfo.pets.Mutt",
            "typeinfo.pets.Rat"
    };

    @SuppressWarnings("unchecked")
    private static void loader() {
        try{
            for (String name : typeNames)
                types.add(
                        (Class<? extends Pet>)Class.forName(name));
        }catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
