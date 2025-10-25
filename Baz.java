public class Baz {
//        protected double x ;
//        protected String y ;
//        public Foo () {
//            this ("midterm");
//        }
//        public Foo ( String x ) {
//            this (x , x . length ());
//        }
//        public Foo ( String x , int y ) {
//            this . x = y * 0.5;
//            this . y = x ;
//        }
//        public void stuff () {
//            System . out . println ( x );
//            System . out . println ( y );
//        }
//        public void stuff ( int x ) {
//            stuff ( "CS" + x );
//        }
//        public void stuff ( String y ) {
//            x -= 2;
//            System . out . println ( x );
//            System . out . println ( y );
//        }
//        public static class Bar extends Foo {
//            protected int z = 2023;
//            public Bar ( String z ) {
//                System . out . println ( x );
//                System . out . println ( y );
//                System . out . println ( z );
//            }
//            public void stuff () {
//                super . stuff ( "exam" );
//                System . out . println ( z );
//            }
//            public void stuff ( String x ) {
//                stuff ();
//                System . out . println ( x );
//            }
//            public static void main ( String [] args ) {
//                Foo obj = new Bar ("time");
//                obj . stuff (251);
//            }
//    }
//public class Baz {
    private static String a = " boo " ;
    private int b = -100;
    public Baz ( String b ) {
        a = b ;
        this . b = a . length ();
    }
    public void doThings ( String c ) {
        System . out . println ( a );
        System . out . println ( b );
        a = c ;
        b += a . length ();
    }
    public static void main ( String [] args ) {
        Baz bob = new Baz ( "banana" );
        bob . doThings ( "strawberry" );
        Baz jane = new Baz ( "kiwi" );
        jane . doThings ( "pear" );
        bob . doThings ( "peach" );
        jane . doThings ( "pineapple" );
        System . out . println ( a );
        System . out . println ( bob . b );
        System . out . println ( jane . b );
    }
}
//}
