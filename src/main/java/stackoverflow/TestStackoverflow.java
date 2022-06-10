package stackoverflow;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class TestStackoverflow {

static class Map2d<K1, K2, V> {
public static void main(String[] args) {
	  String A = "               AAA               \n"
              + "              A:::A              \n"
              + "             A:::::A             \n"
              + "            A:::::::A            \n"
              + "           A:::::::::A           \n"
              + "          A:::::A:::::A          \n"
              + "         A:::::A A:::::A         \n"
              + "        A:::::A   A:::::A        \n"
              + "       A:::::A     A:::::A       \n"
              + "      A:::::AAAAAAAAA:::::A      \n"
              + "     A:::::::::::::::::::::A     \n"
              + "    A:::::AAAAAAAAAAAAA:::::A    \n"
              + "   A:::::A             A:::::A   \n"
              + "  A:::::A               A:::::A  \n"
              + " A:::::A                 A:::::A \n"
              + "AAAAAAA                   AAAAAAA";
      
      String B = "\nBBBBBBBBBBBBBBBBB  \n"
              + "B::::::::::::::::B  \n"
              + "B::::::BBBBBB:::::B \n"
              + "BB:::::B     B:::::B\n"
              + "  B::::B     B:::::B\n"
              + "  B::::B     B:::::B\n"
              + "  B::::BBBBBB:::::B \n"
              + "  B:::::::::::::BB  \n"
              + "  B::::BBBBBB:::::B \n"
              + "  B::::B     B:::::B\n"
              + "  B::::B     B:::::B\n"
              + "  B::::B     B:::::B\n"
              + "BB:::::BBBBBB::::::B\n"
              + "B:::::::::::::::::B \n"
              + "B::::::::::::::::B  \n"
              + "BBBBBBBBBBBBBBBBB ";
      

      String[] AA = A.lines().toArray(String[]::new);
      String[] BB = B.lines().toArray(String[]::new);
      for (int i = 0, m = AA.length; i < m; ++i)
          System.out.println(AA[i] + " " + BB[i + 1]);
}
}
}
